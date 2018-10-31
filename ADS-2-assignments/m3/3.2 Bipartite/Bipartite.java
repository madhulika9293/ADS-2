/**
 * Class for bipartite.
 */
public class Bipartite {
    /**
     * checks if the graph bipartite.
     */
    private boolean isBipartite;
    /**
     * color[v] gives vertices on one side of bipartition.
     */
    private boolean[] color;
    /**
     * marked[v] = true iff v has been visited in DFS.
     */
    private boolean[] marked;
    /**
     * edgeTo[v] = last edge on path to v.
     */
    private int[] edgeTo;
    /**
     * odd-length cycle.
     */
    private Stack<Integer> cycle;

    /**
     * Determines whether an undirected graph is bipartite and finds either a
     * bipartition or an odd-length cycle.
     *
     * @param  graph the graph
     */
    public Bipartite(final Graph graph) {
        isBipartite = true;
        color  = new boolean[graph.V()];
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];

        for (int v = 0; v < graph.V(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
        assert check(graph);
    }

    /**
     * DFS.
     *
     * @param      graph  The graph
     * @param      v      { parameter_description }
     */
    private void dfs(final Graph graph, final int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {

            // short circuit if odd-length cycle found
            if (cycle != null) {
                return;
            }

            // found uncolored vertex, so recur
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(graph, w);
            }

            // if v-w create an odd-length cycle, find it
            else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<Integer>();
                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    /**
     * Returns true if the graph is bipartite.
     *
     * @return {@code true} if the graph is bipartite; {@code false} otherwise
     */
    public boolean isBipartite() {
        return isBipartite;
    }

    /**
     * Returns the side of the bipartite that vertex {@code v} is on.
     *
     * @param  v the vertex
     * @return the side of the bipartition that vertex {@code v} is on; two vertices
     *         are in the same side of the bipartition if and only if they have the
     *         same color
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws UnsupportedOperationException if this method is called when the graph
     *         is not bipartite
     */
    public boolean color(final int v) {
        validateVertex(v);
        if (!isBipartite)
            throw new UnsupportedOperationException("graph is not bipartite");
        return color[v];
    }

    /**
     * Returns an odd-length cycle if the graph is not bipartite, and
     * {@code null} otherwise.
     *
     * @return an odd-length cycle if the graph is not bipartite
     *         (and hence has an odd-length cycle), and {@code null}
     *         otherwise
     */
    public Iterable<Integer> oddCycle() {
        return cycle;
    }

    private boolean check(final Graph graph) {
        // graph is bipartite
        if (isBipartite) {
            for (int v = 0; v < graph.V(); v++) {
                for (int w : graph.adj(v)) {
                    if (color[v] == color[w]) {
                        System.err.printf("edge %d-%d with %d and %d in same side of bipartition\n", v, w, v, w);
                        return false;
                    }
                }
            }
        }

        // graph has an odd-length cycle
        else {
            // verify cycle
            int first = -1, last = -1;
            for (int v : oddCycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }

        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(final int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}
