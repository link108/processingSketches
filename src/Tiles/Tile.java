package Tiles;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tile {

  public PApplet sketch;
  public static int side;


  // row and col are the tile's location
  public int row;
  public int col;
  // x and y are the tile's 0 positions in the overall window
  public float x;
  public float y;
  public float pixelSize;
  public int pixelsPerSide;
  public static Random rand = new Random();
  public static int tileBuffer = 1;
  private List<List<TilesPixel>> tilePixels;
  public int minPixelBounndary = tileBuffer;
  public int maxPixelBounndary;
  private TileFiller tileFiller;

  public boolean initialRender = true;

  public Tile(PApplet sketch, int i, int j, int pixelSize, int pixelsPerSide, TileFiller tileFiller){
    this.sketch = sketch;
    this.row = i;
    this.col = j;
    this.pixelsPerSide = pixelsPerSide;
    this.pixelSize = pixelSize;
    this.side = pixelsPerSide * pixelSize;
    this.x = i * side;
    this.y = j * side;
//    this.pixelSize = sideLength / pixelsPerSide;
    this.tileFiller = tileFiller;
    this.maxPixelBounndary = pixelsPerSide - tileBuffer;
    setupPixels();
  }

  public void setupPixels() {
    tilePixels = new ArrayList<>();
    for(int row=0; row < pixelsPerSide; row++) {
      List rowTilePixels = new ArrayList<>();
      tilePixels.add(rowTilePixels);
      for(int col=0; col < pixelsPerSide; col++) {
        float xPos = x + col * pixelSize;
        float yPos = y + row * pixelSize;
        rowTilePixels.add(new TilesPixel(this.sketch, this, row, col, xPos,  yPos, pixelSize, tileFiller.getFillColor()));
      }
    }
  }

  public TilesPixel getPixel(int row, int col) {
    return tilePixels.get(row).get(col);
  }

  public boolean isInBounds(int check) {
    if (check < minPixelBounndary || check >= maxPixelBounndary) {
      return false;
    }
    return true;

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
    sketch.text("i: " + row + ", j: " + col, x + 2, y + this.side - 2);
  }

  public void initialDraw() {
  }

  public void render(){
    tileFiller.setupTile(this);
    tileFiller.render(this);
    debugPrint();
  }

  public void debugPrint() {
    //printPosition();
    System.out.println("DEBUG PRINT");
    System.out.println("Tile(i: " + row + ", j: " + col + ")");
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