public class Testing
{
   public static void main(String[] args)
   {
      String alpha = "abc";
      System.out.println(alpha.substring(0, 1));
   }
} 

public static String phrase(String line, int position) 
   { 
      String word = line.substring(0, position);
      if (binarySearch(wordList, word))
      {
         if (binarySearch(wordList, line.substring(position, line.length())))
         {
            sentence = word + " " + phrase(line.substring(position, line.length()), 0);
         }
         else 
         {
            phrase(line.substring(position, line.length()), position);
         }

            sentence = word + " " + phrase(line.substring(position, line.length()), 0);
      }
      else 
      {
         if(line.length() == position) {
            if (line.length() >= 1) {
               return null;
            }
            return sentence;
         }
         if (position + 1 <= line.length()) {
            phrase(line, position + 1);
         }
      }
      return sentence;          
   }
