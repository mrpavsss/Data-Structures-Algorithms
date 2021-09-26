import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/** Identify a common 5-letter English word that has the following property:
 * Removing the first letter or the second letter results in words with the
 * same pronunciation as the original word.
 */

public class Homophone {

	public static void main(String[] args) {
		UALDictionary<String, ArrayList<String>> PDict = new UALDictionary<String, ArrayList<String>>();
        File file = new File("/Users/pavly/Downloads/cmudict.0.7a.txt");
	   Scanner scan = new Scanner(System.in);
      int lineInput = scan.nextInt();
      int lines = 0;
      ArrayList<String> allkeys = new ArrayList<String>();
 
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine() && lines <= lineInput) {
				String line = scanner.nextLine();
				if (line.substring(0, 3).equals(";;;"))
					continue; // skip comment lines
				Pronunciation p = new Pronunciation(line);
            if (PDict.find(p.getPhonemes()) != null)
               PDict.find(p.getPhonemes()).add(p.getWord());
            else {
               ArrayList<String> moving = new ArrayList<String>();
               moving.add(p.getWord());
               PDict.insert(p.getPhonemes(), moving);
               allkeys.add(p.getPhonemes());
            }
            lines = lines + 1;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
      
      ArrayList<ArrayList <String>> allhomophones = new ArrayList <ArrayList<String>>();
      int longestlength = 0;
      int length1 = 0;
      int sizeeverything = allkeys.size();
      for (int p = 0; p < sizeeverything; ++p)
      {
         if (longestlength < PDict.find(allkeys.get(p)).size())
            longestlength = PDict.find(allkeys.get(p)).size();
      }
      int allkeyssize = allkeys.size() - 1;
      for (int p =0; p <= allkeyssize; ++p)
      {
         length1 = PDict.find(allkeys.get(p)).size();
         if (length1 == longestlength)
            allhomophones.add(PDict.find(allkeys.get(p)));
      }
      
      System.out.println(longestlength);
      int allhomophonessize = allhomophones.size();
      for (int p = 0; p < allhomophonessize; ++p) {
         ArrayList<String> words = allhomophones.get(p);
         int wordssize = words.size();
         for (int r = 0; r < words.size();  ++r)
         {
            System.out.println(words.get(r));
         }
         System.out.println();
	   }
   }
}
