/* 作成日：20190625
 * 作成者：落合竜也
 * 回答の削除を行うDAO
 * */

package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DbAccess;

/**
 * 回答テーブル削除オブジェクト
 * @author TENOHIRA
 * @version 1.0
 */
public class DeleteDao {

    /**
     * 回答IDを参照し回答データを削除するメソッド
     * @param id 回答ID
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteData(int id) throws ClassNotFoundException, SQLException {
        //ドライバの起動
        DbAccess dbAccess = new DbAccess();
        dbAccess.access();

        //SQLを格納する変数
        String sql = "DELETE FROM answers WHERE answers.id =" + id + ";";
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
        //SQLの実行
        pstmt.executeUpdate();
    }
}
