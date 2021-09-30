package Colors;

import processing.core.PApplet;

import java.util.Random;

public class TestColorPallete extends PApplet implements ColorPallete {

  public final int RED = color(255, 0, 0);
  public final int GREEN = color(0, 255, 0);
  public final int BLUE = color(0, 0, 255);
  public final int YELLOW = color(255, 255, 0);

  private final int[] colorPallete = {RED, GREEN, BLUE, YELLOW};
  private final int colorPalleteSize = colorPallete.length;
  private Random randomGenerator = new Random();

  @Override
  public int[] getPallete() {
    return colorPallete;
  }

  @Override
  public int getRandomColor() {
    return colorPallete[randomGenerator.nextInt(colorPalleteSize)];
  }
}
