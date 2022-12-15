package Tiles;

import processing.core.PApplet;

import java.util.Random;

public class RandomTileFiller implements TileFiller {

  private PApplet sketch;
  public static Random rand = new Random();


  public RandomTileFiller(PApplet sketch) {
    this.sketch = sketch;
  }

  public void setupTile(Tile tile) {
    tile.clear();
    sketch.stroke(0);
    sketch.fill(tile.backgroundColor);
    sketch.rect(tile.x, tile.y, tile.side, tile.side);

    int stepsPerRender = rand.nextInt(tile.maxPixelsPerRender - tile.minPixelsPerRender) + tile.minPixelsPerRender;
    for(int i=0; i < stepsPerRender; i++) {
      TilesPixel tilePixel = tile.getRandomPixelInBoundary();
      tilePixel.setFilled();
    }
  }

  public void render(Tile tile) {
      for(int i=0; i< tile.pixelsPerSide; i++) {
        for(int j=0; j < tile.pixelsPerSide; j++) {
          tile.getPixel(i, j).render();
        }
      }
      tile.debugPrint();
  }
}
