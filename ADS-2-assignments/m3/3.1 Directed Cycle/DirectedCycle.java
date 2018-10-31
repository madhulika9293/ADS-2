/**
 * Class for directed cycle.
 */
public class DirectedCycle {
  /**
   * marked[v] = has vertex v been marked.
   */
  private boolean[] marked;
  /**
   * edgeTo[v] = previous vertex on path to v.
   */
  private int[] edgeTo;
  /**
   * edgeTo[v] = previous vertex on path to v.
   */
  private boolean[] onStack;
  /**
   * onStack[v] = is vertex on the stack.
   */
  private Stack<Integer> cycle;
  /**
   * directed cycle (or null if no such cycle).
   */

  /**
   * Determines whether the digraph {@code g}
   * has a directed cycle and, if so,
   * finds such a cycle.
   * @param g the digraph
   */
  public DirectedCycle(final Digraph g) {
    marked  = new boolean[g.vertices()];
    onStack = new boolean[g.vertices()];
    edgeTo  = new int[g.vertices()];
    for (int v = 0; v < g.vertices(); v++) {
      if (!marked[v] && cycle == null) {
        dfs(g, v);
      }
    }
  }

  /**
   * DFS recursive program.
   * complexity: edges.
   * @param      g     { parameter_description }
   * @param      v     { parameter_description }
   */
  private void dfs(final Digraph g, final int v) {
    onStack[v] = true;
    marked[v] = true;
    for (int w : g.adj(v)) {

      // short circuit if directed cycle found
      if (cycle != null) {
        return;
      } else if (!marked[w]) {
        edgeTo[w] = v;
        dfs(g, w);
      } else if (onStack[w]) {
        cycle = new Stack<Integer>();
        for (int x = v; x != w; x = edgeTo[x]) {
          cycle.push(x);
        }
        cycle.push(w);
        cycle.push(v);
      }
    }
    onStack[v] = false;
  }

  /**
   * Does the digraph have a directed cycle?
   * @return {@code true} if the digraph
   * has a directed cycle, {@code false} otherwise
   */
  public boolean hasCycle() {
    return cycle != null;
  }

  /**
   * Returns a directed cycle if the digraph
   * has a directed cycle, and {@code null} otherwise.
   * @return a directed cycle (as an iterable)
   * if the digraph has a directed cycle,
   *    and {@code null} otherwise
   */
  public Iterable<Integer> cycle() {
    return cycle;
  }

}

