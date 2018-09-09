
void setup() {
  size(2000,2000);
  PFont font;
  font = loadFont("Ziggurat-12.vlw");
  textFont(font);
}
void draw() {
  background(255, 255, 255);
  //noStroke();
 
  for(int i=0; i<xStations.length; i++){
    fill(156,225,80,160);
    ellipse(xStations[i],yStations[i],6,6);
    fill(0);
    text(sNames[i],xStations[i]+8,yStations[i]+8);
  }
 
  for(int i=0; i<xBegins.length; i++){
    stroke(rValues[i],gValues[i],bValues[i],205);
    line(xBegins[i],yBegins[i],xEnds[i],yEnds[i]);
  }
}