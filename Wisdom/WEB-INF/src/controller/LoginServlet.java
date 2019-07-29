/*
 * 作成日：2019-06-21
 * 作成者：湯本涼香
 * 更新日：
 * 更新者：
 * 概要：ログインサーブレット
 */

package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;
import tool.Constant;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();

        String path = Constant.JSP_LOGIN;

        //ログイン状態の場合
        if (session.getAttribute(Constant.TAG_USER) != null) {
            //ログアウトボタンが押された場合
            if (request.getParameter("action").equals("logout")) {
                //ログイン状態の解除
                session.removeAttribute(Constant.TAG_USER);
                //質問サーブレットにフォワード
                path = Constant.SERVLET_DETAIL;
            } else {
                //マイページサーブレットにフォワード
                path = Constant.SERVLET_MYPAGE;
            }
        } else {
            //ログイン画面に遷移
            path = Constant.JSP_LOGIN;
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //ログイン失敗のとき自画面に遷移
        String path = Constant.JSP_LOGIN;

        //ログイン状態のとき
        HttpSession session = request.getSession();
        if (session.getAttribute(Constant.TAG_USER) != null) {
            //ログアウトボタンが押された場合
            if (request.getParameter("logout") != null) {
                //ログイン状態の解除
                session.removeAttribute(Constant.TAG_USER);
                //質問サーブレットにリダイレクト
                path = Constant.SERVLET_DETAIL;
            } else {
                //マイページサーブレットにリダイレクト
                path = Constant.SERVLET_MYPAGE;
            }

        //ユーザが未ログインの場合
        } else {
            //画面から入力値の受け取り
            String loginId = request.getParameter("userID");
            String password = request.getParameter("password");

            boolean errFlg = false;
            //nullチェック
            if (loginId.equals(null) || password.equals(null)) {
                errFlg = true;
            } else {
                UserDao userDao = new UserDao();
                try {
                    //ユーザIDが存在
                    User user = userDao.find_User(loginId);
                    if (user != null) {
                        //パスワード一致：ログイン成功
                        if (user.checkPassword(password)) {
                            //セッションにユーザをセット
                            session.setAttribute(Constant.TAG_USER, user);

                            //マイページにリダイレクト
                            response.sendRedirect(Constant.SERVLET_MYPAGE);
                            return;
                        //パスワード不一致
                        } else {
                            errFlg = true;
                        }
                    //ユーザIDが存在しない
                    } else {
                        errFlg = true;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //エラーがある場合エラーメッセージをセット
            if (errFlg) {
                request.setAttribute(Constant.TAG_ERROR, Constant.ERROR_FITTING);
            }
        }

        request.getRequestDispatcher(path).forward(request, response);

    }


}