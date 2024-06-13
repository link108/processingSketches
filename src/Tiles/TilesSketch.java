package Tiles;

import Colors.ColorPallete;
import Colors.TestColorPallete;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.List;

public class TilesSketch extends PApplet{

  public static int numberOfTiles = 10;
  public static int pixelSize = 8;
  public static int pixelsPerSide = 8;
  public PFont f;

  private boolean initialDraw = true;
  private List<Tile> tiles;

  public void setupTiles() {
    background(255);
    tiles = new ArrayList<>();
    for (int row = 0; row < numberOfTiles; row++) {
      for (int col = 0; col < numberOfTiles; col++) {
        ColorPallete colorPallete = new TestColorPallete();
        TileFiller tileFiller = new RandomTileFiller(this, colorPallete);
        tiles.add(new Tile(this, row, col, pixelSize, pixelsPerSide, tileFiller));
      }
    }
  }

  public void setup() {
    f = createFont("Arial",12,true);
    textFont(f);
  }

  // needed to define size and smooth values for sketches
  public void settings(){
    int side = numberOfTiles * pixelSize * pixelsPerSide;
    size(side, side);
  }

  public void draw(){
    if(initialDraw == true){
      render();
      initialDraw = false;
    }
    if(mousePressed) {
      render();
    }
  }

  public void render() {
    setupTiles();
    System.out.println("generating tiles");
    for (Tile tile : tiles) {
      tile.render();
    }
  }

  public static void main(String[] args){
    TilesSketch tilesSketch = new TilesSketch();
    String[] processingArgs = {tilesSketch.getClass().toString()};
    PApplet.runSketch(processingArgs, tilesSketch);
  }
}
