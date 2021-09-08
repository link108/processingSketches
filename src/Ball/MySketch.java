package Ball;

import processing.core.PApplet;

import java.util.ArrayList;

public class MySketch extends PApplet{

  private ArrayList<Ball> balls = new ArrayList<>();

  public void settings(){
    size(500, 500);
    balls.add(new Ball(this, width/2, height/2));
  }

  public void draw(){
    background(64);
    for(Ball b : balls){
      b.step();
      b.render();
    }
  }

  public void mouseDragged(){
    balls.add(new Ball(this, mouseX, mouseY));
  }

  public static void main(String[] args){
    String[] processingArgs = {"FirstSketch.MySketch"};
    MySketch mySketch = new MySketch();
    PApplet.runSketch(processingArgs, mySketch);
  }
}
