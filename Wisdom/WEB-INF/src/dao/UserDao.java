/*
 * 更新者：湯本涼香
 * 更新日：2019-06-21
 */

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbAccess;
import model.User;

/**
 * ユーザテーブルアクセスオブジェクト
 * @author master
 * @version 1.0
 */
public class UserDao {

//    /**
//     *
//     * @param userId ユーザID
//     * @return ユーザデータオブジェクト
//     * @throws SQLException
//     * @throws ClassNotFoundException
//     */
//    public User findUser(int userId) throws ClassNotFoundException, SQLException  {
//        String sql = "select id, login_id, firstname, lastname from users where id = ?;";
//        DbAccess dbAccess = new DbAccess();
//        dbAccess.access();
//        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
//        pstmt.setInt(1, userId);
//        ResultSet rs = dbAccess.exeQuery(pstmt);
//        User user = null;
//        if (rs.next()) {
//            user = new User();
//            user.setId(rs.getInt("id"));
//            user.setLastName(rs.getString("lastName"));
//            user.setFirstName(rs.getString("firstName"));
//            user.setLoginId(rs.getString("login_id"));
//        }
//        dbAccess.closeAll();
//        return user;
//    }

    /**
     * ログインIDに対する社員データを取得するメソッド
     * @param id ログインID
     * @return ユーザデータオブジェクト
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public User find_User(String id) throws ClassNotFoundException, SQLException {
        //戻り値となるユーザデータオブジェクト
        User user = null;

        //SQL作成
        String sql = "select * from users where login_id = ?;";
        DbAccess dbAccess = new DbAccess();

        //ドライバの起動
        dbAccess.access();
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);

        //ログインIDが一致するユーザのテーブルを取得
        pstmt.setString(1, id);
        ResultSet rs = dbAccess.exeQuery(pstmt);

        //データの受け取り
        if (rs.next()) {
            int userId = rs.getInt("id");
            String loginId = rs.getString("login_id");
            String userName = rs.getString("user_name");
            String password = rs.getString("password");
            boolean parents = rs.getBoolean("parents");
            user = new User(userId, loginId, userName, password, parents);
        }
        dbAccess.closeAll();
        return user;
    }

    /**
     * ユーザIDに対する社員データを取得するメソッド
     * @param id ユーザID
     * @return ユーザデータオブジェクト
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public User find_User_By_Id(int id) throws ClassNotFoundException, SQLException {
        //戻り値となるユーザデータオブジェクト
        User user = null;

        //SQL作成
        String sql = "select * from users where users.id = ?;";
        DbAccess dbAccess = new DbAccess();

        //ドライバの起動
        dbAccess.access();
        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);

        //ログインIDが一致するユーザのテーブルを取得
        pstmt.setInt(1, id);
        ResultSet rs = dbAccess.exeQuery(pstmt);

        //データの受け取り
        if (rs.next()) {
            int userId = rs.getInt("id");
            String loginId = rs.getString("login_id");
            String userName = rs.getString("user_name");
            String password = rs.getString("password");
            boolean parents = rs.getBoolean("parents");
            user = new User(userId, loginId, userName, password, parents);
        }
        dbAccess.closeAll();
        return user;
    }

//    /**
//     *
//     * @param loginId ログインユーザID
//     * @param password ログインパスワード
//     * @return ユーザデータオブジェクト
//     * @throws SQLException
//     * @throws ClassNotFoundException
//     */
//    public User login(String loginId, String password) throws SQLException, ClassNotFoundException  {
//        User user = null;
//        String sql = "select id, login_id, firstname, lastname, encrypt_password, salt from users where login_id = ?;";
//        DbAccess dbAccess = new DbAccess();
//        dbAccess.access();
//        PreparedStatement pstmt = dbAccess.makePreparedStatement(sql);
//        pstmt.setString(1, loginId);
//        ResultSet rs = dbAccess.exeQuery(pstmt);
//
//        if (rs.next()) {
//            if (getSaltedPassword(password, rs.getString("salt")).equals(rs.getString("encrypt_password"))) {
//                user = new User();
//                user.setId(rs.getInt("id"));
//                user.setLastName(rs.getString("lastName"));
//                user.setFirstName(rs.getString("firstName"));
//                user.setLoginId(rs.getString("login_id"));
//            }
//        }
//        return user;
//    }
//
//    /**
//     *
//     * @param password ログインパスワード(平文)
//     * @param salt ソルト
//     * @return 生成された安全なパスワード
//     */
//    public String getSaltedPassword(String password, String salt) {
//        return getSha256(salt + password);
//    }
//
//    // 文字列から SHA256 のハッシュ値を取得
//    private String getSha256(String target) {
//        MessageDigest md = null;
//        StringBuffer buf = new StringBuffer();
//        try {
//            md = MessageDigest.getInstance("SHA-256");
//            md.update(target.getBytes());
//            byte[] digest = md.digest();
//
//            for (int i = 0; i < digest.length; i++) {
//                buf.append(String.format("%02x", digest[i]));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return buf.toString();
//    }
}
