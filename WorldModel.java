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

   public List<Point> moveEntity(Entity e, Point pt){
      List<Point> tiles = new LinkedList<Point>();
      if(this.withinBounds(pt)){
         Point oldPt = e.getPosition();
         this.occupancy[pt.yCoord()][pt.xCoord()] = null;
         tiles.add(oldPt);
         this.occupancy[pt.yCoord()][pt.xCoord()] = e;
         tiles.add(pt);
         e.setPosition(pt);
      }
      return tiles;
   }

   public void removeEntityAt(Point pt){
      if(this.withinBounds(pt) && 
         this.occupancy[pt.yCoord()][pt.xCoord()] != null){
         Entity e = this.occupancy[pt.yCoord()][pt.xCoord()];
         e.setPosition(new Point(-1, 1));
         this.entities.remove(e);
         this.occupancy[pt.yCoord()][pt.xCoord()] = null;
      }
   }

   public void removeEntity(Entity e){
      this.removeEntityAt(e.getPosition());
   }

   public Background getBackground(Point pt){
      Background b = null;
      if(this.withinBounds(pt)){
         b = this.backgrounds[pt.yCoord()][pt.xCoord()];
      }
      return b;
   }

   public void setBackground(Point pt, Background b){
      if(this.withinBounds){
         this.backgrounds[pt.yCoord()][pt.xCoord()] = b;
      }
   }

   public Entity getTileOccupant(Point pt){
      
   }

   public List<Entity> getEntities(){
      return this.entities;
   }

   public Point nextPosition(Point entityPt, Point destPt){
      int horiz = sign(destPt.xCoord() - entityPt.xCoord());
   }

   //Static methods

   public static double distanceSq(Point p1, Point p2){
      return Math.sqrt(Math.pow(p1.xCoord() - p2.xCoord(), 2) + 
         Math.pow(p1.yCoord() - p2.yCoord(), 2));
   }   

   /*public static Entity nearestEntity(List<Entity> entityDists, Point pt){
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

   public static int sign(int x){
      if(x < 0){
         return -1
      }
      else if(x > 0){
         return 1;
      }
      return 0;
   }

   public static boolean adjacent(Point pt1, Point pt2){
      return ((pt1.xCoord() == pt.xCoord() && 
         Math.abs(pt1.yCoord() - pt2.yCoord()) == 1) ||
            (pt1.yCoord() == pt2.yCoord() && Math.abs(pt1.xCoord() - 
               pt2.xCoord()) == 1));
   }
}
