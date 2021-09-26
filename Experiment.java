import java.util.ArrayList;
import java.text.NumberFormat;

/* Program sorts many arrays using one of the "Mystery" sorts
 * sort1-sort4. You can plot the run times using Plot (if compatable
 * with where you are running the program) or simply print out the
 * results and plot with any plotting software.
 */

public class Experiment {

  public static void main(String[] args) {
    MysterySorts srts = new MysterySorts();
    int n;
    int k = 30; // experiment size
    int REP = 10; // how many times to repeat experiment

    int[] data = new int[k]; // to store run times
    for (int i = 0; i < k; ++i) { 
      n = (i + 1) * 5000;
      Integer[] A = new Integer[n];
      for (int j = 0; j < n; ++j)
        A[j] = j;

      long start = System.currentTimeMillis();
      for (int rep = 0; rep < REP; ++rep) {
        srts.shuffleArray(A);
        srts.sort(0,A);
      }
      long now = System.currentTimeMillis();
      long elapsed = now - start;
      data[i] = (int) elapsed;
    }
    Plot.view(data);
     for(int i=0;i<k;++i)
     System.out.println(data[i]);
  }
}
