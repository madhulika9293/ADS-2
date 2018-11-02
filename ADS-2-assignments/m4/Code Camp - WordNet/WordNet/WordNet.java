import java.util.Arrays;
import java.util.TreeMap;


public class WordNet {
  /**
   * id and word map.
   */
  private TreeMap<Integer, Bag<String>> synMap;
  /**
   * word, Id maps.
   */
  private TreeMap<String, Integer> wrdMap;
  /**
   * Digraph.
   */
  private Digraph wNGraph;

  /**
   * Constructs the object.
   *
   * @param      synsets    The synsets
   * @param      hypernyms  The hypernyms
   */
  public WordNet(String synsets, String hypernyms) {
    In synIn = new In(synsets);
    In hypIn = new In(hypernyms);

    String[] syn = synIn.readAllLines();
    String[] hyp = hypIn.readAllLines();

    synMap = new TreeMap<>();
    wrdMap = new TreeMap<>();

    // System.out.println(Arrays.toString(syn));
    for (String item : syn) {
      // System.out.println(item);
      String[] temp = item.split(",");
      Bag<String> tS = new Bag<String>();
      String[] numWords = temp[1].split(" ");
      for (String word : numWords) {
        tS.add(word);
        // System.out.println(temp[0]);
        wrdMap.put(word, Integer.parseInt(temp[0]));
      }
      synMap.put(Integer.parseInt(temp[0]), tS);
    }

    // System.out.println(synMap);
    // System.out.println(wrdMap);

    wNGraph = new Digraph(syn.length);
    for (String link : hyp) {
      String[] ln = link.split(",");
      for (int i = 1; i < ln.length; i++) {
        wNGraph.addEdge(Integer.parseInt(ln[0]), Integer.parseInt(ln[i]));
      }
    }
  }

  /**
   * Gets the digraph.
   *
   * @return     The digraph.
   */
  public Digraph getDi() {
    return wNGraph;
  }
  // returns all WordNet nouns
  public Iterable<String> nouns() {
    return wrdMap.keySet();
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    return wrdMap.keySet().contains(word);
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {

    return 0;
  }

  // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // // in a shortest ancestral path (defined below)
  // public String sap(String nounA, String nounB)

  // do unit testing of this class
  public static void main(String[] args) {

  }
}
