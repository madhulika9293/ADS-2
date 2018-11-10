import java.util.Scanner;

/**
 * Class for solution.
 */
public class Solution {
  /**
   * Constructs the object.
   */
  private Solution() {
    // unused constructor
  }
  /**
   * main method.
   *
   * @param      args  The arguments
   */
  public static void main(final String[] args) {
    // Self loops are not allowed...
    // Parallel Edges are allowed...
    // Take the Graph input here...
    Scanner scan = new Scanner(System.in);

    int vertices = Integer.parseInt(scan.nextLine());
    int edges = Integer.parseInt(scan.nextLine());
    EdgeWeightedGraph eg = new EdgeWeightedGraph(vertices);

    for (int i = 0; i < edges; i++) {
      String[] inputs = scan.nextLine().split(" ");
      int v = Integer.parseInt(inputs[0]);
      int w = Integer.parseInt(inputs[1]);
      double wght = Double.parseDouble(inputs[2]);
      Edge e = new Edge(v, w, wght);
      eg.addEdge(e);
    }

    String caseToGo = scan.nextLine();
    switch (caseToGo) {
    case "Graph":
      System.out.println(eg);
      break;

    case "DirectedPaths":
      // Handle the case of DirectedPaths, where two integers are given.
      // First is the source and second is the destination.
      // If the path exists print the distance between them.
      // Other wise print "No Path Found."
      String[] inputs = scan.nextLine().split(" ");
      int source = Integer.parseInt(inputs[0]);
      int dest = Integer.parseInt(inputs[1]);
      DijkstraUndirectedSP dusp = new DijkstraUndirectedSP(eg, source);
      if (dusp.hasPathTo(Integer.parseInt(inputs[1]))) {
        System.out.println(dusp.distTo(dest));
      } else {
        System.out.println("No Path Found");
      }
      break;

    case "ViaPaths":
      // Handle the case of ViaPaths, where three integers are given.
      // First is the source and second is the via is the one where path should pass throuh.
      // third is the destination.
      // If the path exists print the distance between them.
      // Other wise print "No Path Found."
      break;

    default:
      break;
    }

  }
}