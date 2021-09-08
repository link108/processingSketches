package AnotherTest;

import processing.core.PApplet;

public class AnotherTest extends PApplet{

  //public static void main(String[] args){
  //  String[] processingArgs = {"FirstSketch.MySketch.FirstSketch.MySketch"};
  //  FirstSketch.MySketch.FirstSketch.MySketch mySketch = new FirstSketch.MySketch.FirstSketch.MySketch();
  //  PApplet.runSketch(processingArgs, mySketch);
  //}
  public void settings(){
    size(500, 500);
  }

  public void draw(){
    ellipse(mouseX, mouseY, 50, 50);
  }

  public void mousePressed(){
    background(64);
  }

  public static void main(String[] args){
    String[] processingArgs = {"FirstSketch.MySketch"};
    AnotherTest anotherTest = new AnotherTest();
    PApplet.runSketch(processingArgs, anotherTest);
  }
}
