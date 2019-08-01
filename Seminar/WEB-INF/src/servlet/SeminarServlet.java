package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import dto.SeminarDTO;
//import dao.SeminarDAO;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    	//文字化け対策
    	req.setCharacterEncoding("UTF-8");

    	// JSON 用ライブラリのインスタンス化
    	Gson gson = new Gson();
//        int kensaku;
//        EmployeeDao dao = new EmployeeDao();
//        ArrayList<Department> departmentList = dao.selectDepartment();
//        req.setAttribute("departmentList", departmentList);
//        //新規登録時の移動処理
//        if(req.getParameter("sinki") != null) {
//            RequestDispatcher dispatch = req.getRequestDispatcher("entry.jsp");
//            dispatch.forward(req, resp);
//        }
    	//削除クリック時の処理
        if(req.getParameter("No") != null) {
            int del = Integer.parseInt(req.getParameter("No"));
              dao.delEmployee(del);

         }
//        //初回起動時のみnullなので-1を入れる
//        if(req.getParameter("busyo") == null) {
//            kensaku = -1;
//
//        }else {
//             String selectedJob = req.getParameter("busyo");
//             kensaku = Integer.parseInt(selectedJob);
//        }
//        ArrayList<Employee> employeeList = dao.selectEmployee(kensaku);
//        req.setAttribute("employeeList", employeeList);
//
//
//
        //seminar.jspへフォワード
        RequestDispatcher dispatch = req.getRequestDispatcher("seminar.jsp");
        dispatch.forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    	//文字化け対策
        req.setCharacterEncoding("utf-8");
        // 「data」の受け取り
        String data = (String) req.getParameter("data");

        // JSON 用ライブラリのインスタンス化
        Gson gson = new Gson();
        // data をSeminarDTO クラスのインスタンスにパース
        SeminarDTO dto = gson.fromJson(data, SeminarDTO.class);
        // DB へのデータ登録処理
        SampleDAO dao = new SampleDAO();
        //登録処理をするメソッドに登録したい情報(dto)を引き数で渡す
        dao.create(dto);
        }

        //DBアクセス
//        EmployeeDao dao = new EmployeeDao();
//
//        if (Check.inputCheck(req.getParameter("no")) &&
//            Check.inputCheck(req.getParameter("name")) &&
//            Check.inputCheck(req.getParameter("birthday")) &&
//            Check.inputCheck(req.getParameter("mail"))) {
//
//            int kensaku = -1;
//
//            ArrayList<Employee> employeeList = dao.selectEmployee(kensaku);
//            //Boolean result = false;
//            for(Employee i : employeeList) {
//                if(employeeno == i.getEmployeeno() || name == i.getName() ||
//                    birthday == i.getBirthday() || mail == i.getMail()) {
//                    req.getRequestDispatcher("error.html").forward(req, resp);
//                }
//            }
//
           //DTOにまとめる
//            Employee employee = new Employee(employeeno, name, birthday, gender, mail,groupno);
//
//
//            //そのままbook.jspへ遷移してしまうとDBへアクセスしないため、古い一覧が表示されてしまう。
//            //一度リダイレクトし、doGetメソッドを経由する。
//            resp.sendRedirect("search-employee");
//        }else {
//            req.getRequestDispatcher("error.html").forward(req, resp);
//        }
    }



}
