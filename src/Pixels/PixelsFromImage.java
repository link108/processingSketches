package Pixels;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
//import processing.core.PGraphics;

//import java.nio.file.Path;

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
  private boolean initialDraw;

  public void settings() {
    height = 400;
    width = 400;
    pixelSize = 16;
    colorMode = HSB;
    colorValue = getValue();
    //flower = loadImage("flower-image.jpg");
    //flower = loadImage("sunflower.jpeg");
    //height = flower.height;
    //width = flower.width;
    //size(width, height);
    //size(flower.width, flower.height, P3D);
  }

  public void setup() {
    initialDraw = true;
    flower = loadImage("/Users/cmotevasselani/git-repos/link108/processingSketches/flower-image.jpg");
    //url = "https://ih1.redbubble.net/image.1502927078.3998/fposter,small,wall_texture,product,750x1000.jpg";
    //imageDownloader = new ImageDownloader(url);
    //Path localImage = imageDownloader.downloadImage();
    noStroke();   // Don't draw a stroke around shapes
    //flower = loadImage("flower-image.jpg");
//    skull = loadImage("skull.jpeg");
    //surface.setSize(flower.width, flower.height);
    surface.setSize(flower.width, flower.height);

    flower.pixels[0] = color(255,0,0); // Sets the first pixel of the image to red
    destination = createImage(flower.width, flower.height, RGB);
    //image(flower, 0, 0);
    smooth();
    background(255);
  }


  public int getValue() {
    return colorMode == HSB ? 400 : 0;
  }

  public void draw(){
    //background(flower);
    //tint(0, 255, 117);
    //loopThroughAllPixels();
    //loopThroughPixelsByColumn();
//    fillMapWithRects();
//    tintImage();
//    change_brightness_by_mouse_location();
//    pixelBrightnessThreshold();
//    simpleBrightnessThreshold();
//    pixelLeftNeighborDiff();
//    sharpenWithConvolution();
//    pointilizeImage();
//    convert2Dto3D();
    if (initialDraw == true)  {
      pixelizeImage();
      initialDraw = false;
    }
  }


  public int getAverageColor(int x, int y, int radius){
    int starting_i = Math.max(x - radius, 0);
    int starting_j = Math.max(y - radius, 0);
    int ending_i = Math.min(x + radius, flower.width);
    int ending_j = Math.min(y + radius, flower.height);

    int loc;
    int pixel;
    List<Float> reds = new ArrayList<>();
    List<Float> greens = new ArrayList<>();
    List<Float> blues = new ArrayList<>();

    for (int i = starting_i; i < ending_i; i++) {
      for (int j = starting_j; j < ending_j; j++) {
        loc = i + flower.width * j;
        pixel = flower.pixels[loc];
        reds.add(red(pixel));
        greens.add(green(pixel));
        blues.add(blue(pixel));
      }
    }
    float red = calculateAverage(reds);
    float green = calculateAverage(greens);
    float blue = calculateAverage(blues);


    return color(red, green, blue);

  }

  public static float calculateAverage(List<Float> list) {
//    list.removeIf(e -> e >= 220);
    float sum = 0;
    for (Float value : list) {
      sum += value;
    }
    return sum / list.size();
  }

  public void pixelizeImage() {
    int pixelSize = 5;
    noStroke();
    for (int x = 0; x < flower.width; x += pixelSize) {
      for (int y = 0; y < flower.height; y += pixelSize) {
        int loc = x + flower.width * y;
        int pixelColor = getAverageColor(x, y, pixelSize);
//        int pixelColor = flower.pixels[loc];
        fill(pixelColor);
        rect(x, y, pixelSize, pixelSize);

      }
    }
  }

  public int cellsize = 2;

  public void convert2Dto3D() {
    int cols = flower.width / cellsize;
    int rows = flower.height / cellsize;
    background(0);
    loadPixels();
    int x, y, loc, color;
    float z;

    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        x = i * cellsize + cellsize/2;
        y = j * cellsize + cellsize/2;
        loc = x + width * y;
        color = flower.pixels[loc];

        // Calculate a z position as a function of mouseX and pixel brightness
        z = (float)((mouseX/(float)width) * brightness(flower.pixels[loc]) - 100.0);

        // Translate to the location, set fill and stroke, and draw the rect
        pushMatrix();
        translate((float)x,(float)y,z);
        fill(color);
        noStroke();
        rectMode(CENTER);
        rect(0,0,cellsize,cellsize);
        popMatrix();
      }
    }

  }

  public void pointilizeImage() {
    int pointilize = 16;

    // pick a random spot
    int x = (int)(random(flower.width));
    int y = (int)(random(flower.height));
    int loc = x + flower.width * y;

    flower.loadPixels();
    float r = red(flower.pixels[loc]);
    float g = green(flower.pixels[loc]);
    float b = blue(flower.pixels[loc]);
    noStroke();
    System.out.println("loc: " + loc + ", X: " + x + ", Y: " + y + ", rgb: " + r + "," + g + "," + b);
    //System.out.println("loc: " + loc + ", X: " + x + ", Y: " + y);
    //System.out.println("rgb: " + r + "," + g + "," + b);
    //System.out.println("color: " + color(r, g, b));

    // draw an ellipse at that location
    fill(r,g,b,100);
    //fill(color(r, g, b), 100);
    ellipse(x, y, pointilize, pointilize);
  }

  public void sharpenWithConvolution() {
    int w = 80;
    int loc;
    frameRate(30);
    float[][] matrix = { { -1, -1, -1 },
                         { -1,  9, -1 },
                         { -1, -1, -1 } };
    int matrixsize = matrix.length;
    image(flower, 0, 0);

    // Where is the small rectangle we will process
    int xstart = constrain(mouseX-w/2,0,flower.width);
    int ystart = constrain(mouseY-w/2,0,flower.height);
    int xend = constrain(mouseX+w/2,0,flower.width);
    int yend = constrain(mouseY+w/2,0,flower.height);
    loadPixels();

    for (int x = xstart; x < xend; x++) {
      for (int y = ystart; y < yend; y++) {
        loc = x + flower.width * y;
        // Each pixel location (x,y) gets passed into a function called convolution()
        // which returns a new color value to be displayed.
        int convolutedColor = convolution(x,y,matrix,matrixsize,flower);
        pixels[loc] = convolutedColor;
      }
    }
    updatePixels();
  }

  public int convolution(int x, int y, float[][] matrix, int matrixsize, PImage img) {
    float rtotal = 0.0f;
    float gtotal = 0.0f;
    float btotal = 0.0f;
    int loc, xLoc, yLoc;
    int offset = matrixsize / 2;
    for(int i=0; i<matrixsize; i++) {
      for(int j=0; j<matrixsize; j++) {
        xLoc = x + i - offset;
        yLoc = y + j - offset;
        loc = xLoc + img.width * yLoc;
        // Make sure we have not walked off the edge of the pixel array
        loc = constrain(loc,0,img.pixels.length-1);

        // Calculate the convolution
        // We sum all the neighboring pixels multiplied by the values in the convolution matrix.
        rtotal += (red(img.pixels[loc]) * matrix[i][j]);
        gtotal += (green(img.pixels[loc]) * matrix[i][j]);
        btotal += (blue(img.pixels[loc]) * matrix[i][j]);
      }
    }

    rtotal = constrain(rtotal, 0, 255);
    gtotal = constrain(gtotal, 0, 255);
    btotal = constrain(btotal, 0, 255);

    return  color(rtotal, gtotal, btotal);

  }

  public void pixelLeftNeighborDiff() {
    surface.setSize(flower.width, flower.height);
    loadPixels();
    flower.loadPixels();
    int loc;
    int leftloc;
    int pixColor;
    int leftPixColor;
    for(int y = 1; y < flower.height; y++) {
      for(int x = 0; x < flower.width; x++) {
        loc = x + width * y;
        pixColor = flower.pixels[loc];
        leftloc = (x-1) + width * y;
        leftPixColor = flower.pixels[leftloc];

        float diff = abs(brightness(pixColor) - brightness(leftPixColor));
        pixels[loc] = color(diff);
        System.out.println("diff: " + diff);
      }
    }
    updatePixels();
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

  public void change_brightness_by_mouse_location() {
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
