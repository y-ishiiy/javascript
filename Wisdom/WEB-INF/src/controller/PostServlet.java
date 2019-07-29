/* 作成日：20190621
 * 作成者：落合竜也
 * 質問投稿画面表示と質問・回答をDBに登録をするサーブレット
 * */

package controller;

import java.io.IOException;
import java.sql.SQLException;

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
import tool.InputChecker;
import tool.PostChecker;

public class PostServlet extends HttpServlet {

    //エラーが発生した時にエラー文を保持するための変数
    private String errorStr = null;
    private int errorNum = 0;

    //質問投稿画面の表示
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //セッションからユーザー情報を取得する
        HttpSession session = request.getSession();

        //未ログインの場合
        if (session.getAttribute(Constant.TAG_USER) == null) {
            //ログインサーブレットにリダイレクト
            response.sendRedirect(Constant.SERVLET_LOGIN);
        } else {

            if (errorStr != null) {
                errorNum = 1;
                request.setAttribute(Constant.TAG_ERROR, errorStr);
            }
            request.getRequestDispatcher(Constant.JSP_POST).forward(request, response);
        }
    }

    //質問投稿、回答投稿、追記投稿を受け取るPostメソッド
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //質問の投稿か、回答の投稿かを判別する
        String origin = request.getParameter(Constant.TAG_POST);

        if (origin == null) {
            errorNum = 2;
            //質問一覧画面へ
            response.sendRedirect(Constant.SERVLET_DETAIL);
            return;
        }

        if (origin.equals(Constant.POST_ANSWER)) { //==========回答投稿処理==========

            int questionId = 0;
            Question question = null;

            //画面からの入力値受け取り
            String questionIdStr = request.getParameter(Constant.TAG_QUESTION_ID);
            String content = request.getParameter(Constant.TAG_CONTENT);
            String userId = request.getParameter(Constant.TAG_USER);
            //入力チェック用のクラスをインスタンス化
            PostChecker postChecker = new PostChecker();
            errorStr = postChecker.contentCheck(content);

            //質問IDの取得。無ければ一覧に戻す。
            if (questionIdStr == null) {
                errorNum = 3;
                //質問一覧画面へ
                response.sendRedirect(Constant.SERVLET_DETAIL);
            } else {
                questionId = Integer.parseInt(questionIdStr);
                QuestionDao questionDao = new QuestionDao();

                try {
                    question = questionDao.findQuestionByQuestionId(questionId);
                } catch (ClassNotFoundException e) {
                    errorNum = 4;
                    e.printStackTrace();
                    //質問一覧画面へ
                    response.sendRedirect(Constant.SERVLET_DETAIL);
                } catch (SQLException e) {
                    errorNum = 5;
                    e.printStackTrace();
                    //質問一覧画面へ
                    response.sendRedirect(Constant.SERVLET_DETAIL);
                }
            }

            //投稿内容に関するチェック
            if (content == null || userId == null) {
                //内部エラー処理
                errorStr = Constant.ERROR_POST_NULL;
                errorNum = 6;

                //質問詳細画面に遷移
                forwardDetail(request, response, question);
            } else if (!errorStr.equals("")) {
                //入力値に関するエラー
                errorNum = 7;

                //質問詳細画面に遷移
                forwardDetail(request, response, question);
            } else {
                //受け取り値の変換

                //HTMLタグのエスケープ
                content = InputChecker.htmlTagCheck(content);
                Answer answer = new Answer(content, questionId, Integer.parseInt(userId));

                //Daoのインスタンス生成
                AnswerDao answerDao = new AnswerDao();

                try {
                    //回答テーブルに内容の登録
                    answerDao.saveAnswer(answer);
                } catch (SQLException | ClassNotFoundException e) {
                    errorNum = 8;
                    e.printStackTrace();
                    //入力画面に遷移
                    this.doGet(request, response);
                    return;
                }
                //質問詳細画面へ
                forwardDetail(request, response, question);
                return;
            }

        } else if (origin.equals(Constant.POST_QUESTION)) { //==========質問投稿処理==========

            //画面からの入力値受け取り
            String title = request.getParameter(Constant.TAG_TITLE);
            String content = request.getParameter(Constant.TAG_CONTENT);
            String userId = request.getParameter(Constant.TAG_USER);

            //入力チェック用のクラスをインスタンス化
            PostChecker postChecker = new PostChecker();
            errorStr = postChecker.titleCheck(title);
            errorStr = postChecker.contentCheck(content);

            if (content.equals(null) || userId.equals(null)) { //nullチェック
                errorNum = 9;
                errorStr = Constant.ERROR_POST_NULL;
                //入力画面に遷移
                this.doGet(request, response);
            } else if (!errorStr.equals("")) { //入力値に関するエラー
                errorNum = 10;
                //入力画面に遷移
                request.setAttribute(Constant.TAG_ERROR, errorStr);
                this.doGet(request, response);
            } else { //受け取り値の変換
                Question question = new Question(title, content, Integer.parseInt(userId));

                //Daoのインスタンス生成
                QuestionDao questionDao = new QuestionDao();
                try {
                    questionDao.saveQuestion(question);
                } catch (SQLException | ClassNotFoundException e) {
                    errorNum = 11;
                    e.printStackTrace();
                    //質問詳細画面へ
                    forwardDetail(request, response, question);
                }
                //質問一覧画面へ
                response.sendRedirect(Constant.SERVLET_DETAIL);
                return;
            }

        } else if (origin.equals(Constant.POST_APPEND)) { //==========追記投稿処理==========

            int questionId = 0;
            Question question = null;

            //画面からの入力値受け取り
            String questionIdStr = request.getParameter(Constant.TAG_QUESTION_ID);
            String content = request.getParameter(Constant.TAG_CONTENT);
            //入力チェック用のクラスをインスタンス化
            PostChecker postChecker = new PostChecker();
            errorStr = postChecker.contentCheck(content);

            //質問IDの取得。無ければ一覧に戻す。
            if (questionIdStr == null) {
                errorNum = 12;
                //質問一覧画面へ
                response.sendRedirect(Constant.SERVLET_DETAIL);
            } else {
                questionId = Integer.parseInt(questionIdStr);
                QuestionDao questionDao = new QuestionDao();

                try {
                    question = questionDao.findQuestionByQuestionId(questionId);
                } catch (SQLException | ClassNotFoundException e) {
                    errorNum = 13;
                    e.printStackTrace();
                    //質問一覧画面へ
                    response.sendRedirect(Constant.SERVLET_DETAIL);
                }
            }

            if (content == null || questionIdStr == null) {
                errorNum = 14;
                //nullチェック
                errorStr = Constant.ERROR_POST_NULL;
                //質問詳細画面に遷移
                forwardDetail(request, response, question);
            } else if (!errorStr.equals("")) {
                errorNum = 15;
                //入力値に関するエラー
                //質問詳細画面に遷移
                forwardDetail(request, response, question);
            } else {
                Append append = new Append(content, questionId);

                //Daoのインスタンス生成
                AppendDao appendDao = new AppendDao();
                QuestionDao questionDao = new QuestionDao();
                try {
                    appendDao.saveAppend(append);
                    question = questionDao.findQuestionByQuestionId(questionId);
                } catch (SQLException | ClassNotFoundException e) {
                    errorNum = 16;
                    e.printStackTrace();
                    //質問一覧画面へ
                    response.sendRedirect(Constant.SERVLET_DETAIL);
                }
                //質問詳細画面へ
                forwardDetail(request, response, question);
                return;
            }
        } else {
            errorNum = 17;
            //質問一覧画面へ
            response.sendRedirect(Constant.SERVLET_DETAIL);
        }

    }

    //質問詳細へフォワードする際に必要なデータのセットをおこなうメソッド
    private void forwardDetail(HttpServletRequest request, HttpServletResponse response, Question question)
            throws ServletException, IOException {
        request.setAttribute(Constant.TAG_QUESTION, question);
        request.setAttribute(Constant.PARA_WHERE, Constant.NAME_DETAIL_JSP);
        request.getRequestDispatcher(Constant.SERVLET_DETAIL).forward(request, response);
    }
}
