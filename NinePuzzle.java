
/** Solve the nine-puzzle problem in PS5.
 *
 */

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Scanner;

public class NinePuzzle {

	public static void main(String[] args) {
		final String goalState = "123456789";
		Scanner sc = new Scanner(System.in);
      String originalBoard = sc.nextLine();
		String initialState = "987654321";
		boolean found = false;
		Queue<NinePuzzleState> q = new LinkedList<NinePuzzleState>();
		boolean[] visited = new boolean[987654322];
		for (int i = 0; i < visited.length; ++i)
			visited[i] = false;
                int explored = 0; // number of states visited
		visited[Integer.parseInt(originalBoard)] = true;
                ++explored; 
		q.add(new NinePuzzleState(null, originalBoard));
		while (q.size() > 0 && !found && explored < visited.length) {
			NinePuzzleState s = q.remove();
			if (s.current.equals(goalState)) {
				found = true;
				// Now construct solution; use stack to get right order.
				Stack<String> soln = new Stack<String>();
				soln.push(s.current);
				while (s.predecessor != null) {
					soln.push(s.predecessor.current);
					s = s.predecessor;
				}
				while (!soln.isEmpty())
					System.out.println(soln.pop());
			}
			LinkedList<String> m = PgetMoves(s.current);
			for (String ns : m)
				if ((ns != null) && (!visited[Integer.parseInt(ns)])) {
					q.add(new NinePuzzleState(s, ns));
					visited[Integer.parseInt(ns)] = true;
                                        ++explored; 
				}
		}
		if (!found)
			System.out.println("-1");
			//System.out.println("No solution.");
         
      //for (int size = 0; size < q.size(); ++size)

	}

	/*
	 * Generate all valid states that can be reached via a valid move from current
	 * position (at most four).
	 */
	public static LinkedList<String> PgetMoves(String pos) {
		int ind9 = pos.indexOf('9');
		LinkedList<String> moves = new LinkedList<String>();
		if (ind9 == 4) {
			moves.add(swap(pos, ind9, ind9+1));
                        
                }
		if (ind9 > 2) // try up
			moves.add(swap(pos, ind9 - 3, ind9));
		if (ind9 % 3 < 2) // try right
			moves.add(swap(pos, ind9, ind9 + 1));

                //Pavly: add the (2) other cases
      if (ind9 < 6)
         moves.add(swap(pos, ind9 + 3, ind9));
      if(ind9 % 3 > 0)
         moves.add(swap(pos, ind9, ind9 - 1));

		return moves;
	}

	public static String swap(String str, int i, int j) 
    { 
        char ch[] = str.toCharArray(); 
        char temp = ch[i]; 
        ch[i] = ch[j]; 
        ch[j] = temp; 
        
        return new String(ch); 
    }

}
