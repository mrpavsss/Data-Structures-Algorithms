
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Random;

/* Select the k-th smallest element frmon an array of comparable
 * elements. The pivot element is chosen randomly. Average running time
 * is linear; worst case is quadratic.
 */

public class RandomizedSelect {

	public static Random rm = new Random();

	public static <E extends Comparable<E>> E randomizedSelect(E[] A, int i, int j, int q) {
		// select qth smallest of elements from i to j
		if (i == j)
			return A[i];
		int pivotindex = i + rm.nextInt(j - i + 1); // Pick a random pivot
		swap(A, pivotindex, j); // Stick pivot at end

		// k will be the first position in the right sub-array
		int k = partition(A, i - 1, j, A[j]);
		swap(A, k, j); // Put pivot in place

		int sz = k - i;
		if (q == sz + 1)
			return A[k];
		else if (q <= sz)
			return randomizedSelect(A, i, k - 1, q);
		else
			return randomizedSelect(A, k + 1, j, q - sz - 1);
	}

	private static <E extends Comparable<E>> int partition(E[] A, int l, int r, E pivot) {
		do { // Move bounds inward until they meet
			while (A[++l].compareTo(pivot)<0)
				;
			while ((r != 0) && (A[--r].compareTo(pivot)>0))
				;
			swap(A, l, r); // Swap out-of-place values
		} while (l < r); // Stop when they cross
		swap(A, l, r); // Reverse last, wasted swap
		return l; // Return first position in right partition
	}

	private static <E extends Comparable<E>> void swap(E[] A, int i, int j) {
		E temp = A[j];
		A[j] = A[i];
		A[i] = temp;
	}

}
