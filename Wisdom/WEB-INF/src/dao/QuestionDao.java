/* 変更者：落合竜也
 * 変更日：20190621
 * DBと質問内容に関して連携するDAO
 * */

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import db.DbAccess;
import dto.Question;
import tool.Constant;
import tool.InputChecker;

/**
 * 質問テーブルアクセスオブジェクト
 * @author master
 * @version 1.0
 */
public class QuestionDao {

    /*====================データの取得====================*/
    /**
     * 質問一覧取得メソッド
     * @return 質問一覧(全件)
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Question> getAllQuestions() throws SQLException, ClassNotFoundException {

        //戻り値にする回答リスト
        ArrayList<Question> questions = new ArrayList<Question>();

        //ドライバの起動
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();

        //SQLの流し込み
        String sql = "select * from questions order by " + Constant.COL_QUE_CREATE_AT + " DESC;";
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        ResultSet rs = dbAccess.exeQuery(pstmt);

        //各カラムをQuestion型のクラスに格納する
        while (rs.next()) {
            //質問ID
            int id = rs.getInt(Constant.COL_QUE_ID);
            //質問タイトル　HTMLタグ回避
            String title = InputChecker.htmlTagCheck(rs.getString(Constant.COL_QUE_TITLE));
            //質問文　HTMLタグ回避
            String questionStr = InputChecker.htmlTagCheck(rs.getString(Constant.COL_QUE_CONTENT));
            //質問作成日時
            Timestamp createTime = rs.getTimestamp(Constant.COL_QUE_CREATE_AT);
            //質問ユーザID
            int userId = rs.getInt(Constant.COL_QUE_USER);

            Question question = new Question(id, title, questionStr, createTime, userId);
            questions.add(question);
        }
        //データアクセスの終了
        dbAccess.closeAll();
        return questions;
    }

    /**
     * 質問IDから質問データを取得するメソッド
     * @param id 質問id
     * @return 質問データ
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Question findQuestionByQuestionId(int id) throws ClassNotFoundException, SQLException {
        Question question = null;
        String sql = "select * from questions where questions." + Constant.COL_QUE_ID + " = ? ;";
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = dbAccess.exeQuery(pstmt);
        while (rs.next()) {
            //質問ID
            int questionId = rs.getInt(Constant.COL_QUE_ID);
            //質問タイトル　HTMLタグ回避
            String title = InputChecker.htmlTagCheck(rs.getString(Constant.COL_QUE_TITLE));
            //質問文　HTMLタグ回避
            String questionStr = InputChecker.htmlTagCheck(rs.getString(Constant.COL_QUE_CONTENT));
            //質問作成日時
            Timestamp createTime = rs.getTimestamp(Constant.COL_QUE_CREATE_AT);
            //質問ユーザID
            int userId = rs.getInt(Constant.COL_QUE_USER);
            question = new Question(questionId, title, questionStr, createTime, userId);
        }
        dbAccess.closeAll();
        return question;
    }

    /**
     * ユーザーIDから質問データを取得するメソッド
     * @param id ユーザID
     * @return 質問データリスト
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Question> findQuestion(int id) throws ClassNotFoundException, SQLException {
        ArrayList<Question> questions = new ArrayList<Question>();
        String sql = "select * from questions where questions." + Constant.COL_QUE_USER + " = ? "
                + "order by " + Constant.COL_QUE_CREATE_AT + " desc;";
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = dbAccess.exeQuery(pstmt);
        while (rs.next()) {
            //質問ID
            int questionId = rs.getInt(Constant.COL_QUE_ID);
            //質問タイトル　HTMLタグ回避
            String title = InputChecker.htmlTagCheck(rs.getString(Constant.COL_QUE_TITLE));
            //質問文　HTMLタグ回避
            String questionStr = InputChecker.htmlTagCheck(rs.getString(Constant.COL_QUE_CONTENT));
            //質問作成日時
            Timestamp createTime = rs.getTimestamp(Constant.COL_QUE_CREATE_AT);
            //質問ユーザID
            int userId = rs.getInt(Constant.COL_QUE_USER);
            Question question = new Question(questionId, title, questionStr, createTime, userId);
            questions.add(question);
        }
        dbAccess.closeAll();
        return questions;
    }

    /**
     * questionsの回答ユーザIDと一致する質問データを取得するメソッド
     * @param id 回答ユーザID
     * @return 質問データリスト
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Question> findQuestionId(int id) throws ClassNotFoundException, SQLException {

        //戻り値用のリスト
        ArrayList<Question> questionList = new ArrayList<Question>();
        //被りの質問がすでにリストにある場合falseになるフラグ
        boolean already = true;

        //SQLの作成
        String sql = "select * from questions inner join answers on questions." + Constant.COL_QUE_ID + " = answers."
                + Constant.COL_ANS_QUE_ID + " and answers." + Constant.COL_ANS_USER + " = ? order by questions."
                + Constant.COL_ANS_CREATE_AT + " desc;";

        //ドライバの起動
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();

        //SQLの流し込み
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        pstmt.setInt(1, id);

        //テーブルの受け取り
        ResultSet rs = dbAccess.exeQuery(pstmt);
        while (rs.next()) {
            //質問ID
            int questionId = rs.getInt(Constant.COL_QUE_ID);
            //質問タイトル　HTMLタグ回避
            String title = InputChecker.htmlTagCheck(rs.getString(Constant.COL_QUE_TITLE));
            //質問文　HTMLタグ回避
            String questionStr = InputChecker.htmlTagCheck(rs.getString(Constant.COL_QUE_CONTENT));
            //質問作成日時
            Timestamp createTime = rs.getTimestamp(Constant.COL_QUE_CREATE_AT);
            //質問ユーザID
            int userId = rs.getInt(Constant.COL_QUE_USER);

            //質問クラスの生成
            Question question = new Question(questionId, title, questionStr, createTime, userId);

            //今までのリストを探索
            for (Question que : questionList) {
                if (que.getId() == questionId) {
                    already = false;
                }
            }

            //リスト内に被りの質問がなければ追加する
            if (already) {
                questionList.add(question);
            } else {
                already = true;
            }
        }
        dbAccess.closeAll();
        return questionList;
    }

    /*====================データの登録・更新====================*/
    /**
     * 質問の投稿、更新メソッド
     * @param question 質問データオブジェクト
     * @return 変更データ件数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int saveQuestion(Question question) throws ClassNotFoundException, SQLException {
        if (question.getId() != 0) {
            return updateQuestion(question);
        } else {
            return insertQuestion(question);
        }
    }

    //質問を更新するメソッド
    private int updateQuestion(Question question) throws ClassNotFoundException, SQLException {

        //----------SQL作成----------
        String sql = "update questions set "
                + Constant.COL_QUE_TITLE + " = ?,"
                + Constant.COL_QUE_CONTENT + " = ?,"
                + Constant.COL_QUE_CREATE_AT + " = ?,"
                + Constant.COL_QUE_USER + " = ?;";
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        pstmt.setString(1, question.getTitle());
        pstmt.setString(2, question.getQuestion());
        pstmt.setTimestamp(3, question.getCreateTime());
        pstmt.setInt(4, question.getUserId());
        int cnt = dbAccess.exeUpdate(pstmt);
        dbAccess.closeTwo();
        return cnt;
    }

    //質問を投稿するメソッド
    private int insertQuestion(Question question) throws ClassNotFoundException, SQLException {

        //SQLで参照するカラム
        String columns = Constant.COL_QUE_TITLE + ", " + Constant.COL_QUE_CONTENT
                + ", " + Constant.COL_QUE_CREATE_AT + ", " + Constant.COL_QUE_USER;

        //SQLの作成
        String sql = "insert into questions (" + columns + ") values(?,?,now(),?);";

        //ドライバの起動
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);

        //SQL文へのデータセット
        pstmt.setString(1, question.getTitle());
        pstmt.setString(2, question.getQuestion());
        pstmt.setInt(3, question.getUserId());

        int cnt = dbAccess.exeUpdate(pstmt);
        dbAccess.closeTwo();

        return cnt;
    }

}
