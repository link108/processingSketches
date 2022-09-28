package Tiles;

import Colors.Color;
import processing.core.PApplet;

public class TilesPixel {

  private PApplet sketch;
  private int i;
  private int j;
  private float x;
  private float y;
  private float pixelSize;
  public boolean filled;
  public int color;
  private Tile tile;

  public TilesPixel(PApplet sketch, Tile tile, int i, int j, float x, float y, float pixelSize, int color){
    this.sketch = sketch;
    this.tile = tile;
    this.i = i;
    this.j = j;
    this.x = x;
    this.y = y;
    this.pixelSize = pixelSize;
    this.filled = false;
    this.color = color;
  }

  public void clear() {
    filled = false;
  }

  public void setFilled() {
    filled = true;
  }

  public void printBorder() {
    if(!filled)
      return;

    sketch.stroke(0);
    if(!tile.getPixel(i - 1, j).filled) {
      // print up border
      sketch.line(x, y, x + pixelSize, y);
    }
    if(!tile.getPixel(i + 1, j).filled) {
      // print down border
      sketch.line(x, y + pixelSize, x + pixelSize, y + pixelSize);
    }
    if(!tile.getPixel(i, j + 1).filled) {
      // print right border
      sketch.line(x + pixelSize, y, x + pixelSize, y + pixelSize);
    }
    if(!tile.getPixel(i, j - 1).filled) {
      // print left border
      sketch.line(x, y, x, y + pixelSize);
    }
  }

  public void render(){
    if(filled) {
      sketch.noStroke();
      sketch.fill(color);
      sketch.rect(x, y, pixelSize, pixelSize);
      printBorder();
    }
  }

}
