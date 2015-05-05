public class Animated extends Entity{
   private int animationRate;
   public Animated(String name, Point position, int rate, int animationRate){
      super(name, position, rate);
      this.animationRate = animationRate;
   }

   public int getAnimationRate(){
      return this.animationRate;
   }
}
