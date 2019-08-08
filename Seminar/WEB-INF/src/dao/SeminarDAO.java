package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.SeminarDTO;

public class SeminarDAO {
	  //データベースへの接続情報を定義(他からアクセス禁止、再代入禁止）
	    private static String url = "jdbc:postgresql://localhost/seminar"; //DBのURL (localhost/の後にDB名)
	    private static String userName = "postgres"; //データベースのユーザID
	    private static String password = "password"; //データベースのログインパスワード

	    public SeminarDAO() {
	        try {
	            //JDBCドライバの読み込み(ドライバが見つからない場合は例外が発生するため、try~catchで囲む)
	            Class.forName("org.postgresql.Driver");

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    //データベースから全値を取る（全件表示Read）
	    public ArrayList<SeminarDTO> readSeminar() {

	        //全レコード分のデータを格納する配列
	        ArrayList<SeminarDTO> seminarList = new ArrayList();

	        String sql = "select * from seminars;";
	        try (Connection connection = DriverManager.getConnection(url, userName, password);
	                PreparedStatement pstatement = connection.prepareStatement(sql);) {
	            ResultSet result = pstatement.executeQuery();
	            while (result.next()) {
	                int id = result.getInt("id");
	                String name = result.getString("name");
	                String crowded = result.getString("crowded");

	                SeminarDTO seminarDTO = new SeminarDTO(id,name,crowded);
	                //1レコード分のオブジェクトを追加
	                seminarList.add(seminarDTO);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        //whileが最後のレコードを読み終わった後、全レコード分のデータを戻す
	        return seminarList;
	    }

	    //データベースへの追加処理（講座新規登録Create）
	    public ArrayList<SeminarDTO> addSeminar(SeminarDTO seminars) {

	    	ArrayList<SeminarDTO> seminarList = new ArrayList();
	        String sql = "INSERT INTO seminars(name,crowded) VALUES(?,?) RETURNING *";
	        try (Connection connection = DriverManager.getConnection(url, userName, password);
	                PreparedStatement pstatement = connection.prepareStatement(sql);) {

	            //データ登録(SQL実行）
	            pstatement.setString(1, seminars.getName());
	            pstatement.setString(2, seminars.getCrowded());

	            ResultSet result = pstatement.executeQuery();
	            while (result.next()) {
	            	int id = result.getInt("id");
	                String name = result.getString("name");
	                String crowded = result.getString("crowded");

	                SeminarDTO seminarDTO = new SeminarDTO(id,name,crowded);
	                //1レコード分のオブジェクトを追加
	                seminarList.add(seminarDTO);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	      //whileが最後のレコードを読み終わった後、全レコード分のデータを戻す
	        return seminarList;
	    }
	    //データベースへの変更処理（講座編集Update）
	    public void updateSeminar(SeminarDTO seminars) {
	        String sql = "UPDATE seminars SET name = ? WHERE id= ?;";
	        try (Connection connection = DriverManager.getConnection(url, userName, password);
	                PreparedStatement pstatement = connection.prepareStatement(sql);) {

	            //データ登録(SQL実行）
	        	pstatement.setString(1, seminars.getName());
	            pstatement.setInt(2, seminars.getId());

	            pstatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    //データベースの削除処理（講座削除Delete）
	    public void delSeminar(SeminarDTO seminars) {
	        String sql = "DELETE FROM seminars WHERE id = ?;";
	        try (Connection connection = DriverManager.getConnection(url, userName, password);
	                PreparedStatement pstatement = connection.prepareStatement(sql);) {

	            pstatement.setInt(1,seminars.getId());

	            pstatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


}