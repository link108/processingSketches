package Tiles;

import processing.core.PApplet;

public class TilesPixel {

  private PApplet sketch;
  public int i;
  public int j;
  public float x;
  public float y;
  private float pixelSize;
  public boolean filled;
  public int color;
  private Tile tile;

//  public TilesPixel(PApplet sketch, Tile tile, int i, int j, float x, float y, float pixelSize, int color){
  public TilesPixel(PApplet sketch, Tile tile, int i, int j, float x, float y, float pixelSize) {
    this.sketch = sketch;
    this.tile = tile;
    this.i = i;
    this.j = j;
    this.x = x;
    this.y = y;
    this.pixelSize = pixelSize;
    this.filled = false;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public void clear() {
    filled = false;
  }

  public void setFilled() {
    filled = true;
  }

  // print border
  public void renderPixelBorder() {
    sketch.line(x, y, x + pixelSize, y);
    sketch.line(x, y + pixelSize, x + pixelSize, y + pixelSize);
    sketch.line(x + pixelSize, y, x + pixelSize, y + pixelSize);
    sketch.line(x, y, x, y + pixelSize);
    System.out.println("Printing tile pixel: x: " + x + ", y: " + y + ", pixelSize: " + pixelSize);
  }

  // print border between filled and unfilled
  public void printBorder() {
    if(!filled)
      return;

    sketch.stroke(0);
    if(i - 1 >= 0 && !tile.getPixel(i - 1, j).filled) {
      // print up border
      sketch.line(x, y, x + pixelSize, y);
    }
    if(i + 1 <= tile.pixelsPerSide - 1 && !tile.getPixel(i + 1, j).filled) {
      // print down border
      sketch.line(x, y + pixelSize, x + pixelSize, y + pixelSize);
    }
    if(j + 1 <= tile.pixelsPerSide - 1 && !tile.getPixel(i, j + 1).filled) {
      // print right border
      sketch.line(x + pixelSize, y, x + pixelSize, y + pixelSize);
    }
    if(j - 1 >= 0 && !tile.getPixel(i, j - 1).filled) {
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
