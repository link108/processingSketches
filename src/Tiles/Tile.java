package Tiles;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.List;
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
  private int maxPixelsPerRender = 16;
  private int minPixelsPerRender = 8;
  private List<List<TilesPixel>> tilePixels;
  private int minPixelBounndary = tileBuffer;
  private int maxPixelBounndary = pixelsPerSide - tileBuffer;

  public boolean initialRender = true;

  public Tile(PApplet sketch, int i, int j, int side){
    this.sketch = sketch;
    this.i = i;
    this.j = j;
    this.x = i * side;
    this.y = j * side;
    this.pixelSize = side / pixelsPerSide;

    tilePixels = new ArrayList<>();
    List rowTilePixels;
    float yPos;
    float xPos;

    for(int row=0; row < pixelsPerSide; row++) {
      rowTilePixels = new ArrayList<>();
      tilePixels.add(rowTilePixels);
      for(int col=0; col < pixelsPerSide; col++) {
        xPos = x + row * pixelsPerSide;
        yPos = y + col * pixelsPerSide;
        rowTilePixels.add(new TilesPixel(this.sketch, row, col, xPos,  yPos, pixelSize));
      }

    }
  }

  public void step(){
    int startX = rand.nextInt(maxPixelBounndary - minPixelBounndary) + minPixelBounndary;
    int startY = rand.nextInt(maxPixelBounndary - minPixelBounndary) + minPixelBounndary;
    tilePixels.get(startX).get(startY).render();
  }

  public void render(){
    sketch.fill(255);
    sketch.rect(x, y, side, side);
    int stepsPerRender = rand.nextInt(maxPixelsPerRender - minPixelsPerRender) + minPixelsPerRender;
    System.out.println("Steps per render: " + stepsPerRender);
    for(int i=0; i < stepsPerRender; i++) {
      step();
    }
  }
}