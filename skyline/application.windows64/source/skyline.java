import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class skyline extends PApplet {


int blocksBuilt = 0; //why the fuck are you the only variable that fuctions can see?
float cityGround = 0;
int moonX = 0;
int moonY = 0;
float moonRise = 0;
int moonOpacity = 0;
int moonLeft = 0;
int moonRight = 0;
int moonMoveDistance = 0;
float moonDiameter = 90;
int moonCycle = 10;
int bob = 0;
float tester = 0;



// John made this class, fill internal variable "lights" with array about lights on/off
class Building {
  int base_x;
  int base_y;
  int height;
  int width;
  int lights;
};



//John's crazy C syntax doesn't work here but it's a good idea
//Building a, b, c;
//
//Building city[30];
//
//city[0].base_x = 3;
//city[0].base_y = 0;
//
//
//print(a.base_x);




public void setup() {
  size(800, 300);
  background(0xff233750);
  // moonY = 10+int(random(90));

  moonMoveDistance = width/60;
  cityGround = height*.75f;
}



public void draw() {
  int s = second();
  float secondsInPi = s*.01f;
  float tester = sin(((secondsInPi*PI)));
  bob = PApplet.parseInt(tester*100);
  //  moonCycle = int(second()*width);
  moonCycle = abs(-PApplet.parseInt(30-second()));

  print("MoonX:   "+moonX+'\n');
  print("MoonY:   "+moonY+'\n');
  print("secondsInPi:   "+secondsInPi+'\n');
  print("tester:   "+tester+'\n');
  print("bob:   "+bob+'\n');

  print("moonCycle:   "+moonCycle+'\n');
  print("second: "+second()+'\n');  
  print('\n');
  //int s = second();  // Values from 0 - 59
  //int m = minute();  // Values from 0 - 59
  //int h = hour();    // Values from 0 - 23
  // line(s*5, 0, s*5, 33);
  // line(m, 33, m, 66);
  // line(h, 66, h, 100);

  drawMoon();
}

//the moon comes out
public void drawMoon() {

  strokeWeight(4);
  stroke(0xff233750);
  fill(0xff233750);
  ellipse(moonX, moonY, moonDiameter, moonDiameter);
  moonX = second()*moonMoveDistance;
  moonY = 100-bob;
  
  
  noStroke();
  
  
  //  fill(#FFFDED, (second()*5));
  int moonFill = moonCycle*(256/30);
  print(moonFill+'\n');
  fill(0xffFFFDED, moonFill);
  
  ellipse(moonX, moonY, moonDiameter, moonDiameter);
  moonLeft = moonX-PApplet.parseInt(moonDiameter)/2;
  moonRight = moonX+PApplet.parseInt(moonDiameter)/2;
  paintStars();
  buildThisCity();
}


// Build some city blocks!
public void buildThisCity() {

  // Build a random number of buildings on the block
  for (int numberOfBuildings = 0; numberOfBuildings < 15; numberOfBuildings ++) {
    int buildingsColor = color(0, 126-4*blocksBuilt, 255-4*blocksBuilt, blocksBuilt*25);
    fill(buildingsColor);
    stroke(buildingsColor-100, blocksBuilt*26);

    float currentBuildingWidth = random(10, 50);
    float currentBuildingHeight = -random(10, 90);
    float currentNumberWindowsHoriz = currentBuildingWidth/5;
    float currentNumberWindowsVert = currentBuildingHeight/8;
    float currentBuildingX = random(-10, width);
    float currentBuildingY = cityGround+(5*blocksBuilt);

    strokeWeight(1);
    rect(currentBuildingX, currentBuildingY, currentBuildingWidth, currentBuildingHeight);
    //print("current building Height:   "+currentBuildingHeight+'\n');
    // print("currentNumberWindowsVert:   "+currentNumberWindowsVert+'\n');
    // print("floors:   "+currentNumberWindowsVert+'\n');
    int arg = PApplet.parseInt(currentNumberWindowsVert-1);

    for (int v = -PApplet.parseInt(currentNumberWindowsVert+1); v > 0; v = v-1) {
      for (int i = PApplet.parseInt(currentNumberWindowsHoriz-1); i > 0; i = i-1) {
        boolean lightsOn = random(1) > .5f;

        //print("lights:   "+lightsOn+'\n');
        if (lightsOn==true) {
          fill(0xffFFF79B, blocksBuilt*10 );
          noStroke();
          rect(currentBuildingX+i*5, currentBuildingY-v*8, 3, -4);
        }
      }
    }
  }
  // so so important:
  blocksBuilt ++;
}




// Drop in some stars! You really ought to write the stars to an array, and slowly delete them. This is wicked hacky.
public void paintStars() {
  float sparkle = PApplet.parseInt(second()+random(200));

  if (sparkle % 9 == 0) {

    textSize(random(1, 10));
    fill(0xffFFFFFF);
    for (int i = 0; i < 1; i++) {
      text("*", random(width), random(30, cityGround));
    }
    textSize(random(100, 300));
    fill(0xff233750);

    //    for (int i = 0; i < 20; i++) {
    //      text("*", random(0, moonLeft-moonDiameter), random(0, 100));
    //    }
    //
    //    for (int i = 0; i < 20; i++) {
    //      text("*", random(moonRight+moonDiameter, width), random(0, 100));
    //    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "skyline" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
