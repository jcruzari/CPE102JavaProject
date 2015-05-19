
import processing.core.*;
import java.util.LinkedList;
import java.util.List;

public class Actions implements Action{
   final int BLOB_RATE_SCALE = 4;
   final int BLOB_ANIMATION_SCALE = 50;
   final int BLOB_ANIMATION_MIN = 1;
   final int BLOB_ANIMATION_MAX = 3;

   final int ORE_CORRUPT_MIN = 20000;
   final int ORE_CORRUPT_MAX = 30000;

   final int QUAKE_STEPS = 10;
   final int QUAKE_DURATION = 1100;
   final int QUAKE_ANIMATION_RATE = 100;

   final int VEIN_SPAWN_DELAY = 500;
   final int VEIN_RATE_MIN = 8000;
   final int VEIN_RATE_MAX = 17000;

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
