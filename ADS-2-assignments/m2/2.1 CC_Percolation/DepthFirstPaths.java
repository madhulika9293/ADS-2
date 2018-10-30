public class DepthFirstPaths {
    /**
     *the array to mark the visited.
     *vertex
     */
    private boolean[] marked;
    /**
     *the array to maintian the.
     *relation path.
     */
    private int[] edgeTo;
         // edgeTo[v] = last edge on s-v path
    /**
     *the variable to maintain source vertex.
     */
    private final int s;         // source vertex
    /**
     * Computes a path between {@code s} and
     * every other vertex in graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    /**
     *the method is to perform the depth.
     *first search.
     *
     * @param      G     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a path between the source
     * vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path,
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     *throw an IllegalArgumentException unless
     *{@code 0 <= v < V}
     */
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (V - 1));
    }
}
