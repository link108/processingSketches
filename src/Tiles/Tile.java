package Tiles;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static processing.app.Language.text;

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
    this.side = side;
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
        xPos = x + col * pixelsPerSide;
        yPos = y + row * pixelsPerSide;
        rowTilePixels.add(new TilesPixel(this.sketch, row, col, xPos,  yPos, pixelSize));
      }

    }
  }

  public TilesPixel getPixel(int x, int y) {
    return tilePixels.get(x).get(y);
  }

  public TilesPixel getRandomPixelInBoundary(){
    int x = rand.nextInt(maxPixelBounndary - minPixelBounndary) + minPixelBounndary;
    int y = rand.nextInt(maxPixelBounndary - minPixelBounndary) + minPixelBounndary;
    return getPixel(x, y);
  }

  public void clear() {
    for(int i=0; i < pixelsPerSide; i++) {
      for(int j=0; j < pixelsPerSide; j++) {
        getPixel(i, j).clear();
      }
    }
  }

  public void printPosition() {
    sketch.text("i: " + i + ", j: " + j, x + 2, y + this.side - 2);
  }



  public void render(){
    clear();
    sketch.fill(255);
    sketch.rect(x, y, side, side);

    int stepsPerRender = rand.nextInt(maxPixelsPerRender - minPixelsPerRender) + minPixelsPerRender;
    for(int i=0; i < stepsPerRender; i++) {
      TilesPixel tilePixel = getRandomPixelInBoundary();
      tilePixel.setFilled();
    }

    for(int i=0; i< pixelsPerSide; i++) {
      for(int j=0; j < pixelsPerSide; j++) {
        getPixel(i, j).render();
      }
    }
    debugPrint();
  }

  public void debugPrint() {
    printPosition();
    System.out.println("DEBUG PRINT");
    System.out.println("Tile(i: " + i + ", j: " + j + ")");
    System.out.println("#################");
    String row;
    TilesPixel tilesPixel;
    for(int i=0; i < pixelsPerSide; i++) {
      row = "|";
      for(int j=0; j < pixelsPerSide; j++) {
        tilesPixel = getPixel(i, j);
        row += tilesPixel.filled ? "X" : "_";
      }
      System.out.println(row + "|  - row: " + i);
    }
    System.out.println("#################");
  }
}