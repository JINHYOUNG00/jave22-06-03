package co.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet({ "/emp/AjaxServlet", "/ajax.do" })
public class AjaxServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet() {
        super();
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      response.getWriter().append("Served at: ").append(request.getContextPath());
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      
      
      String job = request.getParameter("job");
      PrintWriter out = response.getWriter(); //출력스트림.
      
      if(job.equals("html")) {
         out.print("<h3>아작스페이지입니다.</h3>");
         out.print("<a href='index.html'>첫페이지로</a>");
      
      
      
   } else if(job.equals("json")) {
      // [{"fname" : ?, "lname":?}, {}, {}]
//      String json = "[";
      EmpDAO dao = new EmpDAO();
//      dao.empList();
      List<Employee> list = dao.empList();
      
      Gson gson = new GsonBuilder().create();
      out.print(gson.toJson(list));
//      
//      
//      for(int i=0; i<list.size(); i++) {
//         json += "{\"fname\" :"+list.get(i).getFirstName()+ "}";
//         if(i != list.size() -1) {
//            json += ",";
//         }
//      }
//      json += "]";
//      
//      out.print(json);
   }
}


   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      doGet(request, response);
      request.setCharacterEncoding("utf-8");
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      
      String cmd = request.getParameter("cmd");
      
      String fname = request.getParameter("fname");
      String lname = request.getParameter("lname");
      String email = request.getParameter("email");
      String job = request.getParameter("job");
      String hdate = request.getParameter("hdate");
      String empId = request.getParameter("empId");
      
      Employee emp = new Employee();
      emp.setFirstName(fname);
      emp.setLastName(lname);
      emp.setEmail(email);
      emp.setJobId(job);
      emp.setHireDate(hdate);
      
      //등록부분
      if(cmd.equals("insert")) {
      
      EmpDAO dao = new EmpDAO();
      dao.insertEmp(emp);
      
      
      System.out.println(emp);
      
      
      //수정부분
      }else if(cmd.equals("update")) {
         emp.setEmployeeId(Integer.parseInt(empId));
//         dao.updateEmp(emp);
         EmpDAO dao = new EmpDAO();
         if(dao.updateEmp(emp)== null) {
            System.out.println("error");
         }else {
            System.out.println("success");
         }
         
         
      }
      
      Gson gson = new GsonBuilder().create();
      response.getWriter().print(gson.toJson(emp));
   }
   


}

/*
 * package co.edu;
 * 
 * import java.io.IOException; import java.io.PrintWriter; import
 * java.util.List;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import com.google.gson.Gson; import com.google.gson.GsonBuilder;
 * 
 * @WebServlet({ "/AjaxServlet", "/ajax.do" }) public class AjaxServlet extends
 * HttpServlet { private static final long serialVersionUID = 1L;
 * 
 * public AjaxServlet() { super(); }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * response.setCharacterEncoding("utf-8"); // 한글 깨짐 수정
 * response.setContentType("text/html;charset=utf-8"); // html로 타입 변경 // 한글 깨지는거
 * 이렇게 해도됨
 * 
 * String job = request.getParameter("job"); PrintWriter out =
 * response.getWriter(); // 출력스트림
 * 
 * if (job.equals("html")) { out.print("<h3>아작스페이지입니다.</h3>");
 * out.print("<a href='index.html'>첫페이지로</a>");
 * 
 * } else if (job.equals("json")) { // [{"fname":?, "lname":?},{},{}] String
 * json = "["; EmpDAO dao = new EmpDAO(); List<Employee> list = dao.empList();
 * 
 * Gson gson = new GsonBuilder().create(); out.print(gson.toJson(list)); //
 * for(int i =0; i<list.size();i++) { // json +=
 * "{\"fname\":"+list.get(i).getFirstName() + "}"; // if(i != list.size()-1) {
 * // json += ", "; // } // } // json += "]"; // out.print(json);
 * 
 * } }
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { // doGet(request, response);
 * 
 * request.setCharacterEncoding("utf-8"); //
 * response.setCharacterEncoding("utf-8");
 * response.setContentType("text/html;charset=utf-8");
 * 
 * String cmd = request.getParameter("cmd");
 * 
 * response.setContentType("text/html");
 * 
 * String fname = request.getParameter("fname"); String lname =
 * request.getParameter("lname"); String email = request.getParameter("email");
 * String hdate = request.getParameter("hdate"); String job =
 * request.getParameter("job"); String empId = request.getParameter("empId");
 * 
 * Employee emp = new Employee(); emp.setFirstName(fname);
 * emp.setLastName(lname); emp.setEmail(email); emp.setHireDate(hdate);
 * emp.setJobId(job); emp.setEmployeeId(Integer.parseInt(empId));
 * 
 * if (cmd.equals("insert")) { // 등록 EmpDAO dao = new EmpDAO();
 * 
 * dao.insertEmp(emp);
 * 
 * System.out.println(emp); } else if (cmd.equals("update")) {
 * emp.setEmployeeId(Integer.parseInt(empId)); // dao.updateEmp(emp); EmpDAO dao
 * = new EmpDAO(); if (dao.updateEmp(emp) == null) {
 * System.out.println("error"); } else { System.out.println("success"); Gson
 * gson = new GsonBuilder().create();
 * response.getWriter().print(gson.toJson(emp)); }
 * 
 * 
 * 
 * }
 * 
 * } }
 * 
 * } else if (cmd.equals("update")) { // 수정 EmpDAO dao = new EmpDAO();
 * 
 * if(dao.updateEmp(emp) == null) { // {"retCode":"error"}
 * System.out.println("error"); } else { // {"retCode":"success"}
 * System.out.println("success"); } }
 * 
 * 
 * 
 * System.out.println(emp);
 * 
 */