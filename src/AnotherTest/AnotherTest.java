package AnotherTest;

import processing.core.PApplet;

import java.util.ArrayList;

public class AnotherTest extends PApplet{

  int[] colorPalette;
  ArrayList<Ellipse> ellipses;
//  ArrayList<PVector> ellipses;

  int ellipseRadius = 50;
  int colorIndex = 0;
  int frameCounter = 0;
  boolean updateColors = true;

  public void setup(){
    ellipses = new ArrayList<>();
    colorPalette = new int[] {
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
      if(ellipses.size() % colorPalette.length == 0) {
        colorIndex = (colorIndex + 1) % colorPalette.length;
      }
      background(64);
//      for (PVector ellipsePos : ellipses) {
      for(int i = 0; i < ellipses.size(); i++) {
//      for (Ellipse ellipse : ellipses) {
        Ellipse ellipse = ellipses.get(i);
        if(ellipse.toDelete) {
          ellipses.remove(i);
        } else {
          ellipse.draw();
        }
      }
    }
    updateColors = false;

    if(mousePressed) {
      Ellipse ellipse = new Ellipse(mouseX, mouseY, ellipseRadius, colorIndex, this);
      fill(colorPalette[colorIndex]);
      ellipse(mouseX, mouseY, ellipseRadius, ellipseRadius);
      colorIndex = (colorIndex + 1) % colorPalette.length;
      ellipses.add(ellipse);
//      ellipses.add(new PVector(mouseX, mouseY));
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

class Ellipse extends PApplet {
  float x;
  float y;
  float radius;
  int colorIndex;
  AnotherTest anotherTest;
  int radiusDelta = 10;
  int numSections;
  int sectionCounter = 1;
  boolean expanding = true;
  boolean toDelete = false;
  float[] radiusHistory = new float[100];
  int radiusHistorySize = 0;

  public Ellipse(float x, float y, float radius, int colorIndex, AnotherTest anotherTest) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.colorIndex = colorIndex;
    this.anotherTest = anotherTest;
    this.numSections = floor(radius/ radiusDelta);
    draw();
  }

  public void draw(){
    if (!toDelete) {
      if (expanding) {
        float radiusToDraw = radius;
        for (int i = 0; i < sectionCounter; i++) {
          anotherTest.fill(anotherTest.colorPalette[colorIndex]);
          anotherTest.ellipse(x, y, radiusToDraw, radiusToDraw);
          colorIndex = (colorIndex + 1) % anotherTest.colorPalette.length;
          radiusToDraw -= radiusDelta;
        }
        sectionCounter++;
        if (sectionCounter == numSections) {
          expanding = false;
        }
      } else {
        float radiusToDraw = radius - ((numSections - sectionCounter) * radiusDelta);
        for (int i = 0; i < sectionCounter; i++) {
          radiusHistory[radiusHistorySize] = radiusToDraw;
          radiusHistorySize++;
          anotherTest.fill(anotherTest.colorPalette[colorIndex]);
          anotherTest.ellipse(x, y, radiusToDraw, radiusToDraw);
          colorIndex = (colorIndex + 1) % anotherTest.colorPalette.length;
          radiusToDraw -= radiusDelta;
        }
        sectionCounter--;
        if (sectionCounter == 0) {
          toDelete = true;
        }
        for(int i=0; i<radiusHistorySize; i++) {
          System.out.printf("%f\n", radiusHistory[i]);
        }
      }
    }
  }

}