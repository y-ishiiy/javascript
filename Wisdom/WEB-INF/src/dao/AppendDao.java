/* 変更者：落合竜也
 * 変更日：20190621
 * 追記テーブルにアクセスするDAO
 * */


package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import db.DbAccess;
import dto.Append;
import tool.Constant;
import tool.InputChecker;

/**
 * 追記テーブルアクセスオブジェクト
 * @author TENOHIRA
 * @version 1.0
 */
public class AppendDao {

    /**
     * 質問IDに対する追記データを取得するメソッド
     * @param id 質問ID
     * @return 追記データリスト
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Append> findAnswerByQuestion(int id) throws ClassNotFoundException, SQLException {
        //戻り値にする回答リスト
        ArrayList<Append> appends = new ArrayList<Append>();

        //==========SQL作成==========
        //参照カラム　ID,追記内容,追記作成日時,質問ID
        String columns = Constant.COL_APP_ID + ", " + Constant.COL_APP_CONTENT + ", " + Constant.COL_APP_CREATE_AT
                + ", " + Constant.COL_APP_QUE_ID;

        String sql = "SELECT " + columns + " from append_questions where question_id = ? order by "
                + Constant.COL_APP_CREATE_AT + " DESC;";
        DbAccess dbAccess = new DbAccess();

        //ドライバの起動
        dbAccess.access();
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);

        //質問IDで条件比較を行い、質問IDに対する回答を取得
        pstmt.setInt(1, id);
        ResultSet rs = dbAccess.exeQuery(pstmt);

        //==========データの受け取り==========
        while (rs.next()) {
            //追記ID
            int appendId = rs.getInt(Constant.COL_APP_ID);
            //質問追記文　HTMLタグ回避
            String appendStr = InputChecker.htmlTagCheck(rs.getString(Constant.COL_APP_CONTENT));
            //追記作成日時
            Timestamp createTime = rs.getTimestamp(Constant.COL_APP_CREATE_AT);
            //追記した質問のID
            int questionId = rs.getInt(Constant.COL_APP_QUE_ID);

            Append append = new Append(appendId, appendStr, questionId, createTime);
            appends.add(append);
        }
        //データアクセスの終了
        dbAccess.closeAll();
        return appends;
    }

    /**
     * 追記データを登録/更新するメソッド
     * @param answer 追記データオブジェクト
     * @return 登録/更新データ件数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int saveAppend(Append append) throws SQLException, ClassNotFoundException {
        if (append.getAppendId() != 0) {
            return updateAppend(append);
        } else {
            return insertAppend(append);
        }
    }

    //データ更新メソッド
    private int updateAppend(Append append) throws SQLException, ClassNotFoundException {
        //----------SQL作成----------
        //参照カラム　ID,回答内容,回答作成日時,質問ID,回答ユーザーID
        String sql = "UPDATE answers SET "
                + Constant.COL_APP_CONTENT + " = ?,"
                + Constant.COL_APP_CREATE_AT + " = ?,"
                + Constant.COL_APP_QUE_ID + " = ?,"
                + " where " + Constant.COL_ANS_ID + " = ?;";

        //ドライバの起動
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();

        //SQLへのデータの流し込み
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        pstmt.setString(1, append.getContent());
        pstmt.setTimestamp(2, append.getCreateTime());
        pstmt.setInt(3, append.getQuestionId());
        pstmt.setInt(4, append.getAppendId());

        int cnt = dbAccess.exeUpdate(pstmt);
        dbAccess.closeTwo();
        return cnt;
    }

    //データ登録メソッド
    private int insertAppend(Append append) throws SQLException, ClassNotFoundException {

        //SQLで参照するカラム
        String columns = Constant.COL_APP_CONTENT + ", " + Constant.COL_APP_CREATE_AT
                + ", " + Constant.COL_APP_QUE_ID;

        String sql = "insert into append_questions (" + columns + ") values(?,now(),?);";
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();
        //SQLへのデータの流し込み
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        pstmt.setString(1, append.getContent());
        pstmt.setInt(2, append.getQuestionId());

        int cnt = dbAccess.exeUpdate(pstmt);
        dbAccess.closeTwo();
        return cnt;
    }

}
