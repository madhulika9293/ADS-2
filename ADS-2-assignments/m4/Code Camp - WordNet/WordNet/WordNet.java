/**.
 * { item_description }
 */
import java.io.File;
/**.
 * { item_description }
 */

import java.util.Scanner;
/**.
 * { item_description }
 */
import java.util.ArrayList;
/**.
 * Class for word net.
 */
public class WordNet {
    /**.
     * { var_description }
     */
    private SAP sap;
    /**.
     * { var_description }
     */
    private Digraph dg;
    /**.
     * { item_description }
     */
    private HashTable<String, ArrayList<Integer>> htable;
    /**.
     * { var_description }
     */
    private HashTable<Integer, String> htable1;
    /**.
     * { var_description }
     */
    private int ver = 0;
    /**.
     * Constructs the object.
     * @throws     Exception  { exception_description }
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    WordNet(final String synsets, final String hypernyms) throws Exception {
        readSynsets(synsets);
        readHypernyms(hypernyms);
        // dg = new Digraph(ver);
        // readHypernyms(hypernyms);
        // sap = new SAP(dg);
    }
    /**.
     * Reads synsets.
     *
     * @param      synsets    The synsets
     *
     * @throws     Exception  { exception_description }
     */
    public void readSynsets(final String synsets) throws Exception {
        htable = new HashTable<String, ArrayList<Integer>>();
        htable1 = new HashTable<Integer, String>();
        int id = 0;
            Scanner synIn = new Scanner(new File(synsets));
            while (synIn.hasNextLine()) {
                ver++;
                // String line = synIn.readString();
                String[] tokens = synIn.nextLine().split(",");
                id = Integer.parseInt(tokens[0]);
                htable1.put(id, tokens[1]);
                String[] word = tokens[1].split(" ");
                for (int i = 0; i < word.length; i++) {
                    if (htable.contains(word[i])) {
                        ArrayList<Integer> list = htable.get(word[i]);
                        list.add(id);
                        htable.put(word[i], list);
                    } else {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(Integer.parseInt(tokens[0]));
                        htable.put(word[i], list);
                    }
                }
            }
    }
    /**.
     * Reads hypernyms.
     *
     * @param      hypernyms  The hypernyms
     *
     * @throws     Exception  { exception_description }
     */
    public void readHypernyms(final String hypernyms) throws Exception {
        dg = new Digraph(ver);
        Scanner hyperIn = new Scanner(new File(hypernyms));
        while (hyperIn.hasNextLine()) {
            // String line = ;
            String[] tokens = hyperIn.nextLine().split(",");
            for (int i = 1; i < tokens.length; i++) {
                dg.addEdge(Integer.parseInt(tokens[0]),
                 Integer.parseInt(tokens[i]));
            }
        }
    }
    /**.
     * { function_description }
     */
    public void display() {
        int c = 0;
        DirectedCycle dc = new DirectedCycle(dg);
        for (int i = 0; i < ver; i++) {
            if (dg.outdegree(i) == 0) {
                c++;
            }
        }
        if (c > 1) {
            System.out.println("Multiple roots");
        } else if (dc.hasCycle()) {
            System.out.println("Cycle detected");
        } else {
            System.out.println(dg);
        }
    }
    /**.
     * { function_description }
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public int distance(final String nounA, final String nounB) {
        sap = new SAP(dg);
        int dist = sap.length(htable.get(nounA), htable.get(nounB));
        return dist;
    }
    /**.
     * { function_description }
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public String sap(final String nounA, final String nounB) {
        sap = new SAP(dg);
        String str = "";
        int id = sap.ancestor(htable.get(nounA), htable.get(nounB));
        return htable1.get(id);
    }
}
