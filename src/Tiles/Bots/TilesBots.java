package Tiles.Bots;

import Colors.ColorPallete;
import Colors.TestColorPallete;
import Tiles.RandomTileFiller;
import Tiles.Tile;
import Tiles.TileFiller;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.List;

public class TilesBots extends PApplet{

  public static int numberOfTiles = 10;
  public PFont f;

  private boolean initialDraw = true;
  private List<Tile> tiles;
  private ColorPallete colorPallete;
  private TileFiller tileFiller;

  public void setupTiles() {
    background(255);
    tileFiller = new BotTileFiller(this);
    tiles = new ArrayList<>();
    for (int row = 0; row < numberOfTiles; row++) {
      for (int col = 0; col < numberOfTiles; col++) {
        tiles.add(new TileBot(this, row, col, Tile.side, colorPallete, tileFiller));
      }
    }
    for (Tile tile : tiles) {
      System.out.print("tile");
      tile.initialDraw();
    }
    System.out.println("hi");
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
      setupTiles();
//      render();
      System.out.print("hi");
      initialDraw = false;
    }
    System.out.print("hi");
    if(mousePressed) {
      render();
    }
  }

  public void render() {
    System.out.println("generating tiles");
    for (Tile tile : tiles) {
      tile.render();
    }
  }

  public static void main(String[] args){
    TilesBots tilesBots = new TilesBots();
    String[] processingArgs = {tilesBots.getClass().toString()};
    PApplet.runSketch(processingArgs, tilesBots);
  }
}
