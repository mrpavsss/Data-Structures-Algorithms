import java.util.*;
import java.text.NumberFormat;

/* Select the k-th smallest element frmon an array of comparable
 * elements. The pivot element is chosen randomly. Average running time
 * is linear; worst case is quadratic.
 */

public class DeterministicSelect {

	public static <E extends Comparable<E>> E deterministicSelect(E[] A, int i, int j, int q) {
		// select qth smallest of elements from i to j
		if (i == j)
			return A[i];
		int pivotindex = getMedian(A); // Pick a pivot
		swap(A, pivotindex, j); // Stick pivot at end

		// k will be the first position in the right sub-array
		int k = partition(A, i - 1, j, A[j]);
		swap(A, k, j); // Put pivot in place

		int sz = k - i;
		if (q == sz + 1)
			return A[k];
		else if (q <= sz)
			return deterministicSelect(A, i, k - 1, q);
		else
			return deterministicSelect(A, k + 1, j, q - sz - 1);
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

   private static <E extends Comparable<E>> void heap(E[] A) {
      // The heap constructor invokes the buildheap method
    
    
      // MaxHeap<E> H = new MaxHeap<E>(A);
      PriorityQueue<E> pq = new PriorityQueue<E>(A.length);
      for (int i = 0; i < A.length; i++)
         pq.add(A[i]);
      for (int i = 0; i < A.length; i++)
        // Now sort
        A[A.length - i - 1] = pq.poll(); // Removemax (Java calls it poll.
   }
   
   private static <E extends Comparable<E>> Integer getMedian(E[] A){
      if(A.length == 1)
      {
         return Integer.parseInt(A[0].toString());
      }
      ArrayList<Integer> medians = new ArrayList<Integer>();
      int counter = 0;
      Integer[] temp;
      while(A.length > counter)
      {
         if(A.length-counter >=5)
            temp = new Integer[5];
         else
            temp = new Integer[A.length-counter]; 

         for(int p = 0; p < temp.length; p++)
         {
            temp[p] = Integer.parseInt(A[p].toString());
            counter++;
         }
            heap(temp);
            medians.add(median(temp));
      }
      Integer[] t = new Integer[medians.size()];
      for(int p = 0; p < medians.size(); p++)
         t[p] = medians.get(p);
      return getMedian(t); 
   }
   
   private static <E extends Comparable<E>> E median(E[] list)
    {
        if((list.length % 2) == 0)
            return list[(list.length/2)-1];
        return list[(list.length/2)];
    }
}