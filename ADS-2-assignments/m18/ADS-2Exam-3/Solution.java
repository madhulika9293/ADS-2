import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
// import java.util.Set;
// import java.util.HashSet;
import java.util.HashMap;

/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // unused constructor
    }
    // Don't modify this method.

    /**
     * main.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash =
                loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.

    /**
     * reads.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static BinarySearchST<String, Integer>
    loadDictionary(final String file) {
        BinarySearchST<String, Integer>  st =
            new BinarySearchST<String, Integer>();
        // your code goes here
        String[] sms = toReadFile(file);
        // System.out.println(Arrays.toString(sms));
        for (String word : sms) {
            word = word.toLowerCase();
            if (st.contains(word)) {
                st.put(word, st.get(word) + 1);
            } else {
                st.put(word, 1);
            }
        }
        // for (String word : st.keys()) {
        //  System.out.println(word + " " + st.get(word));
        // }
        return st;
    }

}
/**
 * Class for t 9.
 */
class T9 {
    /**
     * words.
     */
    private static TST<Integer> smsWords;
    /**
     * keyboard.
     */
    private HashMap<String, ArrayList<String>> keyboard;
    // private static HashMap<String, Integer> output;

    /**
     * out.
     */
    private static ArrayList<String> output;
    /**
     * Constructs the object.
     *
     * @param      st    symbol table.
     */
    public T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        smsWords = new TST();
        for (String word : st.keys()) {
            smsWords.put(word, st.get(word));
            // System.out.println(word + " " + st.get(word));
        }
        keyboard = new HashMap<>();
        keyboard.putIfAbsent("2", new ArrayList<String>());
        keyboard.get("2").add("a");
        keyboard.get("2").add("b");
        keyboard.get("2").add("c");

        keyboard.putIfAbsent("3", new ArrayList<String>());
        keyboard.get("3").add("d");
        keyboard.get("3").add("e");
        keyboard.get("3").add("f");

        keyboard.putIfAbsent("4", new ArrayList<String>());
        keyboard.get("4").add("g");
        keyboard.get("4").add("h");
        keyboard.get("4").add("i");

        keyboard.putIfAbsent("5", new ArrayList<String>());
        keyboard.get("5").add("j");
        keyboard.get("5").add("k");
        keyboard.get("5").add("l");

        keyboard.putIfAbsent("6", new ArrayList<String>());
        keyboard.get("6").add("m");
        keyboard.get("6").add("n");
        keyboard.get("6").add("o");

        keyboard.putIfAbsent("7", new ArrayList<String>());
        keyboard.get("7").add("p");
        keyboard.get("7").add("q");
        keyboard.get("7").add("r");
        keyboard.get("7").add("s");

        keyboard.putIfAbsent("8", new ArrayList<String>());
        keyboard.get("8").add("t");
        keyboard.get("8").add("u");
        keyboard.get("8").add("v");

        keyboard.putIfAbsent("9", new ArrayList<String>());
        keyboard.get("9").add("w");
        keyboard.get("9").add("x");
        keyboard.get("9").add("y");
        keyboard.get("9").add("z");

    }

    // get all the prefixes that match with given prefix.

    /**
     * Gets all words.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return smsWords.keysWithPrefix(prefix);
    }

    /**
     * { function_description }.
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
        output = new ArrayList<String>();
        String[] input = t9Signature.split("");
        findComb(keyboard, input, "", input.length - 1);
        ArrayList<String> uOut = new ArrayList<>();
        for (String word : output) {
            if (!uOut.contains(word) && word.length() ==
                    t9Signature.length()) {
                uOut.add(word);
            } else {
                continue;
            }
        }
        Collections.sort(uOut);
        return uOut;
    }

    /**
     * finds comb.
     *
     * @param      keyboard  The t 9 signature
     * @param      inp  The t 9 signature
     * @param      res  The t 9 signature
     * @param      index  The t 9 signature
     *
     */
    public static void findComb(final HashMap<String,
                                ArrayList<String>> keyboard,
                                final String[] inp,
                                String res, int index) {
        if (index == -1) {
            if (smsWords.contains(res)) {
                // output.putIfAbsent(res, 0);
                output.add(res);
                res = "";
                return;
            }
            res = "";
            return;
        }

        String d = inp[index];
        int len = keyboard.get(d).size();
        for (int i = 0; i < len; i++) {
            findComb(keyboard, inp, keyboard.get(d).get(i) + res, index - 1);
        }
    }

    // return all possibilities(words), find top k with highest frequency.

    /**
     * Gets the suggestions.
     *
     * @param      words  The words
     * @param      k      { parameter_description }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String> words,
                                           final int k) {
        // your code goes here
        HashMap<Integer, String> wtable = new HashMap<>();
        for (String w : words) {
            for (String sw : getAllWords(w)) {
                int temp = smsWords.get(sw);
                if (wtable.containsKey(temp)) {
                    continue;
                } else {
                    wtable.put(temp, sw);
                }
            }
        }
        Object[] keys = wtable.keySet().toArray();
        Arrays.sort(keys);
        ArrayList<String> out = new ArrayList<>();
        for (int i = keys.length - 1; i >= 0 && i > keys.length - 1 - k; i--) {
            // System.out.println(wtable.get(keys[i]));
            out.add(wtable.get(keys[i]));
        }
        Collections.sort(out);
        return out;
    }

    // final output
    // Don't modify this method.

    /**
     * { function_description }.
     *
     * @param      t9Signature  The t 9 signature
     * @param      k            { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
