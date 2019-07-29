/* 作成日：20190625
 * 作成者：落合竜也
 * 回答の削除を行うサーブレット
 * */

package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeleteDao;
import dao.QuestionDao;
import dto.Question;
import tool.Constant;
import tool.InputChecker;

public class DeleteServlet extends HttpServlet {

    //回答の削除要求を処理するGetメソッド
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //==========データの受け取り==========
        //どの質問の回答を消すのか　最終的に戻ってくるページ指定に使用
        String questionIdStr = request.getParameter(Constant.TAG_QUESTION_ID);
        //どの回答を消すのかを回答IDで指定
        String answerIdStr = request.getParameter(Constant.TAG_ANSWER_ID);

        //==========変数宣言==========
        int questionId = 0;//整数型にキャストしたIDを格納する変数
        Question question = null;//質問IDから検索した質問を格納する変数

        //==========質問を取得する処理==========
        if (questionIdStr == null) {
            //内部的なエラーがあれば質問一覧画面へリダイレクト
            response.sendRedirect(Constant.SERVLET_DETAIL);
            return;
        } else if (!InputChecker.positiveIntCheck(questionIdStr)) {
            //IDが自然数でなければ質問一覧へリダイレクト
            response.sendRedirect(Constant.SERVLET_DETAIL);
            return;
        } else {
            //質問IDのキャスト
            questionId = Integer.parseInt(questionIdStr);
            try {
                //質問IDを用いてテーブルから質問を取得
                question = (new QuestionDao()).findQuestionByQuestionId(questionId);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                //質問一覧画面へリダイレクト
                response.sendRedirect(Constant.SERVLET_DETAIL);
                return;
            }
        }

        //==========質問の削除を行う処理==========
        if (answerIdStr == null) {
            //削除したい回答が送られてきていない場合、質問詳細にフォワード
            request.setAttribute(Constant.TAG_QUESTION, question);
            request.setAttribute(Constant.PARA_WHERE, Constant.NAME_DETAIL_JSP);
            request.getRequestDispatcher(Constant.SERVLET_DETAIL).forward(request, response);
        } else if (!InputChecker.positiveIntCheck(answerIdStr)) {
            //IDが自然数でなければ質問一覧へリダイレクト
            request.setAttribute(Constant.TAG_QUESTION, question);
            request.setAttribute(Constant.PARA_WHERE, Constant.NAME_DETAIL_JSP);
            request.getRequestDispatcher(Constant.SERVLET_DETAIL).forward(request, response);
            return;
        } else {
            //回答IDのキャスト
            int answerId = Integer.parseInt(answerIdStr);

            try {
                //削除を行うDAOの実行
                (new DeleteDao()).deleteData(answerId);
            } catch (SQLException | ClassNotFoundException e) {
                //コンソールにエラー出力
                e.printStackTrace();
            } finally {
                //成功した場合でも失敗した場合でも質問詳細画面に遷移
                request.setAttribute(Constant.TAG_QUESTION, question);
                request.setAttribute(Constant.PARA_WHERE, Constant.NAME_DETAIL_JSP);
                request.getRequestDispatcher(Constant.SERVLET_DETAIL).forward(request, response);
            }
        }
    }
}
