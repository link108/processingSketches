package Tiles;

import Colors.ColorPallete;
import Colors.TestColorPallete;
import processing.core.PApplet;
import processing.core.PFont;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlowersSketch extends PApplet{

  public static int numberOfTiles = 1;
  public static int pixelSize = 10;
  public static int pixelsPerSide = 60;
  public PFont f;

  private boolean initialDraw = true;
  private List<Tile> tiles;

  private Random random = new Random();

  public void setupTiles() {
    background(255);

    tiles = new ArrayList<>();
    ColorPallete colorPallete = new TestColorPallete();
    FlowerCreator flowerCreator = new FlowerCreator(colorPallete);

    tiles.add(new Tile(this, 0, 0, pixelSize, pixelsPerSide, flowerCreator));
  }

  public void setup() {
    f = createFont("Arial",12,true);
    textFont(f);
    setupTiles();
  }

  // needed to define size and smooth values for sketches
  public void settings(){
    int side = numberOfTiles * pixelSize * pixelsPerSide;
    System.out.println("Settings -> side = " + side);
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
    for (Tile tile : tiles) {
      tile.render();
    }
  }

  public static void main(String[] args){
    FlowersSketch tilesSketch = new FlowersSketch();
    String[] processingArgs = {tilesSketch.getClass().toString()};
    PApplet.runSketch(processingArgs, tilesSketch);
  }
}
