
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--doc type declaration--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功</title>
</head>
<body>
<%
   // HTTP头信息Expires告诉代理服务器它的缓存页面何时将过期
   // no-cache:强制缓存从服务器上获取新的页面
   // no-store: 在任何环境下缓存不保存任何页面
   response.setHeader("Cache-Control","no-store");
   response.setHeader("Pragma","no-cache");
   response.setDateHeader("Expires",0);
%>
<%
   // Check if the user has logged in
   String userName = (String)session.getAttribute("username") ;
   if(userName == null || userName == ""){
%>
          <p>用户未登录！<p/><br/>
          <p>正在跳转到登录页面。。。<p/>
<%
       response.setHeader("Refresh", "1;URL=login.jsp");
   }else{
       String age = (String)session.getAttribute("age");
       String weight = (String)session.getAttribute("weight");
       String sex = (String)session.getAttribute("sex");

       // get the corrsponding values of objects in this session with given names and convert them into String.
       // the trim() method returns a copy of the string, with leading and trailing whitespace omitted.
       if (sex.trim().equals("M")) {
           sex ="男" ;
       }else{
           sex = "女" ;
       }
  %>

  <%--<div> marks up a generic block with no associated semantics--%>
  <div align="center">
   <%=userName %>
   欢迎您，登陆成功！<br />
   <font color="blue">登陆用户信息：</font>

   <table border =1 >
    <tr>
     <td> 姓名： </td>
     <td>  <%=userName %>  </td>
    </tr>
    <tr>
     <td> 年龄： </td>
     <td>  <%=age %>  </td>
    </tr>
    <tr>
     <td> 体重： </td>
     <td>  <%=weight %> kg </td>
    </tr>
    <tr>
     <td> 性别： </td>
     <td>  <%=sex %>  </td>
    </tr>
   </table>
   <a href="show.jsp">展示</a>
   <a href="profile.jsp">修改资料</a>
   <br/>
   <input type="button" name="Logout" value="退出" onclick="logOut()">
  </div>

<% } %>
  <script type="text/javascript">
               // go to the log out page
               function logOut(){
                     document.location.href="logout.jsp";
               }
  </script>

</body>
</html>
