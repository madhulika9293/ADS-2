import java.util.Scanner;
/**
 *the class to check whether.
 *it percolates or not.
 */
class Percolation {
    /**
     *the variable is to store vertices.
     */
    private int vertices;
    /**
     *the constructor to initialize the vertices.
     *
     * @param      vertex  The vertex count
     */
    Percolation(final int vertex) {
        vertices = vertex;
    }
    /**
     *the method is to check whether the.
     *the grid percolates or not.
     *time complexity is O(N^2)
     *N is the vertices.
     * @param      grid   The grid
     * @param      graph  The graph
     *
     * @return whether is percolates or not.
     */
    public boolean percolates(final boolean[][] grid,
                              final Graph graph) {
    for (int i = 0; i < vertices; i++) {
        for (int j = 0; j < vertices; j++) {
            if (grid[i][j]) {
                int tmp = trans(i, j);
                if (i == 0) {
                    graph.addEdge(tmp,
                        vertices * vertices);
                }
                if (i == vertices - 1) {
                    graph.addEdge(tmp,
                        vertices * vertices + 1);
                }
                if (i - 1 >= 0 && grid[i - 1][j]) {
                    graph.addEdge(tmp,
                        trans(i - 1, j));
                }
                if (i + 1 < vertices  && grid[i + 1][j]) {
                    graph.addEdge(tmp,
                        trans(i + 1, j));
                }
                if (j - 1 >= 0 && grid[i][j - 1]) {
                    graph.addEdge(tmp, trans(i, j - 1));
                }
                if (j + 1 < vertices && grid[i][j + 1]) {
                    graph.addEdge(tmp, trans(i, j + 1));
                }
            }
        }
    }
    DepthFirstPaths object = new DepthFirstPaths(
        graph, vertices * vertices);
    return object.hasPathTo(vertices * vertices + 1);
    }
    /**
     *the method to convert the 2-D array.
     *to one-D value
     * @param      row row value
     * @param      col column value
     *
     * @return translated value
     */
    public int  trans(final int row, final int col) {
        return ((row * vertices) + col);
    }
}
/**
 *the class is to maintain the user input.
 */
final class Solution {
    /**
     *an empty constructor.
     */
    private Solution() {

    }
    /**
     *the main method to read the user input.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        Percolation obj = new Percolation(vertices);
        boolean[][] grid = new boolean[vertices][vertices];
        Graph graph = new Graph(vertices * vertices + 2);
        try {
            while (true) {
                int row = sc.nextInt();
                int col = sc.nextInt();
                grid[row - 1][col - 1] = true;
            }
        } catch (Exception e) {
        } finally {
            System.out.println(obj.percolates(grid, graph));
        }
    }
}
