import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for graph.
 */
public class Graph {
    /**
     *the variable to maintain vertex.
     */
    private final int vertices;
    /**
     *the variable to maintain edges.
     */
    private int edges;
    /**
     *the variable to adjacency matrix.
     */
    private boolean[][] adj;
    /**
     *the constructor to initialize.
     *
     * @param      vertices1  vertices
     */
    public Graph(final int vertices1) {
        if (vertices1 < 0) {
            throw new IllegalArgumentException("Too few vertices");
        }
        this.vertices = vertices1;
        this.edges = 0;
        this.adj = new boolean[vertices1][vertices1];
    }
    /**
     *
     *the method to return vertices.
     * @return vertices.
     */
    public int vertices() {
        return vertices;
    }
    /**
     *the method is to return edges.
     *
     * @return  edges.
     */
    public int edges() {
        return edges;
    }
    /**
     * adds an edge.
     *
     * @param      v  vertexOne
     * @param      w  vertexTwo
     */
    public void addEdge(final int v, final int w) {
        if (!adj[v][w]) {
            edges++;
        }
        adj[v][w] = true;
        adj[w][v] = true;
    }
    /**
     *the method returns whether there is.
     *a connection between two vertices.
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return connection is there are not.
     */

    /**
     * checks if contains.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(final int v, final int w) {
        return adj[v][w];
    }

    /**
     * return list of neighbors of v.
     *
     * @param      v  vertex
     *
     * @return  iterator
     */
    public Iterable<Integer> adj(final int v) {
        return new AdjIterator(v);
    }
    /**
     *support iteration over graph vertices.
     */
    private class AdjIterator implements Iterator<Integer>,
        Iterable<Integer> {
        /**
         * v.
         */
        private int v;
        /**
         * w.
         */
        private int w = 0;

        /**
         * Constructs the object.
         *
         * @param      v1    The v 1
         */
        AdjIterator(final int v1) {
            this.v = v1;
        }

        /**
         * iterator.
         *
         * @return     { description_of_the_return_value }
         */
        public Iterator<Integer> iterator() {
            return this;
        }

        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            while (w < vertices) {
                if (adj[v][w]) {
                    return true;
                }
                w++;
            }
            return false;
        }

        /**
         * next.
         *
         * @return     { description_of_the_return_value }
         */
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return w++;
        }

        /**
         * remove.
         */
        public void remove()  {
            throw new UnsupportedOperationException();
        }
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        return (adj[v][w]);
    }
    /**
     *string representation of Graph - takes quadratic time.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " " + edges + "\n");
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
