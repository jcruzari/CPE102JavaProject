import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class OreBlob extends Animated{
   public OreBlob(String name, Point position, int rate, int animationRate, List<PImage> imgs){
      super(name, position, rate, animationRate, imgs);
      this.currentImg = 0;
   }
}
