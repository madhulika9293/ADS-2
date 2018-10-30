/**
 * Class for connected components.
 */
public class ConnectedComponents {
    /**
     * marked.
     */
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    /**
     * id.
     */
    private int[] id;
    /**
     * size.
     */
    private int[] size;
    /**
     * count.
     */
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph {@code graph}.
     *
     * @param graph the undirected graph
     */
    public ConnectedComponents(final Graph graph) {
        marked = new boolean[graph.vertices()];
        id = new int[graph.vertices()];
        size = new int[graph.vertices()];
        for (int v = 0; v < graph.vertices(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }


    /**
     * depth-first search for a Graph.
     *
     * @param      graph  The graph
     * @param      v      { parameter_description }
     */
    private void dfs(final Graph graph, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }


    /**
     * Returns the component id
     * of the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the component id of
     * the connected component containing vertex {@code v}
     * @throws IllegalArgumentException
     * unless {@code 0 <= v < V}
     */
    public int id(final int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * Returns the number of vertices
     * in the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the number of vertices
     * in the connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int size(final int v) {
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the same
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     */
    public boolean connected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the same
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     * @deprecated Replaced by {@link #connected(int, int)}.
     */
    @Deprecated
    public boolean areConnected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    /**
     * throw an IllegalArgumentException unless {@code 0 <= v < V}.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        int vertices = marked.length;
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vertices - 1));
        }
    }
}
