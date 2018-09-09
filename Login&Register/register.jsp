
<%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>注册页面</title>
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
  <form action="RegisterTestServlet" method="post">
  <h1 align="center">用户注册</h1>
        <center>
            <table border="1">
                <tr>
                    <td>用户名</td>
                    <td colspan="2">
                        <input type="text" name="name1" value="" size="20" maxlength="20" onfocus="if (this.value=='Your name')  this.value='';" />
                    </td>
                </tr>
                <tr>
                    <td>密   码</td>
                    <td colspan="2">
                        <input type="password" name="password1" value="" size="20" maxlength="20" onfocus="if(this.value=='Your Password') this.value='';">
                    </td>
                </tr>
                <tr>
                    <td>确认密码</td>
                    <td colspan="2">
                        <input type="password" name="password2" value="" size="20" maxlength="20" onfocus="if(this.value=='Your Password') this.value='';">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="Submit" value="提交">
                    </td>
                    <td>
                        <input type="reset" name="Reset" value="重置" />
                    </td>
                    <td>
                        <input type="button" name="Login" value="登录" onclick="goLogin()" />
                    </td>

                </tr>
            </table>
        </center>
  </form>

  <script type="text/javascript">

               function goLogin(){
                 document.location.href="login.jsp";
               }
    </script>

</body>
</html>
