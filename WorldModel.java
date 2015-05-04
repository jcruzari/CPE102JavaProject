import java.util.List;
import java.util.LinkedList;

public class WorldModel{
   private int numRows;
   private int numCols;
   private Background[][] backgrounds;
   private Entity[][] occupancy;
   private List<Entity> entities;
   final static int BLOB_RATE_SCALE = 4;

   public WorldModel(int numCols, int numRows, Entity background){
      this.numCols = numCols;
      this.numRows = numRows;
      this.backgrounds = new Background[numCols][numRows];
      this.occupancy = new Entity[numCols][numRows];
      this.entities = new LinkedList<Entity>();
   }

   public boolean withinBounds(Point pt){
      return (pt.xCoord() >= 0 && pt.xCoord() < this.numCols && 
         pt.yCoord() >= 0 && pt.yCoord() < this.numRows);
   }

   public boolean isOccupied(Point pt){
      return (this.withinBounds(pt) && this.occupancy[pt.yCoord()]
         [pt.xCoord()] != null);
   }

   public void addEntity(Entity e){
      Point pt = e.getPosition();
      Entity oldEntity;
      if(this.withinBounds(pt)){
         oldEntity = this.occupancy[pt.yCoord()][pt.xCoord()];
         this.occupancy[pt.yCoord()][pt.xCoord()] = e;
         this.entities.add(e);
      }
   }

   //Static methods

   public static double distanceSq(Point p1, Point p2){
      return Math.sqrt(Math.pow(p1.xCoord() - p2.xCoord(), 2) + 
         Math.pow(p1.yCoord() - p2.yCoord(), 2));
   }   

   /*public static Entity nearestEntity(List<Entity> entityDists){
      List<Entity> pair = new LinkedList<Entity>();
      List<Entity> other = new LinkedList<Entity>();
      Entity nearest;
      if(entityDists.size() > 0){
         pair.add(entityDists(0));
         for (other : entityDists){
            if(other(1) < pair(1)){
               pair = other;
            }
         }
         nearest = pair(0);
      }
      else{
         nearest = null;
      }
      return nearest;
   }*/
}
