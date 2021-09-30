package Pixels;

import processing.core.PApplet;

import java.util.HashMap;


public class PixelMap {

  private PApplet sketch;

  private int height;
  private int width;
  private int pixelSize;
  private int numColums;
  private int numRows;
  private int[][] pixels;

  public PixelMap(PApplet sketch, int height, int width, int pixelSize){
    this.sketch = sketch;
    this.height = height;
    this.width = width;
    this.pixelSize = pixelSize;
    this.numColums = width / pixelSize;
    this.numRows = height / pixelSize;
  }

  public void setupMap() {
    sketch.size(height, width);
    if (height % pixelSize != 0) {
      System.out.println("height not evenly divisible by pixelSize");
    } else if (width % pixelSize != 0) {
      System.out.println("width not evenly divisible by pixelSize");
    }

  }

  public void fillMap(PixelMapFiller pixelMapFiller) {
    int color;
    for (int i = 0; i < numColums; i += 1) {
      for (int j = 0; j < numRows; j += 1) {
        color = pixelMapFiller.getColor(i, j);
        sketch.fill(color);  // Use color variable 'c' as fill color
        sketch.rect(i * pixelSize, j * pixelSize, pixelSize, pixelSize);  // Draw left rect
      }
    }
  }

}
