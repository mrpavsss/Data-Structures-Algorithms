import java.util.*;


public class Solver {
private int moves = 0;
private SearchNode finalNode;
private Stack<Board> boards;


public Solver(Board initial) {
    if (!initial.isSolvable()) throw new IllegalArgumentException("Unsolvable puzzle");

    // this.initial = initial;
    PriorityQueue<SearchNode> minPQ = new PriorityQueue<SearchNode>(initial.size() + 10);

    Set<Board> previouses = new HashSet<Board>(50);
    Board dequeuedBoard = initial;
    Board previous = null;
    SearchNode dequeuedNode = new SearchNode(initial, 0, null);
    Iterable<Board> boards;

    while (!dequeuedBoard.isGoal()) {
        boards = dequeuedBoard.neighbors();
        moves++;

        for (Board board : boards) {
            if (!board.equals(previous) && !previouses.contains(board)) {
                minPQ.add(new SearchNode(board, moves, dequeuedNode));
            }
        }

        previouses.add(previous);
        previous = dequeuedBoard;
        dequeuedNode = minPQ.poll();
        dequeuedBoard = dequeuedNode.current;
    }
    finalNode = dequeuedNode;
}

// min number of moves to solve initial board
public int moves() {
    if (boards != null) return boards.size()-1;
    solution();
    return boards.size() - 1;
}

public Iterable<Board> solution() {
    if (boards != null) return boards;
    boards = new Stack<Board>();
    SearchNode pointer = finalNode;
    while (pointer != null) {
        boards.push(pointer.current);
        pointer = pointer.previous;
    }
    return boards;
}

private class SearchNode implements Comparable<SearchNode> {
    private final int priority;
    private final SearchNode previous;
    private final Board current;


    public SearchNode(Board current, int moves, SearchNode previous) {
        this.current = current;
        this.previous = previous;
        this.priority = moves + current.manhattan();
    }

    @Override
    public int compareTo(SearchNode that) {
        int cmp = this.priority - that.priority;
        return Integer.compare(cmp, 0);
    }


}
public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      String originalBoard = scan.nextLine();
      String goal = "123456789";
      char[][] notsorted = new char[3][3];
      int[][] origin = new int[3][3];
      int inc = 0;
      for (int i = 0; i < 3; ++i)
      {
         for (int j = 0; j < 3; ++j)
         {
            notsorted[i][j] = originalBoard.charAt(inc);
            ++inc;
         }
      }
      inc = 0;
      for (int i = 0; i < 3; ++i)
      {
         for (int j = 0; j < 3; ++j)
         {
            origin[i][j] = Character.getNumericValue(notsorted[i][j]);
            ++inc;
         }
      }



    double start = System.currentTimeMillis();
    Board board = new Board(origin);
    Solver solve = new Solver(board);
    System.out.printf("# of moves = %d && # of actual moves %d & time passed %f\n, ", solve.moves(), solve.moves, (System.currentTimeMillis() - start) / 1000);


}
}