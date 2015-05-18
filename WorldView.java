import processing.core.*;

public class WorldView{
   private int viewCols;
   private int viewRows;
   private PApplet screen;
   private WorldModel world;
   private int tileWidth;
   private int tileHeight;
   private int numRows;
   private int numCols;
   private Viewport view;

   public WorldView(int viewCols, int viewRows, PApplet screen, WorldModel world, int tileWidth, int tileHeight){
      this.view = new Viewport(0, 0, viewCols, viewRows);
      this.screen = screen;
      this.viewCols = viewCols;
      this.viewRows = viewRows;
      this.world = world;
      this.tileWidth = tileWidth;
      this.tileHeight = tileHeight;
      this.numRows = world.numRows;
      this.numCols = world.numCols;
   }

   public Point viewportToWorld(Point pt){
      return new Point(pt.x + this.view.left, pt.y + this.view.top);
   }

   public Point worldToViewPort(Point pt){
      return new Point(pt.x - this.view.left, pt.y - this.view.top);
   }

   public Viewport createShiftedViewport(Point delta, int numRows, int numCols){
      int newX = clamp(this.view.left + delta.x, 0, numCols - this.view.width);
      int newY = clamp(this.view.top + delta.y, 0, numRows - this.view.height);

      return new Viewport(newX, newY, this.view.width, this.view.height);
   }

   public void drawBackground(){
      Point wPt;
      PImage img = new PImage();
      for(int y = 0; y < this.view.height; y++){
         for(int x = 0; x < this.view.width; x++){
            wPt = this.viewportToWorld(new Point(x, y));
            img = this.world.getBackgroundImage(wPt);
            this.screen.image(img,x*this.tileWidth, y*this.tileHeight);
         }
      }
   }

   public void drawEntities(){
      Point vPt;
      for(Entity e : this.world.getEntities()){
         vPt = this.worldToViewPort(e.getPosition());
         this.screen.image(e.getImage(), vPt.x*this.tileWidth, vPt.y*this.tileHeight);
      }
   }

   public void drawViewport(){
      this.drawBackground();
      this.drawEntities();
   }

   public void updateView(Point viewDelta){
      viewDelta = new Point(0, 0);
      this.view = this.createShiftedViewport(viewDelta, this.numRows, this.numCols);
      //pygame.display.update()
      this.drawViewport();
   }

   public PImage getTileImage(Point viewPt){
      Point pt = this.viewportToWorld(viewPt);
      PImage bgnd = this.world.getBackgroundImage(pt);
      Entity occupant = this.world.getTileOccupant(pt);
      PImage img;
      if(this.world.isOccupied(pt)){
         img = occupant.getImage();
         return img;
      }
      else{
         return bgnd;
      }
   }

   public int clamp(int v, int low, int high){
      return Math.min(high, Math.max(v, low));
   }
}
