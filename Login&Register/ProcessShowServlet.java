
package com.login.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class ProcessShowServlet
 */
@WebServlet("/ProcessShowServlet")
public class ProcessShowServlet extends HttpServlet {
       private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

       /**
        * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
        */
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              doPost(request, response);
              //response.getWriter().append("Served at: ").append(request.getContextPath());
       }

       /**
        * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
        */
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         response.setContentType("appliation/json");

         PrintWriter out = response.getWriter();
         List<User> ulist = new ArrayList<User>();//传递List

         try{
             Class.forName("com.mysql.jdbc.Driver");
             String strUrl = "jdbc:mysql://localhost:3306/test";
             Connection conn = (Connection) DriverManager.getConnection(strUrl, "root", "12241083");
             Statement st= (Statement) conn.createStatement();
             ResultSet rs=st.executeQuery("select * from employees");

             //遍历结果集获取指定数据
             while(rs!=null && rs.next())
             {
                 ulist.add(new User(rs.getInt("age")));
                 
             }
           }
           catch (Exception e)
           {
                 response.getWriter().print("错误"+e);
                 e.printStackTrace();
           }


            JSONArray jsonArray1 = JSONArray.fromObject(ulist);//转化成json对象
            out.println(jsonArray1);//返给ajax请求
            out.flush();

       }

}
