/**.
 * Class for directed cycle.
 */
public class DirectedCycle {
    /**.
     * { var_description }
     */
    private boolean[] marked;
    /**.
     * { var_description }
     */
    private int[] edgeTo;
    /**.
     * { var_description }
     */
    private boolean[] onStack;
    /**.
     * { var_description }
     */
    private Stack<Integer> cycle;
    /**.
     * Constructs the object.
     *
     * @param      g     { parameter_description }
     */
    public DirectedCycle(final Digraph g) {
        marked  = new boolean[g.vert()];
        onStack = new boolean[g.vert()];
        edgeTo  = new int[g.vert()];
        for (int v = 0; v < g.vert(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(g, v);
            }
        }
    }
    /**.
     * { function_description }
     * time complexity is 1 in avg case
     * @param      g     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(final Digraph g, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
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
                assert check();
            }
        }
        onStack[v] = false;
    }
/**.
 * Determines if it has cycle.
 * time complexity is 1 in avg case
 * @return     True if has cycle, False otherwise.
 */
    public boolean hasCycle() {
        return cycle != null;
    }
    /**.
     *
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }
    /**.
     * { function_description }
     * time complexity is O(N).
     * @return     { description_of_the_return_value }
     */
    private boolean check() {
        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n",
                 first, last);
                return false;
            }
        }
        return true;
    }

        // In in = new In(args[0]);
        // Digraph G = new Digraph(in);

        // DirectedCycle finder = new DirectedCycle(G);
        // if (finder.hasCycle()) {
        //     StdOut.print("Directed cycle: ");
        //     for (int v : finder.cycle()) {
        //         StdOut.print(v + " ");
        //     }
        //     StdOut.println();
        // }

        // else {
        //     StdOut.println("No directed cycle");
        // }
        // StdOut.println();
}
