
package com.login.test;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class RegisterTestServlet extends HttpServlet
{
     private static final long serialVersionUID = 1L;
     public RegisterTestServlet()
     {
         super();
     }
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
         // the output informations contain Chinese characters
         response.setContentType("text/html;charset=UTF-8");

         // here we get the register information from the form submitted by the register.jsp page
         String a=request.getParameter("name1");
         String b=request.getParameter("password1");
         String c=request.getParameter("password2");

         if(a!=null && b!=null && c!=null && a!="" && b!="" && c!="")
         // check if all the needed inforamtions are inputted
         {
             if(a.equals(a.replace(" ","?")) && b.equals(b.replace(" ","?")) && c.equals(c.replace(" ","?")))
             // check if the inputted string informations contain blank space
             {
                 try
                 {
                     // connect to MySQL
                     Class.forName("com.mysql.jdbc.Driver");
                     String strUrl = "jdbc:mysql://localhost:3306/test";
                     Connection conn = (Connection) DriverManager.getConnection(strUrl, "root", "12241083");
                     Statement st= (Statement) conn.createStatement();
                     ResultSet rs=st.executeQuery("select * from employees");
                     if(rs!=null && rs.next())
                     {
                         // check if the inputted username was already registered
                         // if yes, warn the user by printing corresponding information on the  screen
                         if(a.equals(rs.getString("name")))
                         {
                             response.getWriter().print("该用户已被注册");
                             response.setHeader("refresh","2; URL=register.jsp");
                         }
                         else
                         {
                             // check if two inputted passwords is the same
                             if(b.equals(c))
                             {
                                 response.getWriter().print("注册成功");

                                 // if registered successfully, store the registration information into MySQL
                                 // the ? stands for a placeholder with further information to complete
                                 // PreparedStatement represents a precompiled SQL statement. A SQL statement is precompiled and stored in a PreparedStatement object
                                 // This object can then be used to efficiently execute this statement multiple times
                                 PreparedStatement pst= (PreparedStatement) conn.prepareStatement("insert into employees(name,password,age,weight,sex)"+"values(?,?,0,0,'M')");
                                 pst.setString(1, a);
                                 pst.setString(2, b);
                                 pst.executeUpdate();
                                 response.setHeader("refresh","2; URL=login.jsp");
                             }
                             else
                             {
                                 response.getWriter().print("两次密码不一致");
                                 response.setHeader("refresh","2; URL=register.jsp");
                             }

                         }
                     }
                 }
                 catch (Exception e)
                 {
                     response.getWriter().print("错误"+e);
                     e.printStackTrace();
                 }
             }
             else
             {
                 response.getWriter().print("请检查格式是否正确");
                 response.setHeader("refresh","2; URL=register.jsp");
             }
         }
         else
         {
             response.getWriter().print("请以正确的方式注册账号");
             response.setHeader("refresh","2; URL=register.jsp");
         }
     }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
         doGet(request, response);
     }

 }
