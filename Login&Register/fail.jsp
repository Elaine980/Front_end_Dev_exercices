
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录失败</title>
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
      String userName = (String) session.getAttribute("username");
      String msg = (String) session.getAttribute("message");
%>
    <div align="center">
        <%=userName%>
        对不起，登陆失败！<br /> <font color="red">原因： </font>
        <%=msg%>
        <%--print the reason for the failed login--%>
        <br /> <br /> 5秒后将返回登陆界面。
    </div>

<%
      response.setHeader("Refresh", "5;URL=login.jsp");
      // refresh the webpage in 5 sec and then go to the login.jsp page
%>
</body>
</html>
