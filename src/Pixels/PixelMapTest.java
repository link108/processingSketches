package Pixels;

import processing.core.PApplet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class PixelMapTest extends PApplet{

  private PixelMap pixelMap;
  private int colorMode;
  private int pixelSize;
  private int height;
  private int width;
  private int color;
  private int colorValue;
  private String url;

  public void settings(){
    height = 1600;
    width = 1600;
    pixelSize = 16;
    colorMode = HSB;
    colorValue = getValue();
    size(width, height);
  }

  public int getValue() {
    return colorMode == HSB ? 400 : 0;
  }

  public void draw(){
    //colorMode(RGB, 400);
    colorMode(HSB, 400);
    noStroke();   // Don't draw a stroke around shapes
    fillMap();
  }

  public void fillMap() {
    for (int i = 0; i < height; i += pixelSize) {
      for (int j = 0; j < width; j += pixelSize) {
        color = color(i, j, colorValue);  // Create a color for 'c'
        fill(color);  // Use color variable 'c' as fill color
        rect(i, j, pixelSize, pixelSize);  // Draw left rect
      }
    }
  }

  public static void main(String[] args){
    PixelMapTest pixelMapTest = new PixelMapTest();
    String[] processingArgs = {pixelMapTest.getClass().toString()};
    PApplet.runSketch(processingArgs, pixelMapTest);
  }
}
