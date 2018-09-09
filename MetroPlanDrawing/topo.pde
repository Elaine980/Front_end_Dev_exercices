
Station[] stations = new Station[xStations.length];
Button[] buttons = new Button[18];
Line[] lines = new Line[18];
buttons[0] = new Button(1,5,5,50,20,"Line 1");
buttons[1] = new Button(2,5,30,50,20,"Line 2");
buttons[2] = new Button(4,5,55,50,20,"Line 4");
buttons[3] = new Button(5,5,80,50,20,"Line 5");
buttons[4] = new Button(6,5,105,50,20,"Line 6");
buttons[5] = new Button(7,5,130,50,20,"Line 7");
buttons[6] = new Button(8,5,155,50,20,"Line 8");
buttons[7] = new Button(9,5,180,50,20,"Line 9");
buttons[8] = new Button(10,5,205,50,20,"Line 10");
buttons[9] = new Button(13,5,230,50,20,"Line 13");
buttons[10] = new Button(14,5,255,50,20,"Line 14");
buttons[11] = new Button(15,5,280,50,20,"Line 15");
buttons[12] = new Button(90,5,305,50,20,"Line 90");
buttons[13] = new Button(93,5,330,50,20,"Line 93");
buttons[14] = new Button(94,5,355,50,20,"Line 94");
buttons[15] = new Button(95,5,380,50,20,"Line 95");
buttons[16] = new Button(96,5,405,50,20,"Line 96");
buttons[17] = new Button(97,5,430,50,20,"Line 97");
buttons[18] = new Button(98,5,455,50,20,"Line 98");
lines[0] = new Line(1);
lines[1] = new Line(2);
lines[2] = new Line(4);
lines[3] = new Line(5);
lines[4] = new Line(6);
lines[5] = new Line(7);
lines[6] = new Line(8);
lines[7] = new Line(9);
lines[8] = new Line(10);
lines[9] = new Line(13);
lines[10] = new Line(14);
lines[11] = new Line(15);
lines[12] = new Line(90);
lines[13] = new Line(93);
lines[14] = new Line(94);
lines[15] = new Line(95);
lines[16] = new Line(96);
lines[17] = new Line(97);
lines[18] = new Line(98);
void setup() {
  size(1920,1080);
  PFont font;
  font = loadFont("Ziggurat-12.vlw");
  textFont(font);
 
  for(int i=0; i<stations.length; i++){
      stations[i] = new Station(stationNames[i], stationLines[i], (float)xStations[i], (float)yStations[i]);
  }
 
}
void draw() {
  background(255, 255, 255);
  //noStroke();
 
  drawButtons();
  drawLines();
 
}
void mousePressed(){
  for(int k=0; k<buttons.length; k++){
      //确定被点击的按钮并对点击进行计数
      if(buttons[k].x<mouseX && mouseX<buttons[k].x+buttons[k].xSize && buttons[k].y<mouseY && mouseY<buttons[k].y+buttons[k].ySize){
          
           buttons[k].count = buttons[k].count + 1;
           if(buttons[k].count % 2 == 0){
               //当有按钮被点击双数次时，显示所有站点和线路，并将所有按钮计数器归零
               for(int i=0; i<lines.length; i++){
                 lines[i].ifdraw = true;
               }
               for(int j=0; j<buttons.length; j++){
                 buttons[j].count = 0;
               }
           }else{
               // 当有按钮被按单数次时，只显示归零后所有被按单数次按钮对应的线路
                for(int i=0; i<lines.length; i++){
                    lines[i].ifdraw = false;
                    for(int j=0; j<buttons.length; j++){
                        if(buttons[j].count != 0 && buttons[j].count % 2 != 0 && buttons[j].line == lines[i].lineindex){
                           lines[i].ifdraw = true;
                        }
                    }
                }
           }
      }
  }
 
}
void drawButtons(){
   for(int i=0; i<buttons.length; i++){
      buttons[i].display();
   }
}
void drawLines(){
    for(int i=0; i<lines.length; i++){
        lines[i].display();
    }
   
}
class Button{
   int x;
   int y;
   int xSize;
   int ySize;
   int line;
   int count;
   int r;
   int g;
   int b;
   String txt;
  
  
   Button(int line_, int x_, int y_, int l, int w, String s){
     line = line_;
     count = 0;
     x = x_;
     y = y_;
     xSize = l;
     ySize = w;
     txt = s;
     for(int i=0; i<lineIndex.length; i++){
         if(lineIndex[i] == line){
            r = rValues[i];
            g = gValues[i];
            b = bValues[i];
            break;
         }
     }
   }
  
   void display(){   
     fill(r,g,b,100);
     rect(x,y,xSize,ySize);
     fill(0);
     text(txt,x+xSize/4,y+3*ySize/4);
   }
}
class Station{
  String stationName;
  String stationPinyin;
  int stationIndex;
  int lineIndex;
  int accNum;
  double longitude;
  double latitude;
  float xStation;
  float yStation;
 
  Station(String s_name, int line, double x, double y){
    stationName = s_name;
    lineIndex = line;
    xStation = (float)x;
    yStation = (float)y;
  }
 
  void display(){ 
      fill(156,225,80,160);
      ellipse(xStation,yStation,12,12);
      fill(0);
      text(stationName+" ",xStation+8,yStation+8);
  }
  
}
class Line{
  int lineindex;
  boolean ifdraw;
 
  Line(int l){
     lineindex = l;
     ifdraw = true;
  }
 
  void display(){
      if(ifdraw){
          for(int i=0; i<stations.length; i++){
              if(stations[i].lineIndex == lineindex){
                 stations[i].display();
              }
          }  
         
          for(int i=0; i<xBegins.length; i++){
              if(lineIndex[i] == lineindex){
                  stroke(rValues[i],gValues[i],bValues[i],205);
                  line((float)xBegins[i],(float)yBegins[i],(float)xEnds[i],(float)yEnds[i]);
              }  
          }
         
      }
  }
}