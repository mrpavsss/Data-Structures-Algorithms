

import java.util.Arrays;
import java.util.Random;
/** Program showing some array uses. */
public class Fibo1 {
  public static void main(String[] args) {
    System.out.println(args[0]);
    int n = Integer.parseInt(args[0]);
    long start = System.currentTimeMillis();
    long A = fib(n);
    long t = System.currentTimeMillis()-start;
    System.out.println("The "+n+"th Fibonacci number is "+ A);
    System.out.println("Elapsed time = " + t);
  }
  public static long fib(int n)
  {
    if(n <= 1) return n;
    else return fib(n-1)+fib(n-2);
  }
}
