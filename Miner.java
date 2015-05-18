import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class Miner extends Animated{
   //private int resourceLimit;

   public Miner(String name, int resourceLimit, Point position, int rate, List<PImage> imgs,
      int animationRate){
      super(name, position, rate, animationRate, imgs);
      this.resourceLimit = resourceLimit;
   }

   public int getResourceLimit(){
      return this.resourceLimit;
   }
}
