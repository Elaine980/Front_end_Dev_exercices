
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图片展示</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<script type="text/javascript" src="./public/js/jquery-3.1.1.min.js"></script>
<script>
xStations = new Array();
yStations = new Array();
stationNames = new Array();
stationLines = new Array();
xBegins = new Array();
yBegins = new Array();
xEnds = new Array();
yEnds = new Array();
rValues = new Array();
gValues = new Array();
bValues = new Array();
lineIndex = new Array();

$.getJSON("TopoServlet", function(json){

       for(var i=0; i < eval(json[0]).length; i++){
              xStations.push(json[0][i].xStation);
              yStations.push(json[0][i].yStation);
              stationNames.push(json[0][i].stationName);
              stationLines.push(json[0][i].lineIndex);
              //console.log(xStations[i]);
              //console.log(yStations[i]);
              //console.log(stationNames[i]);
              //console.log(stationLines[i]+" ###");

       }

       for(var i=0; i < eval(json[1]).length; i++){
              xBegins.push(json[1][i].xBegin);
              yBegins.push(json[1][i].yBegin);
              xEnds.push(json[1][i].xEnd);
              yEnds.push(json[1][i].yEnd);
              rValues.push(json[1][i].rVal);
              gValues.push(json[1][i].gVal);
              bValues.push(json[1][i].bVal);
              lineIndex.push(json[1][i].line);
              //console.log(xBegins[i]);
              //console.log(yBegins[i]);
              //console.log(xEnds[i]);
              //console.log(yEnds[i]);
              //console.log(rValues[i]);
              //console.log(gValues[i]);
              //console.log(bValues[i]);
              //console.log(lineIndex[i]+" ###");

       }

});

</script>

<script src="./public/js/processing.js"></script>
<canvas data-processing-sources="./public/processing/topo/topo.pde"></canvas>

</body>
</html>
