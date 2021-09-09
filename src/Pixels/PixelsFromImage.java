package Pixels;

import processing.core.PApplet;
import processing.core.PImage;

import java.nio.file.Path;

// Examples from https://processing.org/tutorials/pixels/#pixels-pixels-and-more-pixels
public class PixelsFromImage extends PApplet {

  private int colorMode;
  private int pixelSize;
  private int height;
  private int width;
  private int color;
  private int colorValue;
  private String url;
  private ImageDownloader imageDownloader;
  private PImage flower;
  private PImage skull;
  private PImage destination;

  public void settings(){
    //height = 400;
    //width = 400;
    pixelSize = 16;
    colorMode = HSB;
    colorValue = getValue();
    flower = loadImage("flower-image.jpg");
    height = flower.height;
    width = flower.width;
    size(width, height);
  }

  public void setup() {
    //url = "https://ih1.redbubble.net/image.1502927078.3998/fposter,small,wall_texture,product,750x1000.jpg";
    //imageDownloader = new ImageDownloader(url);
    //Path localImage = imageDownloader.downloadImage();
    noStroke();   // Don't draw a stroke around shapes
    //flower = loadImage("flower-image.jpg");
    skull = loadImage("skull.jpeg");
    //surface.setSize(flower.width, flower.height);
    surface.setSize(flower.width, flower.height);

    flower.pixels[0] = color(255,0,0); // Sets the first pixel of the image to red
    destination = createImage(flower.width, flower.height, RGB);
  }

  public int getValue() {
    return colorMode == HSB ? 400 : 0;
  }

  public void draw(){
    //background(flower);
    //tint(0, 255, 117);
    //loopThroughAllPixels();
    //loopThroughPixelsByColumn();
    //fillMapWithRects();
    //tintImage();
    //pixelateImage();
    //pixelBrightnessThreshold();
    //simpleBrightnessThreshold();
  }

  public void simpleBrightnessThreshold() {
    surface.setSize(flower.width, flower.height);
    image(flower, 0,0);
    float threshold = 0.5f;
    filter(THRESHOLD, threshold);
  }

  public void pixelBrightnessThreshold() {
    int threshold = 127;
    surface.setSize(flower.width, flower.height);
    flower.loadPixels();
    destination.loadPixels();
    int loc;
    for (int y = 0; y < flower.height; y++) {
      for(int x = 0; x < flower.width; x++) {
        loc = x + width * y;
        if(brightness(flower.pixels[loc]) > threshold) {
          destination.pixels[loc] = color(255);
        } else {
          destination.pixels[loc] = color(0);
        }
      }
    }
    destination.updatePixels();
    image(destination, 0,0);
  }

  public void pixelateImage() {
    loadPixels();
    surface.setSize(flower.width, flower.height);
    flower.loadPixels();
    int pixel;
    int loc;
    for(int y=0; y < height; y++) {
      for(int x=0; x < width; x++) {
        loc = x + width * y;
        pixel = flower.pixels[loc];
        float r = red(pixel);
        float g = green(pixel);
        float b = blue(pixel);

        // Change brightness according to the mouse location
        //float adjustBrightness = (float) (((float) mouseX / (float) width) * 8.0);

        // Calculate an amount to change brightness, based on proximity to the mouse
        float distance = dist(x,y,mouseX,mouseY);
        float adjustBrightness = (50-distance)/50;
        r *= adjustBrightness;
        g *= adjustBrightness;
        b *= adjustBrightness;

        // Constrain RGB to between 0-255
        r = constrain(r,0,255);
        g = constrain(g,0,255);
        b = constrain(b,0,255);

        pixels[loc] = color(r, g, b);
      }
    }
    updatePixels();
  }

  public void tintImage() {
    surface.setSize(flower.width, flower.height);
    background(flower);
    tint(0, 255, 117, 127); // tint color and opacity
    image(skull, 0, 0, flower.width, flower.height);
  }

  public void loopThroughPixelsByColumn() {
    loadPixels();
    // Loop through every pixel column
    for (int x = 0; x < width; x++) {
      // Loop through every pixel row
      for (int y = 0; y < height; y++) {
        // Use the formula to find the 1D location
        int loc = x + y * width;
        if (x % 2 == 0) { // If we are an even column
          pixels[loc] = color(255);
        } else {          // If we are an odd column
          pixels[loc] = color(0);
        }
      }
    }
    updatePixels();
  }

  public void loopThroughAllPixels() {
    loadPixels();
    // Loop through all pixels
    for (int i = 0; i < pixels.length; i++) {
      // Pick a random number, 0 to 255
      float rand = random(255);
      // Create a grayscale color based on random number
      int c = color(rand);
      // Set pixel at that location to random color
      pixels[i] = c;
    }
    // When we are finished dealing with pixels
    updatePixels();
  }

  public void fillMapWithRects() {
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
