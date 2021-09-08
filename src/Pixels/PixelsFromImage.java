package Pixels;

import processing.core.PApplet;

public class PixelsFromImage extends PApplet {

  private int colorMode;
  private int pixelSize;
  private int height;
  private int width;
  private int color;
  private int colorValue;
  private String url;
  private ImageDownloader imageDownloader;

  public void settings(){
    height = 400;
    width = 400;
    pixelSize = 16;
    colorMode = HSB;
    colorValue = getValue();
    url = "https://ih1.redbubble.net/image.1502927078.3998/fposter,small,wall_texture,product,750x1000.jpg";
    imageDownloader = new ImageDownloader(url);
    imageDownloader.downloadImage();
  }

  public int getValue() {
    return colorMode == HSB ? 400 : 0;
  }

  public void draw(){
    //size(width, height);
    ////colorMode(RGB, 400);
    //colorMode(HSB, 400);
    //noStroke();   // Don't draw a stroke around shapes
    //fillMap();
  }

  public void fillMap() {
    for (int i = 0; i < height; i += pixelSize) {
      for (int j = 0; j < width; j += pixelSize) {
        color = color(i, j, colorValue);  // Create a color for 'c'
        fill(color);  // Use color variable 'c' as fill color
        rect(i, j, pixelSize, pixelSize);  // Draw left rect
      }
    }
  }

  public static void main(String[] args){
    PixelsFromImage pixelsFromImage = new PixelsFromImage();
    String[] processingArgs = {pixelsFromImage.getClass().toString()};
    PApplet.runSketch(processingArgs, pixelsFromImage);
  }
}
