/* 作成日：20190620
 * 作成者：落合竜也
 * 質問一覧と質問詳細を表示するJSPの制御サーブレット
 * */

package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AnswerDao;
import dao.AppendDao;
import dao.QuestionDao;
import dto.Answer;
import dto.Append;
import dto.Question;
import tool.Constant;

public class DetailServlet extends HttpServlet {

    //画面表示の要求に対する処理をするPOSTメソッド
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    //画面表示の要求に対する処理をするGETメソッド
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String displayFlag = null;
        //パラメータかアトリビュートでどこに遷移したいのかを取得
        if (request.getParameter(Constant.PARA_WHERE) != null) {
            displayFlag = request.getParameter(Constant.PARA_WHERE);
        } else if (request.getAttribute(Constant.PARA_WHERE) != null) {
            displayFlag = (String) request.getAttribute(Constant.PARA_WHERE);
        } else {
            displayFlag = "";
        }

        if (displayFlag.equals(Constant.NAME_DETAIL_JSP)) {
            //==========質問詳細画面へ==========

            //セッションからユーザー情報を取得する
            HttpSession session = request.getSession();

            //未ログインの場合
            if (session.getAttribute(Constant.TAG_USER) == null) {
                //ログインサーブレットにリダイレクト
                response.sendRedirect(Constant.SERVLET_LOGIN);
                return;
            }
            //押された質問を取得
            String object = request.getParameter(Constant.TAG_SEND_ID);
            Object questionObject = request.getAttribute(Constant.TAG_QUESTION);

            if (object == null && questionObject == null) {
                //何もデータが送られていない場合は質問一覧にリダイレクト
                response.sendRedirect(Constant.SERVLET_DETAIL);
                return;
            } else {

                Question question = null;
                int questionId = 0;

                if (object != null) {
                    //JSP画面から遷移してきた場合の処理
                    questionId = Integer.parseInt(object);
                    QuestionDao questionDao = new QuestionDao();
                    try {
                        question = questionDao.findQuestionByQuestionId(questionId);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        //質問一覧にリダイレクト
                        response.sendRedirect(Constant.SERVLET_DETAIL);
                        return;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        //質問一覧にリダイレクト
                        response.sendRedirect(Constant.SERVLET_DETAIL);
                        return;
                    }

                } else if (questionObject != null) {
                    //サーブレットから遷移してきたときの処理
                    question = (Question) questionObject;
                    questionId = question.getId();
                }

                AnswerDao answerDao = new AnswerDao();
                AppendDao appendDao = new AppendDao();

                //押された質問に対する回答を取得する
                ArrayList<Answer> answerList = new ArrayList<Answer>();
                ArrayList<Append> appendList = new ArrayList<Append>();

                try {

                    appendList = appendDao.findAnswerByQuestion(questionId);
                    answerList = answerDao.findAnswerByQuestion(questionId);
                } catch (ClassNotFoundException e) {
                    //何かする予定
                    e.printStackTrace();
                } catch (SQLException e) {
                    //何かする予定
                    e.printStackTrace();
                }

                request.setAttribute(Constant.TAG_QUESTION, question);
                request.setAttribute(Constant.TAG_APPEND_LIST, appendList);
                request.setAttribute(Constant.TAG_ANSWER_LIST, answerList);
                //質問詳細画面へフォワード
                request.getRequestDispatcher(Constant.JSP_DETAIL).forward(request, response);
                return;
            }
        } else {
            //==========質問一覧画面へ==========

            //DAOから質問一覧を取得する
            QuestionDao questionDao = new QuestionDao();
            ArrayList<Question> questionList = new ArrayList<Question>();
            ArrayList<Question> retQuestionList = new ArrayList<Question>();

            //DAOで起きた例外を処理する
            try {
                questionList = questionDao.getAllQuestions();
            } catch (ClassNotFoundException e) {
                //何かする予定
                e.printStackTrace();
            } catch (SQLException e) {
                //何かする予定
                e.printStackTrace();
            }

            //検索ボタンが押された場合
            String searchWord = request.getParameter(Constant.TAG_SEARCH_WORD);
            if (searchWord != null) {
                for (Question qlist : questionList) {
                    if (qlist.getQuestion().contains(searchWord)) {
                        retQuestionList.add(qlist);
                    }
                }
            } else {
                retQuestionList = questionList;
            }

            request.setAttribute(Constant.TAG_QUESTION_LIST, retQuestionList);
            //質問一覧画面へフォワード
            request.getRequestDispatcher(Constant.JSP_LIST).forward(request, response);

        }

    }
}
