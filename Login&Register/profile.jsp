
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人资料</title>
</head>
<body>
<%
   response.setHeader("Cache-Control","no-store");
   response.setHeader("Pragma","no-cache");
   response.setDateHeader("Expires",0);
%>

<div align="center">
<%
   // Check if the user has logged in
   String Iusername = (String)session.getAttribute("username") ;
   if(Iusername == null || Iusername == ""){
%>
          <p>用户未登录！<p/><br/>
          <p>正在跳转到登录页面。。。<p/>
<%
       response.setHeader("Refresh", "1;URL=login.jsp");
   }else{
       String Iage = (String)session.getAttribute("age") ;
       String Iweight = (String)session.getAttribute("weight") ;
       String Isex = (String)session.getAttribute("sex") ;
%>

    <%=Iusername %>修改个人资料<br/>
    <form method="POST" name="setProfile" action="ProfileServlet">
                用户：
                <input readOnly name="userName" value=<%=Iusername %>>
                <br/>
                年龄：
                <input type="text" name="age1" value=<%=Iage %> onfocus="if(this.value==Iage) this.value='';">
                <br/>
                体重：
                <input type="text" name="weight1" value=<%=Iweight %> onfocus="if(this.value==Iweight) this.value='';">
                <br/>
                性别：                         
                <% if(Isex.trim().equals("M")) { %>
                Male:
                <input type="radio" name="sex1" value="M" checked="checked">
                Female:
                <input type="radio" name="sex1" value="F">
                <% } else {%>
                Male:
                <input type="radio" name="sex1" value="M" >
                Female:
                <input type="radio" name="sex1" value="F" checked="checked">
                <% } %>
                <br/>

                <input type="submit" name="Submit" value="提交" />
                <input type="reset" name="Reset" value="重置" />
                <input type="button" name="Back" value="返回" onclick="goBack()">
    </form>
</div>>
<% } %>

    <script type="text/javascript">
               function goBack(){
                 document.location.href="success.jsp";
               }

    </script>
</body>
</html>
