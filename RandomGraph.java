import java.util.LinkedList;
import java.util.Random;

public class RandomGraph {

  public static void main(String[] args) {

    int n = 5; // number of vertices
    // enter the seed as a single command line argument
    long seed = 10; // pseudo-random number generator seed
    // if(args.length != 1) {
//         System.out.println("Must supply a random number seed.");
//         System.exit(0);
//     } else seed = Long.parseLong(args[0]);
    Random rng = new Random(seed);
    Graphm graph = new Graphm(n);
 
    // create a random graph; each edge present with probability 0.3
    for(int i=0;i<n;++i){
        graph.setMark(i, 0);
        for(int j=i+1;j<n;++j){
            double u = rng.nextDouble();
            if(u < 0.3){
               graph.setEdge(i,j,1);
               graph.setEdge(j,i,1);
            }
        }
    }

    System.out.println("Adjacency matrix:");
    for(int i=0;i<n;++i){
        for(int j=0;j<n;++j){
            if(graph.isEdge(i,j))
               System.out.print("1 ");
            else
               System.out.print("0 ");
        }
        System.out.println();
    }

    // use BFS to identify connected components
    int[] component = new int[n];
    for(int i=0;i<n;++i) component[i] = 0;
    int count = 1;
    for(int source = 0; source < n;++source){
        if(component[source]>0) continue;
        for(int i=0;i<n;++i) graph.setMark(i,0);
        BFS(graph, source);
        for(int i=0;i<n;++i) if(graph.getMark(i)>0) component[i] = count;
        ++count;
    }
    System.out.println("Graph has "+(count-1)+" connected components.");

    // Compute matrix of distances between pairs of vertices.
    // If dest is not reachable from source, set dist = -1.
    int[][] dist = new int[n][n];
    for(int source = 0; source < n;++source){
            for(int i=0;i<n;++i) graph.setMark(i,0);
            BFS(graph, source);
            for(int dest = 0; dest < n;++dest){
                if(graph.getMark(dest)>0)
                    dist[source][dest] = graph.getMark(dest)-1;
                else
                    dist[source][dest] = -1;
            }
    }

    System.out.println("Distances (x means unreachable):");
    for(int i=0;i<n;++i){
        for(int j=0;j<n;++j){
           if(dist[i][j] >= 0)
               System.out.print(dist[i][j]+" ");
           else
               System.out.print("x ");
        }
        System.out.println();
    }
  }


  /** Depth first search */
  static void DFS(Graph G, int v) {
    PreVisit(G, v); // Take appropriate action
    G.setMark(v, 1);
    for (int w = G.first(v); w < G.n(); w = G.next(v, w))
      if (G.getMark(w) == 0)
        DFS(G, w);
    PostVisit(G, v); // Take appropriate action
  }

  /** Breadth first (queue-based) search */
  static void BFS(Graph G, int start) {
    LinkedList<Integer> Q = new LinkedList<Integer>();
    Q.addLast(start);
    G.setMark(start, 1);
    int d = 0;
    while (Q.size() > 0) { // Process each vertex on Q
      ++d;
      int v = Q.removeFirst();
      PreVisit(G, v); // Take appropriate action
      for (int w = G.first(v); w < G.n(); w = G.next(v, w))
        if (G.getMark(w) == 0) { // Put neighbors on Q
          G.setMark(w, G.getMark(v) + 1);
          Q.addLast(w);
        }
      PostVisit(G, v); // Take appropriate action
    }
  }

  static void PreVisit(Graph G, int v) {
    //System.out.println("");
  }

  static void PostVisit(Graph G, int v) {
    //System.out.println("");
  }
}
