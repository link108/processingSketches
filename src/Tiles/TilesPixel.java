package Tiles;

import processing.core.PApplet;

public class TilesPixel {

  private PApplet sketch;
  private int i;
  private int j;
  private float x;
  private float y;
  private float pixelSize;

  public TilesPixel(PApplet sketch, int i, int j, float x, float y, float pixelSize){
    this.sketch = sketch;
    this.i = i;
    this.j = j;
    this.x = x;
    this.y = y;
    this.pixelSize = pixelSize;
  }

  public void render(){
    sketch.fill(0);
    //float pixelX = x + startX * pixelSize;
    //float pixelY = y + startY * pixelSize;
    sketch.rect(x, y, pixelSize, pixelSize);
  }


}
