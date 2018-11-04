/**.
 * { item_description }
 */
import java.util.Scanner;
/**.
 * { item_description }
 */
public final class Solution {
    /**.
     * Constructs the object.
     */
    private Solution() {
        /**.
         * { item_description }
         */
    }
    /**.
     * { function_description }
     *
     * @param      args  The arguments
     */
    // time complexity for the main method is 1.
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = "Files" + "\\" + sc.nextLine();
        String m = "Files" + "\\" + sc.nextLine();
        String word = sc.nextLine();
        try {
            WordNet wn = new WordNet(n, m);
            if (word.equals("Graph")) {
                wn.display();
            } else if (word.equals("Queries")) {
                while (sc.hasNextLine()) {
                    String[] tokens = sc.nextLine().split(" ");
                    String str = wn.sap(tokens[0], tokens[1]);
                    int id = wn.distance(tokens[0], tokens[1]);
                    System.out.println("distance = " + id
                        + ", ancestor = " + str);
                }
            }
        } catch (Exception e) {
            System.out.println("IllegalArgumentException");
        }
    }
}
