import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class Vein extends Entity{
   private int resourceDistance;
   public Vein(String name, int rate, Point position, List<PImage> imgs, int resourceDistance){
      super(name, position, rate, imgs);
      resourceDistance = 1;
      this.currentImg = 0;
      this.resourceDistance = resourceDistance;
   }
   public int getResourceDistance(){
      return this.resourceDistance;
   }
}
