/**.
 * Class for sap.
 */
public class SAP {
    /**.
     * { var_description }
     */
    private Digraph dig;
    /**.
     * { var_description }
     */
    private int ancestor = -1;
    /**.
     * { var_description }
     */
    private int len = Integer.MAX_VALUE;
    /**.
     * Constructs the object.
     *
     * @param      g     { parameter_description }
     */
    public SAP(final Digraph g) {
        dig = g;
    }
    /**.
     * { function_description }
     * time complexity is 1 in avg case
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final int v, final int w) {
        int a = ancestor(v, w);
        if (a == -1) {
            return -1;
        } else {
            return len;
        }
    }
    /**.
     * { function_description }
     * time complexity is O(N).
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final int v, final int w) {
        BreadthFirstSearch bfsv = new BreadthFirstSearch(dig, v);
        BreadthFirstSearch bfsw = new BreadthFirstSearch(dig, w);
        for (int i = 0; i < dig.vert(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int vlen = bfsv.distTo(i);
                int wlen = bfsw.distTo(i);
                if (vlen + wlen < len) {
                    len = vlen + wlen;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }
    /**.
     * { function_description }
     * time complexity is 1 in avg case
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final Iterable<Integer> v, final Iterable<Integer> w) {
        int a = ancestor(v, w);
        if (a == -1) {
            return -1;
        } else {
            return len;
        }
    }
    /**.
     * { function_description }
     * time complexity is O(N^2)
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final Iterable<Integer> v, final Iterable<Integer> w) {
        for (int i : v) {
            for (int j : w) {
                ancestor(i, j);
            }
        }
        return ancestor;
    }

    // do unit testing of this class
    // public static void main(String[] args)
}


