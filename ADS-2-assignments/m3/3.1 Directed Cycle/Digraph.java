import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Class for digraph.
 */
public class Digraph {
    /**
     * Newline declaration.
     */
    /**
     * number of vertices in this digraph.
     */
    private final int vertices;
    /**
     * number of edges in this digraph.
     */
    private int edges;
    /**
     * adj[v] = adjacency list for vertex v.
     */
    private Bag<Integer>[] adj;
    /**
     * adj[v] = adjacency list for vertex v.
     */
    private int[] indegree;

    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param  vertices1 the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Digraph(final int vertices1) {
        if (vertices1 < 0) {
            throw new IllegalArgumentException(
                "Number of vertices in a Digraph must be nonnegative");
        }
        this.vertices = vertices1;
        this.edges = 0;
        indegree = new int[vertices];
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices,
     * with each entry separated by whitespace.
     *
     * @param  sc scanner
     * @throws IllegalArgumentException if the endpoints of any edge
     * are not in prescribed range
     * @throws IllegalArgumentException if the
     *  number of vertices or edges is negative
     * @throws IllegalArgumentException if
     *  the input stream is in the wrong format
     */
    public Digraph(final Scanner sc) {
        try {
            this.vertices = Integer.parseInt(sc.nextLine());
            if (vertices < 0) {
                throw new IllegalArgumentException(
                    "number of vertices in a Digraph must be nonnegative");
            }
            indegree = new int[vertices];
            adj = (Bag<Integer>[]) new Bag[vertices];
            for (int v = 0; v < vertices; v++) {
                adj[v] = new Bag<Integer>();
            }
            int edges1 = Integer.parseInt(sc.nextLine());
            if (edges1 < 0) {
                throw new IllegalArgumentException(
                    "number of edges in a Digraph must be nonnegative");
            }
            for (int i = 0; i < edges1; i++) {
                int v = sc.nextInt();
                int w = sc.nextInt();
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(
                "invalid input format in Digraph constructor", e);
        }
    }

    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param  digraph the digraph to copy
     */
    public Digraph(final Digraph digraph) {
        this(digraph.vertices());
        this.edges = digraph.edges();
        for (int v = 0; v < vertices; v++) {
            this.indegree[v] = digraph.indegree(v);
        }
        for (int v = 0; v < digraph.vertices(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : digraph.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int vertices() {
        return vertices;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int edges() {
        return edges;
    }
    /**
     * Validate Vertex.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vertices - 1));
        }
    }

    /**
     * Adds the directed edge v→w to this digraph.
     *
     * @param  v the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless both
     * {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        edges++;
    }

    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *
     * @param  v the vertex
     * @return the vertices adjacent from vertex {@code v}
     * in this digraph, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(vertices);
        for (int v = 0; v < vertices; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

}
