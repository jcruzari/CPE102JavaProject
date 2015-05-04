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

}
