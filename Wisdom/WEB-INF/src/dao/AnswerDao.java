/* 変更者：落合竜也
 * 変更日：20190621
 * 回答テーブルにアクセスするDAO
 * */

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import db.DbAccess;
import dto.Answer;
import tool.Constant;
import tool.InputChecker;

/**
 * 回答テーブルアクセスオブジェクト
 * @author TENOHIRA
 * @version 1.0
 */
public class AnswerDao {

    /**
     * 質問IDに対する回答データを取得するメソッド
     * @param id 質問ID
     * @return 回答データリスト
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Answer> findAnswerByQuestion(int id) throws ClassNotFoundException, SQLException {
        //IDに対して回答データを取得するメソッドを呼び出す
        return (findAnswerBy(id, "answers." + Constant.COL_ANS_QUE_ID));
    }

    /**
     * ユーザーが回答したデータを取得するメソッド
     * @param id ユーザーID
     * @return 回答データリスト
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Answer> findAnswerByUser(int id) throws ClassNotFoundException, SQLException {
        return (findAnswerBy(id, "answers." + Constant.COL_ANS_USER));
    }

    /**
     * IDが一致するデータを取得するメソッド
     * @param id データベースのID
     * @param column IDを参照するカラム名
     * @return 回答データリスト
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Answer> findAnswerBy(int id, String column) throws ClassNotFoundException, SQLException {

        //戻り値にする回答リスト
        ArrayList<Answer> answers = new ArrayList<Answer>();

        //----------SQL作成----------
        //参照カラム　ID,回答内容,回答作成日時,質問ID,回答ユーザーID
        String columns = Constant.COL_ANS_ID + ", " + Constant.COL_ANS_CONTENT + ", " + Constant.COL_ANS_CREATE_AT
                + ", " + Constant.COL_ANS_QUE_ID + ", " + Constant.COL_ANS_USER;

        String sql = "SELECT " + columns + " FROM answers WHERE " + column + " = ? order by answers."
                + Constant.COL_ANS_CREATE_AT + " DESC;";

        //ドライバの起動
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);

        //質問IDで条件比較を行い、質問IDに対する回答を取得
        pstmt.setInt(1, id);
        ResultSet rs = dbAccess.exeQuery(pstmt);

        //データの受け取り
        while (rs.next()) {
            //回答ID
            int answerId = rs.getInt(Constant.COL_ANS_ID);
            //回答内容　HTMLタグ回避
            String answerStr = InputChecker.htmlTagCheck(rs.getString(Constant.COL_ANS_CONTENT));
            //回答作成日時
            Timestamp createTime = rs.getTimestamp(Constant.COL_ANS_CREATE_AT);
            //回答をする質問ID
            int questionId = rs.getInt(Constant.COL_ANS_QUE_ID);
            //回答ユーザID
            int userId = rs.getInt(Constant.COL_ANS_USER);

            Answer answer = new Answer(answerId, answerStr, questionId, createTime, userId);
            answers.add(answer);
        }

        //データアクセスの終了
        dbAccess.closeAll();
        return answers;
    }

    /**
     * 回答データを登録/更新するメソッド
     * @param answer 回答データオブジェクト
     * @return 登録/更新データ件数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int saveAnswer(Answer answer) throws SQLException, ClassNotFoundException {
        if (answer.getAnswerId() != 0) {
            return updateAnswer(answer);
        } else {
            return insertAnswer(answer);
        }
    }

    //データ更新メソッド
    private int updateAnswer(Answer answer) throws SQLException, ClassNotFoundException {
        //----------SQL作成----------
        //参照カラム　ID,回答内容,回答作成日時,質問ID,回答ユーザーID
        String sql = "UPDATE answers SET "
                + Constant.COL_ANS_CONTENT + " = ?,"
                + Constant.COL_ANS_CREATE_AT + " = ?,"
                + Constant.COL_ANS_QUE_ID + " = ?,"
                + Constant.COL_ANS_USER + " = ?,"
                + " where " + Constant.COL_ANS_ID + " = ?;";

        //ドライバの起動
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();

        //SQLへのデータの流し込み
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        pstmt.setString(1, answer.getAnswer());
        pstmt.setTimestamp(2, answer.getCreateTime());
        pstmt.setInt(3, answer.getQuestionId());
        pstmt.setInt(4, answer.getUserId());
        pstmt.setInt(5, answer.getAnswerId());

        int cnt = dbAccess.exeUpdate(pstmt);
        dbAccess.closeTwo();
        return cnt;
    }

    //データ登録メソッド
    private int insertAnswer(Answer answer) throws SQLException, ClassNotFoundException {

        //SQLで参照するカラム
        String columns = Constant.COL_ANS_CONTENT + ", " + Constant.COL_ANS_CREATE_AT
                + ", " + Constant.COL_ANS_QUE_ID + ", " + Constant.COL_ANS_USER;

        String sql = "insert into answers (" + columns + ") values(?,now(),?,?);";

        DbAccess dbAccess = new DbAccess();
        dbAccess.access();
        //SQLへのデータの流し込み
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        pstmt.setString(1, answer.getAnswer());
        pstmt.setInt(2, answer.getQuestionId());
        pstmt.setInt(3, answer.getUserId());

        int cnt = dbAccess.exeUpdate(pstmt);
        dbAccess.closeTwo();
        return cnt;
    }

}
