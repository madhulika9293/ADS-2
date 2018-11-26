/******************************************************************************
 *  Compilation:  javac GraphGenerator.java
 *  Execution:    java GraphGenerator V E
 *  Dependencies: Graph.java
 *
 *  A graph generator.
 *
 *  For many more graph generators, see
 *  http://networkx.github.io/documentation/latest/reference/generators.html
 *
 ******************************************************************************/


/**
 *  The {@code GraphGenerator} class provides static methods for creating
 *  various graphs, including Erdos-Renyi random graphs, random bipartite
 *  graphs, random k-regular graphs, and random rooted trees.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class GraphGenerator {
    // this class cannot be instantiated
    private GraphGenerator() { }

    /**
     * Returns a random simple graph containing {@code V} vertices and {@code E} edges.
     * @param V the number of vertices
     * @param E the number of vertices
     * @return a random simple graph on {@code V} vertices, containing a total
     *     of {@code E} edges
     * @throws IllegalArgumentException if no such simple graph exists
     */
    public static EdgeWeightedGraph simple(int V, int E) {
        if (E > (long) V * (V - 1) / 2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)                throw new IllegalArgumentException("Too few edges");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        SET<Edge> set = new SET<Edge>();
        while (G.E() < E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double uniRandW = StdRandom.uniform();
            Edge e = new Edge(v, w, uniRandW);
            if ((v != w) && !set.contains(e)) {
                set.add(e);
                G.addEdge(e);
            }
        }
        return G;
    }

    /**
     * Returns a random simple graph on {@code V} vertices, with an
     * edge between any two vertices with probability {@code p}. This is sometimes
     * referred to as the Erdos-Renyi random graph model.
     * @param V the number of vertices
     * @param p the probability of choosing an edge
     * @return a random simple graph on {@code V} vertices, with an edge between
     *     any two vertices with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static EdgeWeightedGraph simple(int V, double p) {
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        for (int v = 0; v < V; v++)
            for (int w = v + 1; w < V; w++)
                if (StdRandom.bernoulli(p)) {
                    double uniRandW = StdRandom.uniform();
                    Edge e = new Edge(v, w, uniRandW);
                    G.addEdge(e);
                }
        return G;
    }

    /**
     * Returns the complete graph on {@code V} vertices.
     * @param V the number of vertices
     * @return the complete graph on {@code V} vertices
     */
    public static EdgeWeightedGraph complete(int V) {
        return simple(V, 1.0);
    }

    public static void main(String[] args) {
        int numExp = Integer.parseInt(args[0]);

        switch (args[1]) {
        case "simple":
            int ver = Integer.parseInt(args[2]);
            int edg = Integer.parseInt(args[3]);
            double sum = 0.0;
            for (int i = 0; i < numExp; i++) {
                EdgeWeightedGraph eg = simple(ver, edg);
                LazyPrimMST lp = new LazyPrimMST(eg);

                MaxPQ<Edge> mx = new MaxPQ<>();
                for (Edge e : lp.edges()) {
                    mx.insert(e);
                }

                Edge longest = mx.delMax();

                int count = 0;

                for (Edge e : eg.edges()) {
                    if (e.compareTo(longest) < 0) {
                        count += 1;
                    }
                }
                // System.out.println(i + " " + (double) count / edg);
                double r1 = (double) count / edg;
                // System.out.println(i + " " + (double) r1);
                sum += r1;
            }
            // System.out.println((double) sum);
            System.out.printf("%.3f", sum/numExp);
        }
    }


}
