package Tiles;

import Colors.Color;
import Colors.ColorPallete;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tile {

  public PApplet sketch;
  public static int side = 64;

  public int maxPixelsPerRender = 20;
  public int minPixelsPerRender = 10;

  // row and col are the tile's location
  public int row;
  public int col;
  // x and y are the tile's 0 positions in the overall window
  public float x;
  public float y;
  public float pixelSize;
  public int pixelsPerSide = 8;
  public static Random rand = new Random();
  public static int tileBuffer = 1;
  private List<List<TilesPixel>> tilePixels;
  public int minPixelBounndary = tileBuffer;
  public int maxPixelBounndary = pixelsPerSide - tileBuffer;
  private ColorPallete colorPallete;
  public int backgroundColor;
  public int fillColor;
  private TileFiller tileFiller;

  public boolean initialRender = true;

  public Tile(PApplet sketch, int i, int j, int side, ColorPallete colorPallete, TileFiller tileFiller){
    this.sketch = sketch;
    this.row = i;
    this.col = j;
    this.side = side;
    this.x = i * side;
    this.y = j * side;
    this.pixelSize = side / pixelsPerSide;
    this.colorPallete = colorPallete;
    List<Color> colors = colorPallete.getMultipleRandomColors(2);
    this.backgroundColor = colors.get(0).toColor();
    this.fillColor = colors.get(1).toColor();
    this.tileFiller = tileFiller;
    setupPixels();
  }

  public void setupPixels() {
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
        rowTilePixels.add(new TilesPixel(this.sketch, this, row, col, xPos,  yPos, pixelSize, fillColor));
      }
    }
  }

  public TilesPixel getPixel(int row, int col) {
    return tilePixels.get(row).get(col);
  }

  public boolean isInBounds(int check) {
    System.out.println("min pixel boundary: " + minPixelBounndary);
    System.out.println("max pixel boundary: " + maxPixelBounndary);
    if (check < minPixelBounndary || check >= maxPixelBounndary) {
      return false;
    }
    return true;

  }

  public TilesPixel getRandomPixelInBoundary(){
    int x = rand.nextInt(maxPixelBounndary - minPixelBounndary) + minPixelBounndary;
    int y = rand.nextInt(maxPixelBounndary - minPixelBounndary) + minPixelBounndary;
    System.out.println("getRandomPixelInBoundary, x: " + x + ", y: " + y);
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