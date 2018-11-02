/**
 * Class for Solutipn.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {
		// unused constructor
	}
	public static void main(String[] args) {
		// Scanner scan = new Scanner(System.in);
		In in = new In();
		String synInp = "Files/" + in.readString();
		String hypInp = "Files/" + in.readString();

		WordNet wn = new WordNet(synInp, hypInp);
		// System.out.println(wn.nouns());
		// System.out.println(wn.isNoun("a"));

		String token = in.readString();
		switch (token) {
		case "Graph":
			System.out.println(wn.getDi());
			break;
		case "Queries":
			System.out.println("q");
			break;
		default:
			break;
		}
	}
}
