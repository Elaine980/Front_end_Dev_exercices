
<%@pagelanguage="java"contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--UTF-8 is a character encoding capable of encoding all possible characters, or code points, defined by Unicode--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--Meta tags are used by the likes of search engines to catalogue information about the web page--%>
<title>用户登录</title>
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
    <form method="POST" name="frmLogin" action="LoginTestServlet">
    <%--we use a form to submit the data inputted by users--%>
    <%--action=The address to which submitted form data should be sent to--%>
    <%--method=The HTTP method by which submitted form data should be sent--%>
    <%--name=The name of the form--%>
        <h1 align="center">用户登录</h1>
        <center>
            <table border="1">
            <%--the table element defines the table, tr defines a table row, td defines a data cell--%>
                <tr>
                    <td>用户名：</td>
                    <td colspan="2">
                    <%--here this data cell will take up two columns--%>
                        <input type="text" name="username" value="" size="20" maxlength="20" onfocus="if (this.value=='Your name')  this.value='';" />
                        <%--here we name the inputted data as username and send it to LoginTestServlet--%>
                        <%--the onfocus attribute is used to clear the default values when the data cell is focused on--%>
                    </td>
                </tr>
                <tr>
                    <td>密  码</td>
                    <td colspan="2">
                        <input type="password" name="password" value="" size="20" maxlength="20" onfocus="if(this.value=='Your Password') this.value='';">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="Submit" value=" 提交" />
                        <%--submit the form--%>
                    </td>
                    <td>
                        <input type="reset" name="Reset" value="重置" />
                        <%--reset the inputted data--%>
                    </td>
                    <td>
                        <input type="button" name="Register" value="注册" onclick="goRegister()" />
                        <%--call the goRegister() function on click--%>
                    </td>
                </tr>
            </table>
        </center>
    </form>

    <script type="text/javascript">
              function goRegister(){
                 document.location.href="register.jsp";
                 // go to the register.jsp webpage
              }
    </script>
</body>
</html>

 