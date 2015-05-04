public class Vein extends Entity{
   private int resourceDistance;
   public Vein(String name, int rate, Point position, int resourceDistance){
      super(name, position, rate);
      resourceDistance = 1;
      this.resourceDistance = resourceDistance;
   }
   public int getResourceDistance(){
      return this.resourceDistance;
   }
}
