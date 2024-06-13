package Tiles;

import Colors.Color;
import Colors.ColorPallete;
import processing.core.PApplet;

import java.util.List;
import java.util.Random;

public class RandomTileFiller implements TileFiller {

  public int maxPixelsPerRender = 20;
  public int minPixelsPerRender = 10;
  public ColorPallete colorPallete;
  public int backgroundColor;
  public int fillColor;

  private PApplet sketch;
  public static Random rand = new Random();


  public RandomTileFiller(PApplet sketch, ColorPallete colorPallete) {
    this.sketch = sketch;
    this.colorPallete = colorPallete;
    List<Color> colors = colorPallete.getMultipleRandomColors(2);
    this.backgroundColor = colors.get(0).toColor();
    this.fillColor = colors.get(1).toColor();
  }

  public int getFillColor() {
    return fillColor;
  }

  public int getBackgroundColor() {
    return backgroundColor;
  }

  public void setupTile(Tile tile) {
    tile.clear();
    sketch.stroke(0);
    sketch.fill(backgroundColor);
    sketch.rect(tile.x, tile.y, tile.side, tile.side);

    int stepsPerRender = rand.nextInt(maxPixelsPerRender - minPixelsPerRender) + minPixelsPerRender;
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
