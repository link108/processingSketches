package Colors;

import java.util.List;

public interface ColorPallete {
  public List<Color> getPallete();
  public Color getRandomColor();
  public List<Color> getMultipleRandomColors(int numColors);
}
