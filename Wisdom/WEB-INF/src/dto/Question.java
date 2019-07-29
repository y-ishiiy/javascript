//作成者 石本
//作成日 6/21
//DTO質問ページ

package dto;

import java.sql.SQLException;
import java.sql.Timestamp;

import dao.UserDao;

public class Question {
    //質問を区別するID
    private int id;
    //質問のタイトル
    private String title;
    //質問内容
    private String question;
    //質問投稿時間
    private Timestamp createTime;
    //質問者ID
    private int userid;
    //質問者名
    private String userName;

    //==========コンストラクタ==========
    public Question(String title, String question, int userid) {
        this.id = 0;
        this.userid = userid;
        this.title = title;
        this.question = question;

    }

    public Question(int id, String title, String question, Timestamp createTime, int userid) {
        this.id = id;
        this.userid = userid;
        this.title = title;
        this.question = question;
        this.createTime = createTime;

        //ユーザIDを用いてDAOからユーザー名を取得する
        UserDao userDao = new UserDao();
        try {
            this.userName = userDao.find_User_By_Id(userid).getUsername();
        } catch (ClassNotFoundException | SQLException e) {
            //例外発生
            userName = "";
            e.printStackTrace();
        }
    }

    //==========getter==========
    public int getId() {
        return (id);
    }

    public int getUserId() {
        return (userid);
    }

    public String getTitle() {
        return (title);
    }

    public String getQuestion() {
        return (question);
    }

    public Timestamp getCreateTime() {
        return (createTime);
    }

    public String getUserName() {
        return (userName);
    }

    //==========setter==========
    public void setId(int id) {
        this.id = id;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
