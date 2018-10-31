import java.util.Scanner;
/**
 * Class for Solution.
 */
public final class Solution {
  /**
   * Constructs the object.
   */

  private Solution() {
    /**
     * Unused.
     */
  }
  /**
   * Main function.
   *
   * @param      args  The arguments
   */

  public static void main(final String[] args) {
    Scanner sc = new Scanner(System.in);
    // Scanner sc1 = sc;
    Digraph dg = new Digraph(sc);
    // int n = sc1.nextInt();
    DirectedCycle dgs = new DirectedCycle(dg);
    // boolean m = dgs.hasCheck();
    if (dgs.hasCycle()) {
      System.out.println("Cycle exists.");
    } else {
      System.out.println("Cycle doesn't exists.");
    }
  }
}
