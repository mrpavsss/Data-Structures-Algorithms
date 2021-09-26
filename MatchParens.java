import java.util.Scanner;
import java.util.Stack;
import java.util.*;

public class MatchParens {
	public static void main(String[] args) throws EmptyStackException{
		Stack<Character> stack = new Stack<Character>();

		Scanner fin = new Scanner(System.in);
		
		String opening = "([{";
		String closing = ")]}";

                boolean balanced = true;

		String s = fin.next();

		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			int j = opening.indexOf(c);
			int k = closing.indexOf(c);
			if(j>-1){  // opening symbol
				stack.push(new Character(c));
			}
			else if (k>-1) { // closing symbol
				if (stack.isEmpty() || stack.pop().charValue() != opening.charAt(k)) {
                                        balanced = false;
					break;
				}
			}
		}
		if (balanced && stack.isEmpty())
			System.out.println("Balanced parenthesis");
		else
			System.out.println("Unbalanced parenthesis");
	}
}
