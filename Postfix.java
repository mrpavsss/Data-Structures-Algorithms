import java.util.*;

/* Evaluate postix expression, terminated by "=".
 */

public class Postfix {
  public static void main(String[] args) throws EmptyStackException {
    Stack<Integer> stack = new Stack<Integer>();
    Scanner fin = new Scanner(System.in);

    while (fin.hasNext()) {
      String s = fin.next();
      if(s.equals("="))
        break;
      if (s.equals("+"))
        stack.push(stack.pop() + stack.pop());
      else if (s.equals("-"))
        stack.push(-stack.pop() + stack.pop());
      else if (s.equals("*"))
        stack.push(stack.pop() * stack.pop());
      else
        stack.push(Integer.parseInt(s));
    }
    System.out.println(stack.pop());
  }
}
