package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.SeminarDAO;
import dto.SeminarDTO;




public class SeminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    	//文字化け対策
    	req.setCharacterEncoding("UTF-8");

    	// JSON 用ライブラリのインスタンス化
    	Gson gson = new Gson();
    	//DBアクセス
    	SeminarDAO dao = new SeminarDAO();
    	//DBから値取得
    	ArrayList<SeminarDTO> seminarList = dao.readSeminar();
    	//文字化け対策
    	resp.setContentType("application/json;charset=UTF-8");
    	// レスポンス用のデータを文字列に変換
    	PrintWriter out = resp.getWriter();
    	out.println(gson.toJson(seminarList));
    	out.close();

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    	//文字化け対策
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=UTF-8");
        // 「data」の受け取り
        String data = (String) req.getParameter("data");
        String method = (String) req.getParameter("method");


        // JSON 用ライブラリのインスタンス化
        Gson gson = new Gson();
        // data をSeminarDTO クラスのインスタンスにパース
        SeminarDTO dto = gson.fromJson(data, SeminarDTO.class);
        // DB へのデータ登録処理
        SeminarDAO dao = new SeminarDAO();
        //dao側での登録処理
        if(method.equals("create")) {
        	//登録処理をするメソッドに登録したい情報(dto)を引き数で渡す
        	ArrayList<SeminarDTO> seminarList = new ArrayList();
        	seminarList = dao.addSeminar(dto);
        	// レスポンス用のデータを文字列に変換
            PrintWriter out = resp.getWriter();
        	out.println(gson.toJson(seminarList));
        	out.close();
        }
        //dao側での更新処理
        if(method.equals("update")) {
        	dao.updateSeminar(dto);
        }
        //dao側での削除処理
        if(method.equals("delete")) {
        	dao.delSeminar(dto);
        }

    }


}




