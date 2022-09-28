package Tiles;

import processing.core.PApplet;

public class TilesPixel {

  private PApplet sketch;
  private int i;
  private int j;
  private float x;
  private float y;
  private float pixelSize;
  public boolean filled;

  public TilesPixel(PApplet sketch, int i, int j, float x, float y, float pixelSize){
    this.sketch = sketch;
    this.i = i;
    this.j = j;
    this.x = x;
    this.y = y;
    this.pixelSize = pixelSize;
    this.filled = false;
  }

  public void clear() {
    filled = false;
  }

  public void setFilled() {
    filled = true;
  }

  public void render(){
    if(filled) {
      sketch.fill(0);
      sketch.rect(x, y, pixelSize, pixelSize);
    }
  }

}
