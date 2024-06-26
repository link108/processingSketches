package Colors;

import processing.core.PApplet;

import java.util.*;
import java.util.stream.Collectors;

public class TestColorPallete extends PApplet implements ColorPallete {

  public final Color RED = new Color(255, 0, 0);
  public final Color GREEN = new Color(0, 255, 0);
  public final Color BLUE = new  Color(0, 0, 255);
  public final Color YELLOW = new Color(255, 255, 0);
  public final Color PURPLE = new Color(255, 0, 255);
  public final Color CYAN = new Color(0, 255, 255);


  private final List<Color> colorPallete;
  private Random randomGenerator = new Random();

  public TestColorPallete() {
    colorPallete = Arrays.asList(RED, GREEN, BLUE, YELLOW, PURPLE, CYAN);
  }

  @Override
  public List<Color> getPallete() {
    return colorPallete;
  }

  @Override
  public Color getRandomColor() {
    return colorPallete.get(randomGenerator.nextInt(colorPallete.size()));
  }

  public List<Color> getMultipleRandomColors(int numColors) {
    List<Color> toReturn = new ArrayList<>();
    Collections.shuffle(colorPallete);
    return colorPallete.stream().limit(numColors).collect(Collectors.toList());
  }
}

