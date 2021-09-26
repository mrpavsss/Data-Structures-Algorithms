
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/* Determine length of longest word in words.txt */

public class Starter {

        public static ArrayList<String> wordList;

	public static void main(String[] args) {
		wordList = new ArrayList<String>();
		//File file = new File("words.txt"); // Use this on your machine.
		File file = new File("C:\\Users\\pavly\\Downloads\\words.txt");

		int maxWordLength = 0;

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String line = scanner.next();
				wordList.add(line);
                                int n = line.length();
                                if(n>maxWordLength) maxWordLength = n;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
                System.out.println("Max length = "+maxWordLength);
	}
}
