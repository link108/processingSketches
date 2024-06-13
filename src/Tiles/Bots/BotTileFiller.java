package Tiles.Bots;

import Tiles.Tile;
import Tiles.TileFiller;
import processing.core.PApplet;
import Colors.Color;

import java.util.Random;

public class BotTileFiller implements TileFiller {

  private PApplet sketch;
  public static Random rand = new Random();

  public BotTileFiller(PApplet sketch) {
    this.sketch = sketch;
  }

  public void setupTile(Tile tile) {
//    tile.clear();
//    sketch.stroke(0);
//    sketch.fill(tile.backgroundColor);
//    sketch.rect(tile.x, tile.y, tile.side, tile.side);
  }

  public void render(Tile tile) {
//    tile.render();
  }

  public int getBackgroundColor() {
    return sketch.color(255, 0, 0);
  }

  public int getFillColor() {
    return 0;
  }

}
