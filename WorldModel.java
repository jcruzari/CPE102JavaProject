import java.util.List;
import java.util.LinkedList;
import processing.core.*;

public class WorldModel{
   protected int numRows;
   protected int numCols;
   private Background[][] backgrounds;
   private Entity[][] occupancy;
   private List<Entity> entities;
   final static int BLOB_RATE_SCALE = 4;

   public WorldModel(int numCols, int numRows){
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

   public Entity findNearest(Point pt, Class type){
      List<Entity> ofType = new LinkedList<Entity>();
      for(Entity e : this.entities){
         if(type.isInstance(e)){
            ofType.add(e);
         }
      }
      return nearestEntity(ofType, pt);
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

   public PImage getBackgroundImage(Point pt){
      PImage b = new PImage();
      if(this.withinBounds(pt)){
         b = this.backgrounds[pt.y][pt.x].getImage();
      }
      return b;
   }

   public void setBackground(Point pt, Background b){
      if(this.withinBounds(pt)){
         this.backgrounds[pt.yCoord()][pt.xCoord()] = b;
      }
   }

   public Entity getTileOccupant(Point pt){
      Entity e = null;
      if(this.withinBounds(pt)){
         e = this.occupancy[pt.yCoord()][pt.xCoord()];
      }
      return e;
   }

   public List<Entity> getEntities(){
      return this.entities;
   }

   public Point nextPosition(Point entityPt, Point destPt){
      int vert;
      int horiz = sign(destPt.xCoord() - entityPt.xCoord());
      Point newPt = new Point(entityPt.xCoord() + horiz, entityPt.yCoord());
      
      if(horiz == 0 || this.isOccupied(newPt)){
         vert = sign(destPt.yCoord() - entityPt.yCoord());
         newPt = new Point(entityPt.xCoord(), entityPt.yCoord() + vert);
         if(vert == 0 || this.isOccupied(newPt)){
            newPt = new Point(entityPt.xCoord(), entityPt.yCoord());
         }
      }
      return newPt;
   }

   public Point blobNextPosition(Point entityPt, Point destPt){
      int vert;
      int horiz = sign(destPt.xCoord() - entityPt.xCoord());
      Point newPt = new Point(entityPt.xCoord() + horiz, entityPt.yCoord());

      if(horiz == 0 || (this.isOccupied(newPt) &&
         !(getTileOccupant(newPt) instanceof Ore))){
         vert = sign(destPt.yCoord() - entityPt.yCoord());
         newPt = new Point(entityPt.xCoord(), entityPt.yCoord() + vert);

         if(vert == 0 || (this.isOccupied(newPt) && 
            !(getTileOccupant(newPt) instanceof Ore))){
            newPt = new Point(entityPt.xCoord(), entityPt.yCoord());
         }
      }
      return newPt;
   }

   public boolean minerToOre(Entity e, Ore ore){
      Point entityPt = e.getPosition();
      Point orePt = ore.getPosition();
      if(adjacent(entityPt, orePt)){
         e.setResourceCount(1 + e.getResourceCount());
         return true;
      }
      return false;
   }

   public boolean minerToSmith(Entity e, Blacksmith smith){
      Point entityPt = e.getPosition();
      Point smithPt = smith.getPosition();
      if(adjacent(entityPt, smithPt)){
         smith.setResourceCount(smith.getResourceCount() +
            e.getResourceCount());
         e. setResourceCount(0);
         return true;
      }
      return false;
   }

   public boolean blobToVein(Entity e, Vein vein){
      Point entityPt = e.getPosition();
      Point veinPt = vein.getPosition();

      if(adjacent(entityPt, veinPt)){
         return true;
      }
      else{
         return false;
      }
   }

   public Point findOpenAround(Point pt, int distance){
      Point newPt;
      for(int dy = -distance; dy < distance + 1; dy++){
         for(int dx = -distance; dx < distance + 1; dx++){
            newPt = new Point(pt.xCoord() + dx, pt.yCoord() + dy);

            if(this.withinBounds(newPt) && !(this.isOccupied(newPt))){
               return newPt;
            }
         }
      }
      return null;
   }

   //Static methods

   public static double distanceSq(Point p1, Point p2){
      return (Math.sqrt(Math.pow(p1.xCoord() - p2.xCoord(), 2) +
         Math.pow(p1.yCoord() - p2.yCoord(), 2)));
   }   

   public static Entity nearestEntity(List<Entity> entityDists, Point pt){
      Entity nearest = null;
      Entity other = null;
      //entityDists = new LinkedList<Entity>();
      if(entityDists.size() > 0){
         other = entityDists.get(0);
         for(Entity e : entityDists){
            if(distanceSq(e.getPosition(), pt) < distanceSq(other.getPosition(),
               pt)){
               nearest = e;
            }
         }
      }
      else{
         nearest = null;
      }
      return nearest;
   }

   public static int sign(int x){
      if(x < 0){
         return -1;
      }
      else if(x > 0){
         return 1;
      }
      return 0;
   }

   public static boolean adjacent(Point pt1, Point pt2){
      return ((pt1.xCoord() == pt2.xCoord() && 
         Math.abs(pt1.yCoord() - pt2.yCoord()) == 1) ||
            (pt1.yCoord() == pt2.yCoord() && Math.abs(pt1.xCoord() - 
               pt2.xCoord()) == 1));
   }
}
