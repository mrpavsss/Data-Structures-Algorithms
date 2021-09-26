import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


public final class Board {

    private final int[][] tilesCopy;
    private final int N;

    // cache
    private int hashCode = -1;
    private int zeroRow = -1;
    private int zeroCol = -1;
    private Collection<Board> neighbors;
    /*
     * Rep Invariant
     *      tilesCopy.length > 0
     * Abstraction Function
     *      represent single board of 8 puzzle game
     * Safety Exposure
     *      all fields are private and final (except cache variables). In the constructor,
     * defensive copy of tiles[][] (array that is received from the client)
     * is done.
     */
    public Board(int[][] tiles) {
        //this.N = tiles.length;
        this.N = 3;
        this.tilesCopy = new int[N][N];
        // defensive copy
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] >= 0 && tiles[i][j] < N*N) tilesCopy[i][j] = tiles[i][j];
                else {
                    System.out.printf("Illegal tile value at (%d, %d): "
                            + "should be between 0 and N^2 - 1.", i, j);
                    System.exit(1);
                }
            }
        }
        checkRep();
    }

    public int tileAt(int row, int col) {
        if (row < 0 || row > N - 1) throw new IndexOutOfBoundsException
                ("row should be between 0 and N - 1");
        if (col < 0 || col > N - 1) throw new IndexOutOfBoundsException
                ("col should be between 0 and N - 1");

        return tilesCopy[row][col];
    }

    public int size() {
        return N;
    }

    public int hamming() {
        int hamming = 0;
        for (int row = 0; row < this.size(); row++) {
            for (int col = 0; col < this.size(); col++) {
                if (tileAt(row, col) != 0 && tileAt(row, col) != (row*N + col + 1)) hamming++;
            }
        }
        return hamming;
    }
    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;

        int expectedRow = 0, expectedCol = 0;
        for (int row = 0; row < this.size(); row++) {
            for (int col = 0; col < this.size(); col++) {
                if (tileAt(row, col) != 0 && tileAt(row, col) != (row*N + col + 1)) {
                    expectedRow = (tileAt(row, col) - 1) / N;
                    expectedCol = (tileAt(row, col) - 1) % N;
                    manhattan += Math.abs(expectedRow - row) + Math.abs(expectedCol - col);
                }
            }
        }
        return manhattan;
    }

    public boolean isGoal() {

        if (tileAt(N-1, N-1) != 0) return false;        // prune

        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (tileAt(i, j) != 0 && tileAt(i, j) != (i*N + j + 1)) return false;
            }
        }

        return true;
    }
    // change i && j' s name
    public boolean isSolvable() {
        int inversions = 0;

        for (int i = 0; i < this.size() * this.size(); i++) {
            int currentRow = i / this.size();
            int currentCol = i % this.size();

            if (tileAt(currentRow, currentCol) == 0) {
                this.zeroRow = currentRow;
                this.zeroCol = currentCol;
            }

            for (int j = i; j < this.size() * this.size(); j++) {
                int row = j / this.size();
                int col = j % this.size();


                if (tileAt(row, col) != 0 && tileAt(row, col) < tileAt(currentRow, currentCol)) {
                    inversions++;
                }
            }
        }

        if (tilesCopy.length % 2 != 0 && inversions % 2 != 0) return false;
        if (tilesCopy.length % 2 == 0 && (inversions + this.zeroRow) % 2 == 0) return false;

        return true;
    }



    @Override
    public boolean equals(Object y) {
        if (!(y instanceof Board)) return false;
        Board that = (Board) y;
        // why bother checking whole array, if last elements aren't equals
        return this.tileAt(N - 1, N - 1) == that.tileAt(N - 1, N - 1) && this.size() == that.size() && Arrays.deepEquals(this.tilesCopy, that.tilesCopy);

    }

    @Override
    public int hashCode() {
        if (this.hashCode != -1) return hashCode;
        // more optimized version(Arrays.hashCode is too slow)?
        this.hashCode = Arrays.deepHashCode(tilesCopy);
        return this.hashCode;
    }

    public Collection<Board> neighbors() {
        if (neighbors != null) return neighbors;
        if (this.zeroRow == -1 && this.zeroCol == -1) findZeroTile();

        neighbors = new HashSet<>();

        if (zeroRow - 1 >= 0)           generateNeighbor(zeroRow - 1, true);
        if (zeroCol - 1 >= 0)           generateNeighbor(zeroCol - 1, false);
        if (zeroRow + 1 < this.size())  generateNeighbor(zeroRow + 1, true);
        if (zeroCol + 1 < this.size())  generateNeighbor(zeroCol + 1, false);

        return neighbors;
    }

    private void findZeroTile() {
        outerloop:
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (tileAt(i, j) == 0) {
                    this.zeroRow = i;       // index starting from 0
                    this.zeroCol = j;
                    break outerloop;
                }
            }
        }
    }
   private void generateNeighbor(int toPosition, boolean isRow) {
    Board board = new Board(this.tilesCopy);
    if (isRow)  swapEntries(board.tilesCopy, zeroRow, zeroCol, toPosition, zeroCol);
    else        swapEntries(board.tilesCopy, zeroRow, zeroCol, zeroRow, toPosition);

    neighbors.add(board);
}

    private void swapEntries(int[][] array, int fromRow, int fromCol, int toRow, int toCol) {
        int i = array[fromRow][fromCol];
        array[fromRow][fromCol] = array[toRow][toCol];
        array[toRow][toCol] = i;
    }

    public String toString() {
        StringBuilder s = new StringBuilder(4 * N * N);     // optimization?
//      s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private void checkRep() {
        assert tilesCopy.length > 0;
    }
}