
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图片展示</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
   response.setHeader("Cache-Control","no-store");
   response.setHeader("Pragrma","no-cache");
   response.setDateHeader("Expires",0);

   String userName = (String)session.getAttribute("username") ;
   if(userName == null || userName == ""){
%>       
          <p>用户未登录！<p/><br/>
          <p>正在跳转到登录页面。。。<p/>
<%     
       response.setHeader("Refresh", "1;URL=login.jsp");
   }else{              
%>

<script type="text/javascript" src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
//获取后台传来的jsonArray并将指定数据存入数组
//jsonAge为全局变量
jsonAge = new Array();
//jQuery getJSON方法获取后台数据
//json即为后台传来的json数组
$.getJSON("ProcessShowServlet", function(json){
       //第一种遍历方式   
       //$.each(json, function(i, item) {
       //       alert(item.age);
       //       });
       
       //第二张遍历方式
       //将jsonArray里的用户age信息存入数组jsonAge
       //console.log()仅在控制台中打印相关信息，便于网页调试       
       for(var i = 0; i < eval(json).length; i++){
                  jsonAge.push(json[i].age);
                  console.log("jsonAge["+i+"]="+jsonAge[i]);
       }  
       //将获取的数据存入pde文件中的绘图数组
       //js调用API是异步的  
       detectArray();
                
});
</script>

<script type="text/javascript">
function detectArray(){
       yHeights = new Array();
       for(var i=0; i<jsonAge.length; i++){
              yHeights[i]=jsonAge[i];
       }
}
</script>

<%-- processing.js和pde文件都存放在WebContent目录下的public文件夹里  --%>
<script src="./public/js/processing.js"></script>
<canvas data-processing-sources="./public/processing/histogram.pde"></canvas>


<% } %>
</body>
</html>