public class Entity{
   private String name;
   private Point position;
   private int rate;

   public Entity(String name, Point position, int rate){
      this.name = name;
      this.position = position;
      this.rate = rate;
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
}
