function fadeRect(opacity, str){
  return function(g,i){

  gRect.selectAll("rect")
    .filter(function(d){
    return d.name == g.name;
    })
    .attr("fill", function(w){
    if(str == "over"){
    return "steelblue";
    }else if(str == "out"){
    return "black";
    }
    });

    gLine.selectAll("path")
  .filter(function(d){
  return d.sourceName != g.name;
  })
  .transition()
  .style("opacity", opacity);

  };

}


function fadeRing(opacity, str){
  return function(g,i){

    gRing.selectAll("circle")
      .filter(function(d){
      return d.name == g.name;
      })
      .attr("fill", function(w){
      if(str == "over"){
      return "yellow";
      }else if(str == "out"){
      return "green";
      }
      });

    gLine.selectAll("path")
    .filter(function(d){
    return d.targetName != g.name;

    })
    .transition()
    .style("opacity", opacity);


    gRect.selectAll("rect")
    .each(function(d,j){

    for(var i = 0; i < d.targets.length; i++){

    if(d.targets[i] == g.name){

    d3.select(this)
    .attr("fill", function(w){
    if(str == "over"){
    return "steelblue";
    }else if(str == "out"){
    return "black";
    }
    });

    }

    }

    });

  };
}


function goActivityPlan(){
//alert("Clicked!");
document.location.href = "http://www.taobao.com";
}


function goEngineer(){
//alert("Clicked!");
  document.location.href = "http://www.amazon.cn";
}



function getConceptMap(activity, responsable, engineering, respoMatrix, engMatrix,
origin, padding, long, wide, R, r){

    var conceptMap = new Array();

    var planRect = getPlanRect(activity, responsable, engineering, respoMatrix,
    engMatrix, origin, padding, long, wide);

    var center = planRect[0].ringCenter;

    var ring = getRing(responsable, engineering, center, R, r);
    var ringRespo = ring[0];
    var ringEng = ring[1];

    var numRect = activity.length;
    var numRespo = responsable.length;
    var numEng = engineering.length;

    var lineRespo = new Array();
    var lineEng = new Array();

    // on the left side
    for(var i = 0; i < numRect; i++){
    for(var j = 0; j < numRespo; j++){

    if(respoMatrix[i][j] == 1){
    lineRespo.push({sourceName: planRect[i].name,
    sourceX: planRect[i].x,
    sourceY: planRect[i].y + wide/2,
    targetName: ringRespo[j].name,
    targetX: ringRespo[j].xCenter,
    targetY: ringRespo[j].yCenter,
    });
    }
    }
    }

    // on the right side
    for(var k = 0; k < numRect; k++){
    for(var l = 0; l < numEng; l++){

    if(engMatrix[k][l] == 1){
    lineEng.push({sourceName: planRect[k].name,
    sourceX: planRect[k].x + long,
    sourceY: planRect[k].y + wide/2,
    targetName: ringEng[l].name,
    targetX: ringEng[l].xCenter,
    targetY: ringEng[l].yCenter,
    });
    }
    }
    }

    conceptMap = [planRect, ringRespo.concat(ringEng), lineRespo.concat(lineEng)];
    return conceptMap;


}


function getPlanRect(activity, responsable, engineering, respoMatrix, engMatrix,
origin, padding, long, wide){

    var planRect = new Array();
    var numRect = activity.length;
    var numRespo = responsable.length;
    var numEng = engineering.length;

    var ringCenter = [origin[0] + long / 2,
    origin[1] + (wide * numRect + padding * (numRect - 1)) / 2];

    for(var i = 0; i < numRect; i++){

        var resM = new Array();
        var engM = new Array();

        for(var j = 0; j < numRespo; j++){
            if(respoMatrix[i][j] == 1){
            resM.push(responsable[j]);
        }
        }

        for(var k = 0; k < numEng; k++){
            if(engMatrix[i][k] == 1){
            engM.push(engineering[k]);
        }
        }

        planRect[i] = {name: activity[i],
        x: origin[0],
        y: origin[1] + i * (padding + wide),
        width: wide,
        length: long,
        targets: resM.concat(engM),
        ringCenter: ringCenter
        };

    }

    return planRect;

}


function getRing(responsable, engineering, center, R, r){

    var ring = new Array();

    var numRespo = responsable.length;
    var numEng = engineering.length;

    var angleInterRespo = Math.PI / (numRespo + 1);
    var angleInterEng = Math.PI / (numEng + 1);

    var engRing = new Array();
    var respoRing = new Array();

      // on left Ring
      for(var i = 0; i < numRespo; i++){

          respoRing[i] = {name: responsable[i],
          angle: angleInterRespo * (i + 1),
          xCenter: center[0] - R * Math.sin(angleInterRespo * (i + 1)),
          yCenter: center[1] - R * Math.cos(angleInterRespo * (i + 1)),
          rayon: r,
          position: "left"
          };

      }

      // on right Ring
      for(var j = 0; j < numEng; j++){

          engRing[j] = {name: engineering[j],
          angle: angleInterEng * (j + 1),
          xCenter: center[0] + R * Math.sin(angleInterEng * (j + 1)),
          yCenter: center[1] - R * Math.cos(angleInterEng * (j + 1)),
          rayon: r,
          position: "right"
          };

      }

      ring = [respoRing, engRing];
      return ring;

}
