import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;

/* Starter code for PS8.
 */

public class MovieRanker {

	public static void main(String[] args) {
                File file = new File("C:\\Users\\pavly\\Downloads\\ratings.tsv");

		ArrayList<MovieRating> rl = new ArrayList<MovieRating>();

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
                String[] tkns = line.split("\\t"); // tabs separate tokens
                MovieRating nr = new MovieRating(tkns[0], tkns[1], tkns[2]);
				rl.add(nr);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
      
		int minVotes = 1;
		int numRecords = 1;
		Scanner input = new Scanner(System.in);
//       MinHeap<MovieRating> original = new MinHeap<MovieRating>();
//       int inc = 0;
//       while (inc < rl.size());
//       {
//          original.insert(rl.get(inc));
//          inc++;
//       }

		while (true) {
			System.out.println();
			System.out.println("Enter minimum vote threshold and number of records:");
			minVotes = input.nextInt();
			numRecords = input.nextInt();
			if (minVotes * numRecords == 0)
				break;
			long startTime = System.currentTimeMillis();

/* Fill in code to determine the top numRecords movies that have at least
 * minVotes votes. For each record mr, in decreasing order of average rating,
 * execute a System.out.println(mr).
 * Do not sort the movie list!
 */
         MaxHeap<MovieRating> rankings = new MaxHeap<>();
         //MinHeap<MovieRating> invrankings = new MinHeap<>();
         int inc = 0;
         
         for (int i = 0; i < rl.size(); i++) {
             if (rl.get(i).getVotes()>minVotes ){
                 rankings.insert(rl.get(i));
//                  invrankings.insert(rl.get(i));
             }
//              else if (rl.get(i).getVotes()>minVotes && rankings.heapsize() < numRecords)
//              {
//                rankings.insert(rl.get(i));
//                invrankings.insert(rl.get(i));
//              }

         }
         
         for (int i = 0; i < numRecords; i++) {
             if (rankings.isEmpty()){
                 break;
             }
             System.out.println(rankings.removemax());
         }
			
            System.out.println();
			long readTime = System.currentTimeMillis();
			System.out.println("Time: "+(System.currentTimeMillis()-startTime)+" ms");
		}
	}
}
