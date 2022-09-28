package Colors;

import java.util.List;

public interface ColorPallete {
  public List<Color> getPallete();
  public int getRandomColor();
  public List<Color> getMultipleRandomColors(int numColors);
}
