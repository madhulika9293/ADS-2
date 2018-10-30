import java.util.Scanner;
/**
 * Interface for graph.
 */
interface Graph {
    /**
     * number of vertices.
     *
     * @return     { description_of_the_return_value }
     */
    int vertices();
    /**
     * number of edges.
     *
     * @return     { description_of_the_return_value }
     */
    int edges();
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    void addEdge(int v, int w);
    /**
     * iterable.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // empty constructor.
    }
    /**
     * main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        int v = Integer.parseInt(scan.nextLine());
        int e = Integer.parseInt(scan.nextLine());
        int temp = e;
        String[] data = scan.nextLine().split(",");
        if (s.equals("List")) {
            GraphList list = new GraphList(v);
            while (temp > 0) {
                String[] fun = scan.nextLine().split(" ");
                list.addEdge(Integer.parseInt(fun[0]),
                             Integer.parseInt(fun[1]));
                temp--;
            }
            System.out.println(list.display(data));
        } else if (s.equals("Matrix")) {
            temp = e;
            GraphMatrix sol = new GraphMatrix(v);
            while (temp > 0) {
                String[] fun = scan.nextLine().split(" ");
                sol.addEdge(Integer.parseInt(fun[0]),
                            Integer.parseInt(fun[1]));
                temp--;
            }
            System.out.println(sol);
        }
    }
}

