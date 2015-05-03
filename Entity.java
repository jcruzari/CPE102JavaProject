public class Entity{
   private String name;
   private Point position;
   private int rate;
   private int animationRate;
   private int resourceLimit;
   private int resourceCount;

   public Entity(String name, Point position, int rate){
      this.name = name;
      this.position = position;
      this.rate = rate;
   }   
   
   public Entity(String name){
      this.name = name;
   }
   
   public void setPosition(Point point){
      this.position = point;
   }

   public Point getPosition(){
      return this.position;
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

   public void setResourceCount(int n){
      this.resourceCount = n;
   }

   public int getResourceCount(){
      return this.resourceCount;
   }
}
