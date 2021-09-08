package Pixels;

import processing.core.PApplet;

public class PixelMap {

  private PApplet sketch;


  private int height;
  private int width;
  private int pixelSize;

  public PixelMap(PApplet sketch, int height, int width, int pixelSize){
    this.sketch = sketch;
    this.height = height;
    this.width = width;
    this.pixelSize = pixelSize;
  }

  public void setupMap() {
    sketch.size(height, width);
  }




}
