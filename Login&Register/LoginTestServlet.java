
package com.login.test;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class LoginTestServlet
 * WebServlet Annotation is used to declare a servlet and is processed by the container at deployment time
 */
@WebServlet("/LoginTestServlet")
public class LoginTestServlet extends HttpServlet {
    /**
     * The serialization runtime associates with each serializable class a version number, called a serialVersionUID
     * which is used during deserialization to verify that the sender and receiver of a serialized object
     * have loaded classes for that object that are compatible with respect to serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginTestServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     * HTTP GET requests are idempotent. I.e. you get exactly the same result everytime you execute the request
     * If a Servlet is listening on the URL in question, then its doGet() method will be called to preprocess a request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     * HTTP POST requests are not itempotent and the submitted form data is not reflected in the URL
     * If a Servlet is listening on the URL in question, then its doPost() will be called to postprocess a request.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        // the setContentType() sets the content type of the response being sent to the client, which may include a character encoding specification
        // the setCharacterEncoding() overrides the name of the character encoding used in the body of this request
        // the gb2312 encoding is often used for webpages containing Chinese characters
        response.setContentType("text/heml;charset=gb2312");
        request.setCharacterEncoding("gb2312");

        // get the username and password from the form submitted by the login webpage
        String username = request.getParameter("username");
        String psw = request.getParameter("password");

        if(username == "" || username == null || username.length()>20){
            try {
                // print on screen the warning information and go to the login.jsp page
                response.getWriter().print("请输入用户名（不能超过20个字符）！");
                response.setHeader("refresh","2; URL=login.jsp");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(psw == "" || psw == null || psw.length() > 20){
            try {
                response.getWriter().print("请输入密码（不能超过20个字符）！");
                response.setHeader("refresh","2; URL=login.jsp");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Connecting to MySQL Using the JDBC DriverManager Interface
         */
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.print("Class Not Found Exception");
        }

        //add URL for connecting to MySQL
        String url = "jdbc:mysql://localhost:3306/test";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to MySQL with valid url, account and password
            conn = (Connection) DriverManager.getConnection(url,"root","12241083");
            stmt = (Statement) conn.createStatement();

            // MySQL syntax: select expr... from table_reference where where_condition
            // Here we excute the query to check if the inputted username and password exist in our MySQL table
            String sql = "select * from employees where name='"+username+"' and password= '"+psw+"'";
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    // Set a session for the current request
    // Set an object named "username" for this session and store our username in it
    // getSession() returns the current session associated with this request
    // setAttribute() binds an object to this session, using the name specified
    HttpSession session = request.getSession();
    session.setAttribute("username", username);

    try {
        // Check if the MySQL query result is null or not
        // if not null, get the user information and go to the success.jsp webpage
        if(rs.next()){
            session.setAttribute("age",rs.getString("age")) ;
            session.setAttribute("sex",rs.getString("sex")) ;
            session.setAttribute("weight",rs.getString("weight")) ;
            response.sendRedirect("success.jsp") ;
            return;
        }else{
            // if null, the inputted user does not exist
            // the setArribute() sets an attribute named "message" and stores the value of result in it
            // the sendRedirect() sends a temporary redirect response to the client
            // using the specified redirect location URL and clears the buffer
            session.setAttribute("message", "用户名或密码不匹配。");
            response.sendRedirect("fail.jsp") ;
            return;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}
