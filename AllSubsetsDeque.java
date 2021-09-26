

import java.util.LinkedList;
import java.util.Deque;

public class AllSubsetsDeque {

  /**
   * Print out all nonempty subsets of a string using a deque
   * (queue or stack) instead of recursion.
   */

  public static void main(String args[]) {
    Deque<CState> q = new LinkedList<CState>();
    CState start = new CState("", "abcdef");
    // use addFirst for stack, addLast for queue
    q.addFirst(start);
    //q.addLast(start);
    while (!q.isEmpty()) {
      CState next = q.removeFirst(); // dequeue or pop (same)
      if (next.suff().length() > 0) {
        String newPrefix = next.pre() + next.suff().charAt(0);
        String newSuffix = next.suff().substring(1);
        System.out.println(newPrefix);
        CState n1 = new CState(newPrefix, newSuffix);
        CState n2 = new CState(next.pre(), newSuffix);
        // use addFirst for stack, addLast for queue
        q.addFirst(n1);
        q.addFirst(n2); //stack
        //q.addLast(n2); //queue
        //q.addLast(n1);
      }
    }
  }
}

