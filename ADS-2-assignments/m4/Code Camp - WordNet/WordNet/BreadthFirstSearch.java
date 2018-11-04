/**.
 * Class for breadth first directed paths.
 */
public class BreadthFirstSearch {
    /**.
     * { var_description }
     */
    private static final int INFINITY = Integer.MAX_VALUE;
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
    private int[] distTo;
    /**.
     * Constructs the object.
     *
     * @param      g     { parameter_description }
     * @param      s     { parameter_description }
     */
    public BreadthFirstSearch(final Digraph g, final int s) {
        marked = new boolean[g.vert()];
        distTo = new int[g.vert()];
        edgeTo = new int[g.vert()];
        for (int v = 0; v < g.vert(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertex(s);
        bfs(g, s);
    }
    /**.
     * bfs
     * time complexity is 1 in avg case
     * @param      g     { parameter_description }
     * @param      s     { parameter_description }
     */
    private void bfs(final Digraph g, final int s) {
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }
    // // BFS from multiple sources
    // private void bfs(Digraph G, Iterable<Integer> sources) {
    //     Queue<Integer> q = new Queue<Integer>();
    //     for (int s : sources) {
    //         marked[s] = true;
    //         distTo[s] = 0;
    //         q.enqueue(s);
    //     }
    //     while (!q.isEmpty()) {
    //         int v = q.dequeue();
    //         for (int w : G.adj(v)) {
    //             if (!marked[w]) {
    //                 edgeTo[w] = v;
    //                 distTo[w] = distTo[v] + 1;
    //                 marked[w] = true;
    //                 q.enqueue(w);
    //             }
    //         }
    //     }
    // }
    /**.
     * Is there a directed path from the source
     time complexity is 1 in avg case
     * @param      v     { parameter_description }
     * @return {@code true} if there is a directed path
     */
    public boolean hasPathTo(final int v) {
        validateVertex(v);
        return marked[v];
    }
    /**
     * Returns the number of edges in
     * (or sources) to vertex {@code v}?
     * time complexity is 1 in avg case
     * @param v the vertex
     * @return the number of edges in a shortest path
     */
    public int distTo(final int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns a shortest path from {@code s} (or sources) to {@code v}, or
     * {@code null} if no such path.
     * time complexity is O(N).
     * @param v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> pathTo(final int v) {
        validateVertex(v);

        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }
    /**.
     * { function_description }
     * time complexity is 1 in avg case
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        int v1 = marked.length;
        if (v < 0 || v >= v1) {
            throw new IllegalArgumentException("vertex " + v
                + " is not between 0 and " + (v1 - 1));
        }
    }
    /**.
     * { function_description }
     * time complexity is O(N).
     * @param      vertices  The vertices
     */
    private void validateVertices(final Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int v2 = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= v2) {
                throw new IllegalArgumentException("vertex " + v
                 + " is not between 0 and " + (v2 - 1));
            }
        }
    }


    // /**
    //  * Unit tests the {@code BreadthFirstDirectedPaths} data type.
    //  *
    //  * @param args the command-line arguments
    //  */
    // public static void main(String[] args) {
    //     In in = new In(args[0]);
    //     Digraph G = new Digraph(in);
    //     // StdOut.println(G);

    //     int s = Integer.parseInt(args[1]);
    //     BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);

    //     for (int v = 0; v < G.V(); v++) {
    //         if (bfs.hasPathTo(v)) {
    //             StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
    //             for (int x : bfs.pathTo(v)) {
    //                 if (x == s) StdOut.print(x);
    //                 else        StdOut.print("->" + x);
    //             }
    //             StdOut.println();
    //         }

    //         else {
    //             StdOut.printf("%d to %d (-):  not connected\n", s, v);
    //         }

    //     }
    // }
}

