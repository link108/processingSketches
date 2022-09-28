package Colors;

import processing.core.PApplet;

import java.util.*;
import java.util.stream.Collectors;

public class TestColorPallete extends PApplet implements ColorPallete {

  public final Color RED = new Color(255, 0, 0);
  public final Color GREEN = new Color(0, 255, 0);
  public final Color BLUE = new  Color(0, 0, 255);
  public final Color YELLOW = new Color(255, 255, 0);

  private final List<Color> colorPallete;
  private Random randomGenerator = new Random();

  public TestColorPallete() {
    colorPallete = new ArrayList<>();
    colorPallete.add(RED);
    colorPallete.add(GREEN);
    colorPallete.add(BLUE);
    colorPallete.add(YELLOW);
  }

  @Override
  public List<Color> getPallete() {
    return colorPallete;
  }

  @Override
  public int getRandomColor() {
    return colorPallete.get(randomGenerator.nextInt(colorPallete.size())).toColor();
  }

  public List<Color> getMultipleRandomColors(int numColors) {
    List<Color> toReturn = new ArrayList<>();
    Collections.shuffle(colorPallete);
    return colorPallete.stream().limit(numColors).collect(Collectors.toList());
  }
}

