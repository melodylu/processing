void setup(){
  size(800, 900);
    background(0);
}



// actual program
void draw(){
  noStroke();
  if (mousePressed) {
    fill(0);
  } else {
    fill(255);
  }
  ellipse(mouseX, mouseY, 80, 80);
}

