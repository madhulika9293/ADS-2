/**
 * Class for depth first paths.
 */
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
     * Computes a path between {@code s1} and
     * every other vertex in graph {@code graph}.
     * @param graph the graph
     * @param s1 the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DepthFirstPaths(final Graph graph, final int s1) {
        this.s = s1;
        edgeTo = new int[graph.vertices()];
        marked = new boolean[graph.vertices()];
        validateVertex(s);
        dfs(graph, s1);
    }

    /**
     *the method is to perform the depth.
     *first search.
     *
     * @param      graph     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(final Graph graph, final int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    /**
     * Is there a path between the source
     * vertex {@code s} and vertex {@code v}?
     * @param v1 the vertex
     * @return {@code true} if there is a path,
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     */
    public boolean hasPathTo(final int v1) {
        validateVertex(v1);
        return marked[v1];
    }

    /**
     * @param v the vertex
     *throw an IllegalArgumentException unless.
     *{@code 0 <= v < V}
     */
    private void validateVertex(final int v) {
        int ver = marked.length;
        if (v < 0 || v >= ver) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (ver - 1));
        }
    }
}
