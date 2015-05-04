public class Blacksmith extends Entity{
   private int resourceLimit;
   private int resourceCount;
   private int resourceDistance;

   public Blacksmith(String name, Point position, int resourceLimit,
      int rate, int resourceDistance){
      super(name, position, rate);
      this.resourceLimit = resourceLimit;
      this.resourceCount = 0;
      resourceDistance = 1;
      this.resourceDistance = resourceDistance;
   }
}
