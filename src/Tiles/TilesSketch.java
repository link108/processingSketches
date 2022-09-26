package Tiles;

import Ball.Ball;
import Pixels.PixelMapTest;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TilesSketch extends PApplet{

  public static int numberOfTiles = 10;
  public PFont f;
  public boolean initialRender = true;
  public int iteration = 0;

  private List<List<Tile>> tiles;

  public void setupTiles() {
    tiles = new ArrayList<>();
    ArrayList<Tile> rowTiles;
    for (int i = 0; i < numberOfTiles; i++) {
      rowTiles = new ArrayList<>();
      tiles.add(rowTiles);
      for (int j = 0; j < numberOfTiles; j++) {
        rowTiles.add(new Tile(this, i, j, Tile.side));
      }
    }
  }
  public void setup() {
    f = createFont("Arial",12,true);
    textFont(f);
  }

  public void settings(){
    int side = numberOfTiles * Tile.side;
    size(side, side);
    setupTiles();
  }

  public void draw(){
    //background(64);
    if(initialRender == true) {
      background(255);
      initialRender = false;
    }
    Tile tile;
    for (int i = 0; i < numberOfTiles; i++) {
      for (int j = 0; j < numberOfTiles; j++) {
        tile = tiles.get(i).get(j);
        tile.render();
      }
    }
    iteration++;
    int maxIterations = 25;
    if(iteration > maxIterations) {
      try {
        TimeUnit.SECONDS.sleep(30);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  public static void main(String[] args){
    TilesSketch tilesSketch = new TilesSketch();
    String[] processingArgs = {tilesSketch.getClass().toString()};
    PApplet.runSketch(processingArgs, tilesSketch);
  }
}
