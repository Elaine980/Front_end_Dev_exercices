
package com.login.test;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ProfileServlet extends HttpServlet
{
     private static final long serialVersionUID = 1L;
     public ProfileServlet()
     {
         super();
     }

     // check if v is an integer
     public static boolean isInteger(String v){
         try{
               Integer.parseInt(v);
               return true;
         }catch(NumberFormatException e){
               return false;
         }
     }
     

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
         response.setContentType("text/html;charset=UTF-8");

         String nm=request.getParameter("userName");
         String ag=request.getParameter("age1").trim();
         String wg=request.getParameter("weight1").trim();
         String sx=request.getParameter("sex1").trim();

         if(ag!=null && wg!=null && sx!=null && ag!="" && wg!="" && sx!="")
         {

             try {
                 Class.forName("com.mysql.jdbc.Driver");
             } catch (Exception e) {
                 System.out.print("Class Not Found Exception");
             }

             String url = "jdbc:mysql://localhost:3306/test";
             Connection conn = null;
             Statement stmt = null;
             ResultSet rs = null;

             try {
                 conn = (Connection) DriverManager.getConnection(url,"root","12241083");
                 stmt = (Statement) conn.createStatement();

                 String sql = "select * from employees where name='"+nm+"'";
                 rs = stmt.executeQuery(sql);
             } catch (Exception e) {
                 e.printStackTrace();
             }

             try {
                        if(rs.next())
                        {
                              if(isInteger(ag) && isInteger(wg)){
                                // get the integer value
                                int a=Integer.valueOf(ag);
                                int w=Integer.valueOf(wg);

                                // update the user information in MySQL
                                String sq = "update employees set age="+a+",weight="+w+",sex='"+sx+"' where name='"+nm+"'";
                                PreparedStatement p1= (PreparedStatement) conn.prepareStatement(sq);
                                p1.executeUpdate();

                                response.getWriter().print("修改成功！");

                                HttpSession session = request.getSession();
                                session.setAttribute("username", nm);
                                session.setAttribute("age", ag);
                                session.setAttribute("weight", wg);
                                session.setAttribute("sex", sx);
                                response.setHeader("refresh","2; URL=success.jsp");
                             }else{
                                      response.getWriter().print("年龄和体重应该为整数！");
                                      response.setHeader("refresh","2; URL=profile.jsp");
                             }
                        }
                        else{
                           response.getWriter().print("该用户不存在！");
                           response.setHeader("refresh","2; URL=profile.jsp");
                        }
                 } catch (SQLException e) {
                           e.printStackTrace();
                 }
          }
          else
          {
             response.getWriter().print("请输入有效数据！");
             response.setHeader("refresh","2; URL=profile.jsp");
          }
     }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
         doGet(request, response);
     }

 }
