
import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class Actions{
   public static Miner tryTransformMinerFull(WorldModel world, Miner m){
      Miner newEntity = new MinerNotFull(m.getName(), m.getResourceLimit(),
            m.getPosition(), m.getRate(), m.getImages(), m.getAnimationRate());
      return newEntity;
   }

   public static Miner tryTransformMinerNotFull(WorldModel world, Miner m){
       if(m.resourceCount < m.resourceLimit){
          return m;
       }

       else{
          Miner newEntity = new MinerFull(m.getName(), m.getResourceLimit(),
                m.getPosition(), m.getRate(), m.getImages(), m.getAnimationRate());
          return newEntity;
       }
   }
}
