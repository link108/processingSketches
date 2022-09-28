package Colors;

import processing.core.PApplet;

public class Color extends PApplet {
  public int red;
  public int blue;
  public int green;
  public int grey;
  public float hue;
  public float saturation;
  public float brightness;
  public int alpha;
  public float alpohaFloat;

  public Color(int r, int g, int b) {
    red = r;
    green = g;
    blue = b;
  }

  public int toColor() {
    return color(red, green, blue);
  }

}
