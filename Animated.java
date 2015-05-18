import processing.core.*;
import java.util.List;
import java.util.LinkedList;

public class Animated extends Entity{
   //private int animationRate;
   public Animated(String name, Point position, int rate, int animationRate, List<PImage> imgs){
      super(name, position, rate, imgs);
      this.animationRate = animationRate;
   }

   /*public int getAnimationRate(){
      return this.animationRate;
   }*/
}
