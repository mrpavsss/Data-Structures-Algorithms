

import java.util.Arrays;
import java.util.Random;
/** Program showing some array uses. */
public class Fibo2 {
  public static void main(String[] args) {
    System.out.println(args[0]);
    int n = Integer.parseInt(args[0]);
    int A[] = new int[n+1];
    long start = System.currentTimeMillis();
    for(int rep = 0; rep < 10000; ++rep){
      A[0] = 0;
      A[1] = 1;
      for (int i = 2; i < A.length; i++)
        A[i] = A[i-1]+A[i-2];
    }
    long t = System.currentTimeMillis()-start;
    System.out.println("The "+n+"th Fibonacci number is "+ A[n]);
    System.out.println("Elapsed time = " + t);
  }
}
