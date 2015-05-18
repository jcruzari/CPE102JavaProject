import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class Blacksmith extends Entity{
   /*private int resourceLimit;
   private int resourceCount;*/
   private int resourceDistance;

   public Blacksmith(String name, Point position, int resourceLimit,
      int rate, List<PImage> imgs, int resourceDistance){
      super(name, position, rate, imgs);
      this.currentImg = 0;
      this.resourceLimit = resourceLimit;
      this.resourceCount = 0;
      resourceDistance = 1;
      this.resourceDistance = resourceDistance;
   }
}
