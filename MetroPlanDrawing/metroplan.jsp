
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图片展示</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
xStations = new Array();
yStations = new Array();
sNames = new Array();
xBegins = new Array();
yBegins = new Array();
xEnds = new Array();
yEnds = new Array();
rValues = new Array();
gValues = new Array();
bValues = new Array();
$.getJSON("MetroServlet", function(json){
       
       for(var i=0; i < eval(json[0]).length; i++){       
              xStations.push(json[0][i].xStation);
              yStations.push(json[0][i].yStation);
              sNames.push(json[0][i].stationName);
              
       }
       
       for(var i=0; i < eval(json[1]).length; i++){       
              xBegins.push(json[1][i].xBegin);
              yBegins.push(json[1][i].yBegin);
              xEnds.push(json[1][i].xEnd);
              yEnds.push(json[1][i].yEnd);
              rValues.push(json[1][i].rVal);
              gValues.push(json[1][i].gVal);
              bValues.push(json[1][i].bVal);
              
       }
       
});
</script>
<script src="./public/js/processing.js"></script>
<canvas data-processing-sources="./public/processing/metro/metro.pde"></canvas>
</body>
</html>