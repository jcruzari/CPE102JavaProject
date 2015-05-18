import processing.core.*;

import java.util.LinkedList;
import java.util.List;

public class Entity{
   private String name;
   private Point position;
   private int rate;
   protected int animationRate;
   protected int resourceLimit;
   protected int resourceCount;
   protected List<PImage> imgs = new LinkedList<>();
   protected int currentImg;

   public Entity(String name, Point position, int rate, List<PImage> imgs){
      this.name = name;
      this.position = position;
      this.rate = rate;
      this.imgs = imgs;
   }   
   
   public Entity(String name, List<PImage> imgs){
      this.name = name;
      this.imgs = imgs;
   }

   public Entity(String name, Point position, List<PImage> imgs){
      this.name = name;
      this.position = position;
      this.imgs = imgs;
   }

   public Entity(String name, Point position, List<PImage> imgs, int animationRate){
      this.name = name;
      this.position = position;
      this.imgs = imgs;
      this.animationRate = animationRate;
   }
   
   public void setPosition(Point point){
      this.position = point;
   }

   public Point getPosition(){
      return this.position;
   }

   public List<PImage> getImages(){
      return this.imgs;
   }

   public int getRate(){
      return this.rate;
   }

   public String getName(){
      return this.name;
   }

   public int getAnimationRate(){
      return this.animationRate;
   }

   public PImage getImage(){
      return this.imgs.get(currentImg);
   }

   public void nextImage(){
      this.currentImg = (this.currentImg + 1) % this.imgs.size();
   }

   public void setResourceCount(int n){
      this.resourceCount = n;
   }

   public int getResourceCount(){
      return this.resourceCount;
   }
}
