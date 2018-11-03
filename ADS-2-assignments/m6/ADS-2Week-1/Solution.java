import java.util.Scanner;
// import java.util.Arrays;
import java.util.ArrayList;

/**
 * Class for page rank.
 */
class PageRank {
  /**
   * the digraph.
   */
  private Digraph graph;
  /**
   * PageRank array.
   */
  private double[] pr;

  /**
   * imcoming vertices.
   */
  private ArrayList<Integer>[] incomVr;
  /**
   * Constructs the object.
   *
   * @param      graph1  The graph
   */
  PageRank(final Digraph graph1) {
    this.graph = graph1;
    pr = new double[graph1.vertices()];
    for (int i = 0; i < graph.vertices(); i++) {
      // int temp = graph.vertices();
      pr[i] = (double) (1.0 / graph.vertices());
    }
    // System.out.println(Arrays.toString(pr));
    // System.out.println(graph.vertices());
    incomVr = getAdjRev();
  }

  /**
   * Gets the adj reverse.
   *
   * @return     The adj reverse.
   */
  public ArrayList<Integer>[] getAdjRev() {
    ArrayList<Integer>[] adjRev = new ArrayList[graph.vertices()];

    for (int v = 0; v < graph.vertices(); v++) {
      adjRev[v] = new ArrayList<Integer>();
    }

    // System.out.println(Arrays.toString(adjRev));
    Bag<Integer>[] adjGr = graph.getAdj();
    for (int i = 0; i < graph.vertices(); i++) {
      for (Integer num : adjGr[i]) {
        // System.out.println(num);
        adjRev[num].add(i);
      }
    }
    return adjRev;
  }

  /**
   * Gets the pr.
   */
  public void getPR() {
    final int iter = 104;
    boolean flag = false;
    for (int it = 0; it < iter; it++) {
      double[] tempPR = new double[graph.vertices()];
      for (int i = 0; i < graph.vertices(); i++) {
        for (Integer ver : incomVr[i]) {
          double temp = (double) pr[ver] / graph.outdegree(ver) * 1.0;
          tempPR[i] += temp;
        }
      }
      // if (it > 2 && Arrays.equals(pr, tempPR)) {
      //   flag = true;
      //   System.out.println(it);
      //   break;
      // }
      pr = tempPR.clone();
      // System.out.println(Arrays.toString(tempPR));
      // double delta = 0.0;
      // for (int i = 0; i < graph.vertices(); i++) {
      //   delta += (double) (tempPR[i] - pr[i]);
      // }
      // // System.out.println(delta);
      // if (it > 1 && delta == 0.00005) {
      //   System.out.println(it);
      //   break;
      // }
      // System.out.println(Arrays.toString(pr));
    }
  }

  /**
   * Returns a string representation of the object.
   *
   * @return     String representation of the object.
   */
  public String toString() {
    getPR();
    String out = "";
    for (int i = 0; i < pr.length; i++) {
      out += i;
      out += " - ";
      out += pr[i];
      out += "\n";
    }
    return out.substring(0, out.length() - 1);
  }

  /**
   * prints the data.
   */
  public void print() {
    getPR();
    for (int i = 0; i < pr.length; i++) {
      System.out.printf(i + " - ");
      System.out.println(pr[i]);
    }
  }
}
/**
 * Class for web search.
 */
class WebSearch {

}

/**
 * Class for solution.
 */
public final class Solution {
  /**
   * Constructs the object.
   */
  private Solution() {
    // unused
  }
  /**
   * Main method.
   *
   * @param      args  The arguments
   */
  public static void main(final String[] args) {

    // read the first line of the input to get the number of vertices
    // iterate count of vertices times
    // to read the adjacency list from std input
    // and build the graph

    Scanner scan = new Scanner(System.in);

    int numV = Integer.parseInt(scan.nextLine());

    Digraph di = new Digraph(numV);

    for (int i = 0; i < numV; i++) {
      String[] inps = scan.nextLine().split(" ");
      int ver = Integer.parseInt(inps[0]);
      for (int j = 1; j < inps.length; j++) {
        di.addEdge(ver, Integer.parseInt(inps[j]));
      }
    }

    System.out.println(di);

    // Create page rank object and pass the graph object to the constructor

    PageRank pg = new PageRank(di);

    // print the page rank object

    // System.out.println(pg);
    pg.print();
    // System.out.println(Arrays.toString(pg.getAdjRev()));

    // This part is only for the final test case

    // File path to the web content
    String file = "WebContent.txt";

    // instantiate web search object
    // and pass the page rank object and the file path to the constructor

    // read the search queries from std in
    // remove the q= prefix and extract the search word
    // pass the word to iAmFeelingLucky method of web search
    // print the return value of iAmFeelingLucky

  }
}
