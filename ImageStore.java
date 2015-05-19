import java.util.*;
import processing.core.*;
import java.io.*;
import java.util.Scanner;

private static final int COLOR_MASK = 0xffffff;

public class ImageStore{
   final String DEFAULT_IMAGE_NAME = "background_default";

   public Map <String, ArrayList<PImage> > loadImages(Scanner in, int tileWidth, int tileHeight){
      //List<PImage> images = new ArrayList<PImage>();
      
      HashMap images = new HashMap();
      while(in.hasNextLine()){
         processImageLine(images, in.nextLine());
      }
   }

   private static PImage setAlpha(PImage img, int maskColor, int alpha)
   {
      int alphaValue = alpha << 24;
      int nonAlpha = maskColor & COLOR_MASK;
      img.format = PApplet.ARGB;
      img.loadPixels();
      for (int i = 0; i < img.pixels.length; i++)
      {
         if ((img.pixels[i] & COLOR_MASK) == nonAlpha)
         {
            img.pixels[i] = alphaValue | nonAlpha;
         }
      }
      img.updatePixels();
      return img;
   }
}
