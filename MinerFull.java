import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class MinerFull extends Miner{
   private int resourceCount;
   public MinerFull(String name, int resourceLimit, Point position, int rate, List<PImage> imgs,
      int animationRate){
      super(name, resourceLimit, position, rate, imgs, animationRate);
      this.currentImg = 0;
      this.resourceCount = resourceLimit;
   }
}
