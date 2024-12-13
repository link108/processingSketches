package AnotherTest;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class AnotherTest extends PApplet{

  int[] colors;
  ArrayList<PVector> ellipses;

  int ellipseRadius = 30;
  int colorIndex = 0;
  int frameCounter = 0;
  boolean updateColors = true;

  public void setup(){
    ellipses = new ArrayList<>();
    colors = new int[] {
            color(255, 0, 0),  // Red
            color(0, 255, 0),  // Green
            color(0, 0, 255),  // Blue
            color(255, 255, 0), // Yellow
            color(0, 255, 255)  // Cyan
    };
    background(64);
  }

  public void settings(){
    size(500, 500);
  }

  public void draw(){
    if(updateColors) {
      // Colors do not shift in the below if condition, this makes them shift
      if(ellipses.size() % colors.length == 0) {
        colorIndex = (colorIndex + 1) % colors.length;
      }
      background(64);
      for (PVector ellipsePos : ellipses) {
        fill(colors[colorIndex]);
        ellipse(ellipsePos.x, ellipsePos.y, ellipseRadius, ellipseRadius);
        colorIndex = (colorIndex + 1) % colors.length;
      }
    }
    updateColors = false;

    if(mousePressed) {
      fill(colors[colorIndex]);
      ellipse(mouseX, mouseY, ellipseRadius, ellipseRadius);
      colorIndex = (colorIndex + 1) % colors.length;
      ellipses.add(new PVector(mouseX, mouseY));
    }

    frameCounter++;
    if (frameCounter % 30 == 0) {
      updateColors = true;
    }
  }

  public static void main(String[] args){
    String[] processingArgs = {"AnotherTest.AnotherTest"};
    AnotherTest anotherTest = new AnotherTest();
    PApplet.runSketch(processingArgs, anotherTest);
  }
}
