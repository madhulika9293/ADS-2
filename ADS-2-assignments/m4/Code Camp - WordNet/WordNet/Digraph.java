/**.
 * Class for digraph.
 */
public class Digraph {
    /**.
     * { var_description }
     */
    private int ver;
    /**.
     * { var_description }
     */
    private int edg;
    /**.
     * { var_description }
     */
    private Bag<Integer>[] adj;
    /**.
     * { var_description }
     */
    private int size = 0;
    /**.
     * { var_description }
     */
    private int[] indegree;
    /**
     * Initializes an empty graph with V vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  vt number of vertices
     */
    Digraph(final int vt) {
        this.ver = vt;
        this.edg = 0;
        indegree = new int[ver];
        size = 0;
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Returns the number of vertices in this graph.
     * time complexity is 1 in avg case
     * @return the number of vertices in this graph
     */
    public int vert() {
        return ver;
    }

    /**
     * Returns the number of edges in this graph.
     * time complexity is 1 in avg case
     * @return the number of edges in this graph
     */
    public int edge() {
        return edg;
    }
    // /**.
    //  * Adds a vertex.
    //  * time complexity is 1
    //  * @param      v     { parameter_description }
    //  */
    // public void addVertex(final String v) {
    //     vertexes[size] = v;
    //     size++;
    // }
    /**.
     * Determines if it has edge.
     * time complexity is O(N).
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int i : adj[w]) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * Adds the undirected edge v-w to this graph.
     * time complexity is 1 in avg case
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     */
    public void addEdge(final int v, final int w) {
        edg++;
        adj[v].add(w);
        indegree[w]++;
        // adj[w].add(v);
    }
    /**
     * Returns the vertices adjacent to vertex {@code v}.
     * time complexity is 1 in avg case
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     * time complexity in average case is 1.
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     */
    public int outdegree(final int v) {
        return adj[v].size();
    }

    /**
     * time complexity in average case is 1.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     */
    public int indegree(final int v) {
        return indegree[v];
    }

}
