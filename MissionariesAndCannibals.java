import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;

public class MissionariesAndCannibals {

  /**
   * Solve the "missionaries and cannibals" problem. Start with N of each on
   * left bank, get them all across without ever having missionaries outnumbered
   * by cannibals on either bank. Boat can carry at most 2 people.
   * Report number of trips required (-1 if there is no solution).
   * In this version we use a dequeue as a queue.
   */
  public static void main(String args[]) {
    int N = 3;  //problem size
    State.setN(N);
    int numberTrips = -1;
    ArrayDeque<State> toExplore = new ArrayDeque<State>();
    ArrayList<State> visited = new ArrayList<State>();
    ArrayDeque<State> soln = new ArrayDeque<State>();
    State start = new State(N, N, 0);
    //toExplore.addLast(start); // using deque as queue
    toExplore.addFirst(start); // using deque as stack
    visited.add(start); 
    while (! toExplore.isEmpty()) {
      State next = toExplore.removeFirst(); // dequeue
      next.display();
      if (next.m == 0 && next.c == 0 && next.b == 1) {
        for (State x = next; x != null; x = x.pred) {
          soln.offerLast(x);
        }
        numberTrips = soln.size()-1;
        System.out.println("Solution:");
        State s = soln.pollLast();
        while(s != null) {
            s.display();
            s = soln.pollLast();
        }
        break;
      }
      // generate all possible next states;
      // i represents number of missionaries in boat, j number of cannibals
      for (int i = 0; i <= 2; i++)
        for (int j = 0; i + j <= 2; j++) {
          if (i == 0 && j == 0)
            continue; // can't have empty boat
          State p = next.move(i, j);
          if (!p.legal())
            continue;
          boolean beenHere = false;
          for(State s : visited) {
             if (s.equiv(p)) {
               beenHere = true;
               break;
             }
          }
          if(beenHere)
            continue;
          //toExplore.addLast(p); // enqueue
          toExplore.addFirst(p); // stack push
          visited.add(p); 
        }
    }
    System.out.println("It required " + numberTrips + " crossings");
  }
}
