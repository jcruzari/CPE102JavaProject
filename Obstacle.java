
import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class Obstacle extends Entity{
   public Obstacle(String name, Point position, List<PImage> imgs){
       super(name, position, imgs);
       this.currentImg = 0;
   }
}
