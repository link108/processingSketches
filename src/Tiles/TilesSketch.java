package Tiles;

import Colors.ColorPallete;
import Colors.TestColorPallete;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.List;

public class TilesSketch extends PApplet{

  public static int numberOfTiles = 1;
  public PFont f;

  private boolean initialDraw = true;
  private List<Tile> tiles;
  private ColorPallete colorPallete;
  private TileFiller tileFiller;

  public void setupTiles() {
    background(255);
    tileFiller = new RandomTileFiller(this);
    tiles = new ArrayList<>();
    for (int row = 0; row < numberOfTiles; row++) {
      for (int col = 0; col < numberOfTiles; col++) {
        tiles.add(new Tile(this, row, col, Tile.side, colorPallete, tileFiller));
      }
    }
  }

  public void setup() {
    f = createFont("Arial",12,true);
    textFont(f);
  }

  // needed to define size and smooth values for sketches
  public void settings(){
    int side = numberOfTiles * Tile.side;
    colorPallete = new TestColorPallete();
    size(side, side);
  }

  public void draw(){
    if(initialDraw){
      render();
      initialDraw = false;
    }
    if(mousePressed) {
      render();
    }
  }

  public void render() {
    setupTiles();
    System.out.printf("generating %d tiles\n", tiles.size());
    int index = 0;
    for (Tile tile : tiles) {
      if(index == 10) {
        System.out.println("Here");
      }
      tile.render();
      System.out.println("Finished calling render on tile: " + index);
      index += 1;
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args){
    TilesSketch tilesSketch = new TilesSketch();
    String[] processingArgs = {tilesSketch.getClass().toString()};
    PApplet.runSketch(processingArgs, tilesSketch);
  }
}
