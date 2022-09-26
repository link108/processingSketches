package Tiles;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.Random;

public class Tile {

  private PApplet sketch;
  public static int side = 64;

  private int i;
  private int j;
  private float x;
  private float y;
  private float pixelSize;
  private int pixelsPerSide = 8;
  public static Random rand = new Random();
  public static int tileBuffer = 1;

  public boolean initialRender = true;

  public Tile(PApplet sketch, int i, int j, int side){
    this.sketch = sketch;
    this.i = i;
    this.j = j;
    this.x = i * side;
    this.y = j * side;
    this.pixelSize = side / pixelsPerSide;
  }

  private void fillPixel(int startX, int startY) {
    sketch.fill(0);
    float pixelX = x + startX * pixelSize;
    float pixelY = y + startY * pixelSize;
    sketch.rect(pixelX, pixelY, pixelSize, pixelSize);
  }

  public void step(){
    int max = pixelsPerSide - tileBuffer;
    int min = tileBuffer;
    int startX = rand.nextInt(max - min) + min;
    int startY = rand.nextInt(max - min) + min;
    System.out.println("StartX: " + startX + ", startY: " + startY);
    fillPixel(startX, startY);

    //x += xSpeed;
    //if(x < 0 || x > sketch.width){
    //  xSpeed *= -1;
    //}
    //
    //y += ySpeed;
    //if(y < 0 || y > sketch.height){
    //  ySpeed *= -1;
    //}
  }

  public void render(){
    if(initialRender == true)  {
      sketch.fill(255);
      sketch.rect(x, y, side, side);
      initialRender = false;
    }

    step();

    //sketch.fill(0);
    //sketch.text("i: " + i + ", j: " + j, x + 2, y + side / 4);
    //sketch.text("position: " + (TilesSketch.numberOfTiles * j + i + 1), x + 2, y + side / 2);
  }
}