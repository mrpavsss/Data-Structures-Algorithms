package Lab1;

import java.util.Scanner;

public class RecursiveMultiply {

    // example:
    // mult(5, 20)
    // 20 +   mult(2, 40)
    //      => mult(1, 80)
    //        => 80 + mult(0, 160)
    //          => mult(0, 160), it returns 0
    // in total return 100

    public static long mult(long a, long b) {
        System.out.println("a: " + a + ", b: " + b);
        if (a <= 0 || b <= 0) {
            return 0;
        } else if (a % 2 == 1) {
            // a is an odd number
            return b + mult(a>>1, b<<1);
        } else {
            return mult(a>>1, b<<1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aa = scanner.nextInt();
        int bb = scanner.nextInt();
        System.out.println(mult(aa, bb));
    }
}
