import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Test;
public class TestCases{
   private static final double DELTA = 0.00001;

   @Test
   public void testPointMethods(){
      Point pt = new Point(24, 81);
      assertEquals(pt.xCoord(), 24, DELTA);
      assertEquals(pt.yCoord(), 81, DELTA);
   }

   @Test
   public void testBackgroundMethods(){
      Background b = new Background("World");
      assertEquals(b.getName(), "World");
   }

   @Test
   public void testMinerNotFull(){
      Point pt = new Point(14, 47);
      Miner m = new MinerNotFull("Manny", 12, pt, 58, 5);
      System.out.println(m.getAnimationRate());
      assertEquals(m.getName(), "Manny");
      assertEquals(m.getResourceLimit(), 12);
      assertEquals(m.getPosition(), pt);
      assertEquals(m.getRate(), 58);
      assertEquals(m.getAnimationRate(), 5);
   }
}
