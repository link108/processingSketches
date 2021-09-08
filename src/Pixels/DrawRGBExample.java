package Pixels;

import processing.core.PApplet;

public class DrawRGBExample extends PApplet {

  public void settings(){
    size(400, 400);
  }

  public void draw(){
    noStroke();
    colorMode(RGB, 400);
    for (int i = 0; i < 400; i++) {
      for (int j = 0; j < 400; j++) {
        stroke(i, j, 0);
        point(i, j);
      }
    }

  }


  public static void main(String[] args){
    DrawRGBExample drawRGBExample = new DrawRGBExample();
    String[] processingArgs = {drawRGBExample.getClass().toString()};
    PApplet.runSketch(processingArgs, drawRGBExample);
  }
}
