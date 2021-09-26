import java.util.*;
import java.text.*;
import java.awt.*;

public class SortIdentifier {

   public static void main(String[] args) {
      ArrayList<String> names = new ArrayList<String>();
      names.add("selection");
      names.add("insertion");
      names.add("heap");
      names.add("quick");
      
      Integer[] smaller = new Integer[5000];
      Integer[] bigger = new Integer[10000];
      for (int i = 0; i < 3; ++i) { 
         for (int count = 0; count < smaller.length; ++count)
           smaller[count] = count;
         
         for (int count = 0; count < bigger.length; ++count){
            bigger[count] = count;
         }
         
         double smallersorted = sortedgettime(i, smaller);
         double biggersorted = sortedgettime(i, bigger);
         double smallerunsorted = unsortedgettime(i, smaller);
         double biggerunsorted = unsortedgettime(i, bigger);
         
         double sortedcase = biggersorted / smallersorted;
         double unsortedcase = biggerunsorted / smallerunsorted;
         
         if (Math.abs(4-sortedcase)<1 && Math.abs(4-unsortedcase)<1){
            System.out.println("selection");
//            int position = names.indexOf("selection");
            names.remove(names.indexOf("selection"));
         }
         else if (sortedcase<1 && Math.abs(4-unsortedcase)<1) {
            System.out.println("insertion");
//            int position = names.indexOf("insertion");
            names.remove(names.indexOf("insertion"));
         }
         else if (Math.abs(1-sortedcase) < 1 && Math.abs(2-unsortedcase) < 1) {
            System.out.println("heap");
//            int position = names.indexOf("heap");
            names.remove(names.indexOf("heap"));
         }
         else {
            System.out.println("quick");
//            int position = names.indexOf("quick");
            names.remove(names.indexOf("quick"));
         }
      }
      System.out.println(names.get(0));
      
   }
   
   public static double sortedgettime(int number, Integer[] array) {
      MysterySorts srts = new MysterySorts();
      int time = 0;
      int k = 15;
      int Reps = 10;
      for (int i = 0; i < k; ++i) {
         long start = System.currentTimeMillis();
         for (int rep = 0; rep < Reps; ++rep) {
            srts.sort(number, array);
         }
         long now = System.currentTimeMillis();
         long elapsed = now - start;
         time += (double) elapsed;
      }
      return time;
   }
   public static double unsortedgettime(int number, Integer[] array) {
      MysterySorts srts = new MysterySorts();
      int time = 0;
      int k = 15;
      int Reps = 10;
      for (int i = 0; i < k; ++i) {
         long start = System.currentTimeMillis();
         for (int rep = 0; rep < Reps; ++rep) {
            srts.shuffleArray(array);
            srts.sort(number, array);
         }
         long now = System.currentTimeMillis();
         long elapsed = now - start;
         time += (double) elapsed;
      }
      return time;
   }
}