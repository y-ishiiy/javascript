/*
 * 作成日：2019-06-21
 * 作成者：湯本涼香
 * 更新日：
 * 更新者：
 * 概要：マイページサーブレット
 */

package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import dto.Question;
import model.User;
import tool.Constant;

public class MyPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ログインしている場合、ログインサーブレットからリダイレクト
        //直接ここにアクセスされたとき
        //セッションがある場合画面表示可能
        //ログインしているユーザの場合マイページに遷移
        String path = Constant.JSP_MYPAGE;

        //セッションからユーザー情報を取得する
        HttpSession session = request.getSession();

        //未ログインの場合
        if (session.getAttribute(Constant.TAG_USER) == null) {
            //ログインサーブレットにリダイレクト
            path = Constant.SERVLET_LOGIN;
        } else {
            //ユーザ情報のキャスト
            User user = (User) session.getAttribute(Constant.TAG_USER);

            //ユーザ情報から質問リストの取得
            QuestionDao questionDao = new QuestionDao();
            ArrayList<Question> questionList = new ArrayList<Question>();
            ArrayList<Question> answerList = new ArrayList<Question>();

            try {
                questionList = questionDao.findQuestion(user.getUserid());
            } catch (ClassNotFoundException | SQLException e) {
                //エラー
                e.printStackTrace();
            }

            try {
                //ユーザが回答した質問の回答リスト
                //リストの質問IDから質問リスト取得
                answerList = questionDao.findQuestionId(user.getUserid());

                request.setAttribute(Constant.TAG_ANSWER_LIST, answerList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //マイページに送るデータのセット
            request.setAttribute(Constant.TAG_QUESTION_LIST, questionList);

            path = Constant.JSP_MYPAGE;
        }

        request.getRequestDispatcher(path).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
