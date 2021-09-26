public class NinePuzzleState {
  public String current;
  public NinePuzzleState predecessor;

  public NinePuzzleState(NinePuzzleState p, String c) {
    current = c;
    predecessor = p;
  }
}
