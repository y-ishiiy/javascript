//作成者・・・石本

//作成日・・・6/20/219

//DTO回答ページ

package dto;

import java.sql.SQLException;
import java.sql.Timestamp;

import dao.UserDao;

public class Answer {

    //回答の時に使うid
    private int answerId;
    //回答本文
    private String answer;
    //どの質問に対する回答か
    private int questionId;
    //いつ投稿したか
    private Timestamp createTime;
    //そのままuseridの意味
    private int userId;
    //ユーザー名
    private String userName;

    //==========コンストラクタ==========
    public Answer(String answer, int questionId, int userId) {
        //DAOからユーザー名を取得
        this.answerId = 0;
        this.answer = answer;
        this.questionId = questionId;
        this.userId = userId;

        //DAOからユーザー名を取得するのでまだエラー起こしたままにする
        this.userName = null;
    }

    public Answer(int answerId, String answer, int questionId, Timestamp createTime, int userId) {
        //DAOからユーザー名を取得
        this.answerId = answerId;
        this.answer = answer;
        this.questionId = questionId;
        this.userId = userId;
        this.createTime = createTime;

        //ユーザIDを用いてDAOからユーザー名を取得する
        UserDao userDao = new UserDao();
        try {
            this.userName = userDao.find_User_By_Id(userId).getUsername();
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            //例外発生
            userName = "名無しさん";
            e.printStackTrace();
        }
    }

    //==========getter==========
    public int getUserId() {
        return (userId);
    }

    public int getAnswerId() {
        return (answerId);
    }

    public String getAnswer() {
        return (answer);
    }

    public int getQuestionId() {
        return (questionId);
    }

    public String getUserName() {
        return (userName);
    }

    public Timestamp getCreateTime() {
        return (createTime);
    }
    //==========setter==========

    public void setUserid(int userid) {
        this.userId = userid;
    }

    public void setAnswerid(int answerid) {
        this.answerId = answerid;

    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
