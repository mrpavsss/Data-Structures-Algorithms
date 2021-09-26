import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class RestoreBlanks
{
   public static ArrayList<String> wordList;
   public static String sentence = "";
   public static boolean binarySearch(ArrayList<String> words, String wording) 
   {
      int lowIndex = 0;
      int highIndex = words.size()-1;

      // Holds the position in array for given element
      // Initial negative integer set to be returned if no match was found on array
      

      // If lowIndex less than highIndex, there's still elements in the array
      while (lowIndex <= highIndex) 
      {
         int midIndex = (lowIndex + highIndex) / 2;
         if (wording.equals(words.get(midIndex))) 
         {
            return true;
         }
         else if (wording.compareTo(words.get(midIndex)) < 0) 
         {
            highIndex = midIndex-1;
         }
         else if (wording.compareTo(words.get(midIndex)) > 0) 
         {
            lowIndex = midIndex+1;
         }
      }
      return false;
   }

   public static void main(String[] args) 
   {
      Scanner scan = new Scanner(System.in);
      String lineInput = scan.nextLine();
      
      
      wordList = new ArrayList<String>();
		//File file = new File("words.txt"); // Use this on your machine.
		File file = new File("C:\\Users\\pavly\\Downloads\\words.txt");

		int maxWordLength = 0;

		try 
      {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) 
         {
				String line = scanner.next();
				wordList.add(line);
            int n = line.length();
            if(n>maxWordLength) maxWordLength = n;
			}
			scanner.close();
		} 
      catch (FileNotFoundException e) {
			e.printStackTrace();
		}
      
      phrase(lineInput, 1);
      if (sentence == "")
      {
         System.out.println("null");
      }
      else
      {
         System.out.println(sentence.substring(1, sentence.length()));
      }
	}
   public static String phrase(String line, int position) 
   { 
      String word = line.substring(0, position);
      if (binarySearch(wordList, word))
      {
         sentence =  " " + word + phrase(line.substring(position, line.length()), 1);
         
         
      }
      else 
      {
         if(line.length() == position) {
            return line;
         }
         if (position + 1 <= line.length()) {
            return phrase(line, position + 1);
         }
      }
      return sentence;          
   }

}
