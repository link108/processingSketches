package Tiles;

import Colors.ColorPallete;
import Colors.TestColorPallete;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.List;

public class TilesSketch extends PApplet{

  public static int numberOfTiles = 10;
  public PFont f;

  private boolean initialDraw = true;
  private List<List<Tile>> tiles;
  private ColorPallete colorPallete;
  private TileFiller tileFiller;

  public void setupTiles() {
    tileFiller = new RandomTileFiller(this);
    tiles = new ArrayList<>();
    ArrayList<Tile> rowTiles;
    for (int row = 0; row < numberOfTiles; row++) {
      rowTiles = new ArrayList<>();
      tiles.add(rowTiles);
      for (int col = 0; col < numberOfTiles; col++) {
        rowTiles.add(new Tile(this, row, col, Tile.side, colorPallete, tileFiller));
      }
    }
  }

  public void setup() {
    f = createFont("Arial",12,true);
    textFont(f);
  }

  public void settings(){
    int side = numberOfTiles * Tile.side;
    colorPallete = new TestColorPallete();
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
    background(255);
    System.out.println("generating tiles");
    Tile tile;
    for (int i = 0; i < numberOfTiles; i++) {
      for (int j = 0; j < numberOfTiles; j++) {
        tile = tiles.get(i).get(j);
        tile.render();
      }
    }
  }

  public static void main(String[] args){
    TilesSketch tilesSketch = new TilesSketch();
    String[] processingArgs = {tilesSketch.getClass().toString()};
    PApplet.runSketch(processingArgs, tilesSketch);
  }
}
