import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class Ore extends Entity{
   public Ore(String name, Point position, int rate, List<PImage> imgs){
      super(name, position, rate = 5000, imgs);
   }
}
