
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注销登录</title>
</head>
<body>
<%
   response.setHeader("Cache-Control","no-store");
   response.setHeader("Pragma","no-cache");
   response.setDateHeader("Expires",0);

   // log out and remove user information
   session.removeAttribute("username");
   session.invalidate();
%>
  <p>注销成功！<p/><br/>
  <p>正在跳转到登录页面。。。<p/>
<%
   response.setHeader("Refresh", "1;URL=login.jsp");
%>
</body>
</html>
