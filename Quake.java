
import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class Quake extends Entity{
    public Quake(String name, Point position, List<PImage> imgs, int animationRate){
        super(name, position, imgs, animationRate);
        this.currentImg = 0;
    }
}
