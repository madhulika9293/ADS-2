import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
// import java.math.BigDecimal;
// import java.math.MathContext;
import java.io.File;
import java.io.IOException;
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
  // private BigDecimal[] pr;
  private double[] pr;

  /**
   * imcoming vertices.
   */
  private Bag<Integer>[] incomVr;

  /**
   * Constructs the object.
   * BigDecimal change - 37.
   * @param      graph1  The graph
   */
  PageRank(final Digraph graph1) {
    this.graph = graph1;
    // pr = new BigDecimal[graph1.vertices()];
    pr = new double[graph1.vertices()];
    for (int i = 0; i < graph.vertices(); i++) {
      // int temp = graph.vertices();
      // pr[i] = BigDecimal.valueOf(1.0 / graph.vertices());
      pr[i] = 1.0 / graph.vertices();
    }
    for (int i = 0; i < graph.vertices(); i++) {
      if (graph.outdegree(i) == 0) {
        for (int j = 0; j < graph.vertices(); j++) {
          if (j != i) {
            graph.addEdge(i, j);
          }
        }
      }
    }
    // System.out.println(Arrays.toString(pr));
    // System.out.println(graph.vertices());
    // incomVr = getAdjRev();
    incomVr = graph.reverse().getAdj();
    getPR();
  }

  /**
   * Gets the adj reverse.
   *
   * Complexity : O(V)
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
   *
   * Complexity:  O(V).
   */
  public void getPR() {
    final int iter = 1000;
    // boolean flag = false;
    for (int it = 0; it < iter; it++) {
      double[] tempPR = new double[graph.vertices()];
      for (int i = 0; i < graph.vertices(); i++) {
        for (Integer ver : incomVr[i]) {
          double temp = (double) pr[ver] / graph.outdegree(ver) * 1.0;
          tempPR[i] += temp;
        }

      // BigDecimal[] tempPR = new BigDecimal[graph.vertices()];
      // Arrays.fill(tempPR, BigDecimal.ZERO);
      // for (int i = 0; i < graph.vertices(); i++) {
      //   for (Integer ver : incomVr[i]) {
      //     BigDecimal temp = pr[ver].divide(BigDecimal.valueOf(
      //                                        graph.outdegree(ver) * 1.0),
      //                                      MathContext.DECIMAL64);
      //     // System.out.println(tempPR[i]);
      //     tempPR[i] = tempPR[i].add(temp, MathContext.DECIMAL64);
      //   }

      }
      // System.out.println(it);
      if (Arrays.equals(pr, tempPR)) {
        // System.out.println(it);
        break;
      }
      // System.out.println(it);
      // System.out.println(Arrays.toString(tempPR));
      // System.out.println(Arrays.toString(pr));
      pr = tempPR.clone();
    }
  }

  /**
   * Gets the pr value.
   *
   *Complexity:  O(1).
   *
   * @param      vertex  The vertex
   *
   * @return     The pr value.
   */
  public /*BigDecimal*/ double getPRValue(final int vertex) {
    return pr[vertex];
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
  /**
   * the pagerank object.
   */
  private PageRank pgS;
  /**
   * hash map for the words in the content.
   */
  private HashMap<String, ArrayList<Integer>> webCont;
  /**
   * Constructs the object.
   *
   * @param      pg1       The page 1
   * @param      filename  The filename
   */
  WebSearch(final PageRank pg1, final String filename) {
    this.pgS = pg1;
    try {
      File file = new File(filename);
      Scanner sc = new Scanner(file);
      webCont = new HashMap<>();
      while (sc.hasNextLine()) {
        String[] temp = sc.nextLine().split(":");
        for (String word : temp[1].split(" ")) {
          webCont.putIfAbsent(word, new ArrayList<Integer>());
          webCont.get(word).add(Integer.parseInt(temp[0]));
        }
      }
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Could not open");
    }
  }

  /**
   * returns the webpage with the best page rank.
   *
   * Complexity:  O(V).
   *
   * @param      query  The query
   *
   * @return     the webpage.
   */
  public int iAmFeelingLucky(final String query) {
    if (webCont.containsKey(query)) {
      // BigDecimal max = BigDecimal.ZERO;
      double max = 0.0;
      int mpage = 0;
      for (Integer page : webCont.get(query)) {
        // BigDecimal temp = pgS.getPRValue(page);
        double temp = pgS.getPRValue(page);
        // if (temp.compareTo(max) >= 0) {
        if (temp >= max) {
          max = temp;
          mpage = page;
        }
      }
      return mpage;
    } else {
      return -1;
    }
  }
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
    WebSearch ws = new WebSearch(pg, file);

    // read the search queries from std in
    // remove the q= prefix and extract the search word
    // pass the word to iAmFeelingLucky method of web search
    // print the return value of iAmFeelingLucky

    while (scan.hasNext()) {
      String qr = scan.nextLine();
      String qrMod = qr.substring(2, qr.length());
      System.out.println(ws.iAmFeelingLucky(qrMod));
    }
  }
}
