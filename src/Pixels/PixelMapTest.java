package Pixels;

import Colors.TestColorPallete;
import processing.core.PApplet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class PixelMapTest extends PApplet{

  private PixelMap pixelMap;
  private int colorMode;
  private int pixelSize;
  private int height;
  private int width;
  private int color;
  private int colorValue;
  private String url;
  private TestColorPallete testColorPallete;
  private Random random;

  public PixelMapFiller pixelMapFiller = (col, row) -> {
    return testColorPallete.getRandomColor().toColor();
  };

  public void settings(){
    height = 1600;
    width = 1600;
    pixelSize = 16;
    colorMode = HSB;
    colorValue = getValue();
    size(width, height);
    pixelMap = new PixelMap(this, height, width, pixelSize);
    testColorPallete = new TestColorPallete();
    random = new Random();
  }

  public int getValue() {
    return colorMode == HSB ? 400 : 0;
  }

  public void draw(){
    //colorMode(RGB, 400);
    colorMode(HSB, 400);
    noStroke();   // Don't draw a stroke around shapes
    //fillMap();
    pixelMap.fillMap(pixelMapFiller);
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
