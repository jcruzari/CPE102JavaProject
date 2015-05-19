import java.util.*;
import processing.core.*;
import java.io.*;
import java.util.Scanner;


public class ImageStore{
   final String DEFAULT_IMAGE_NAME = "background_default";
   private static final int COLOR_MASK = 0xffffff;


   public PImage createDefaultImage(int tileWidth, int tileHeight){
      PImage surf = new PImage(tileWidth, tileHeight);
      return surf;
   }

   public Map <String, ArrayList<PImage> > loadImages(Scanner in, int tileWidth, int tileHeight){
      //List<PImage> images = new ArrayList<PImage>();
      PImage defaultImage;
      HashMap images = new HashMap();
      while(in.hasNextLine()){
         processImageLine(images, in.nextLine());
      }
      if(!images.containsKey(DEFAULT_IMAGE_NAME)){
         defaultImage = createDefaultImage(tileWidth, tileHeight);
         images.put(DEFAULT_IMAGE_NAME, defaultImage);
      }
      return images;
   }

   public void processImageLine(HashMap images, String line){
      String[] attrs = line.split("\\s");

      if(attrs.length >= 2){
         String key = attrs[0];
         PImage img = loadImage(attrs[1]);
      }
   }

   //public

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
