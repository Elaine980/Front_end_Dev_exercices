
void setup() {
  size(640, 360);
}

void draw() {
  background(127);
  noStroke();
  for (int i=0, k=0; i < yHeights.length; i++) {
    fill(129, 206, 15);
    rect(k, 0, 10, yHeights[i]);
    k += 20;
  }
}
