package Pixels;
import processing.core.PApplet;

public class DrawHSBExample extends PApplet {

  public void settings(){
    size(400, 400);
  }

  public void draw(){
    noStroke();
    colorMode(HSB, 400);
    for (int i = 0; i < 400; i++) {
      for (int j = 0; j < 400; j++) {
        stroke(i, j, 400);
        point(i, j);
      }
    }
  }

  public static void main(String[] args){
    DrawHSBExample drawHSBExample = new DrawHSBExample();
    String[] processingArgs = {drawHSBExample.getClass().toString()};
    PApplet.runSketch(processingArgs, drawHSBExample);
  }
}
