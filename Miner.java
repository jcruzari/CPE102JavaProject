public class Miner extends Animated{
   private int resourceLimit;

   public Miner(String name, int resourceLimit, Point position, int rate,
      int animationRate){
      super(name, position, rate, animationRate);
      this.resourceLimit = resourceLimit;
   }

   public int getResourceLimit(){
      return this.resourceLimit;
   }
}
