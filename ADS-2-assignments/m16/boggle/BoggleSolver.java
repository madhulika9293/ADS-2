import java.util.HashSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
	// Initializes the data structure using
	// the given array of strings as the dictionary.
	// (You can assume each word in the dictionary
	// contains only the uppercase letters A through Z.)
	private TrieSET dict, twoLetPref;
	public BoggleSolver(String[] dictionary) {
		for (String word : dictionary) {
			dict.add(word);
			twoLetPref.add(word.substring(0, 2));
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		HashSet<String> validWords = new HashSet<String>();

		int r = board.rows();
		int c = board.cols();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				boolean[][] visited = new boolean[r][c];
				collect(board, i, j, visited, "", validWords);
			}
		}
		return validWords;
	}
	private void collect(BoggleBoard board, int row, int col, boolean[][] visited, String prefix, HashSet<String> set) {
		if (visited[row][col]) {
			return;
		}

		char letter = board.getLetter(row, col);
		String word = prefix;

		if (letter == 'Q') {
			word += "QU";
		} else {
			word += letter;
		}

		if (!dict.hasPrefix(word)) {
			return;
		}

		if (word.length() > 2 && dict.contains(word)) {
			set.add(word);
		}

		visited[row][col] = true;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}

				if ((row + i >= 0) && (row + i < board.rows()) && (col + j >= 0) && (col + j < board.cols())) {
					collect(board, row + i, col + j, visited, word, set);
				}
			}
		}

		visited[row][col] = false;
	}
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if (dict.contains(word)) {
			int t = word.length();
			if (t > 2) {
				return t;
			}
			return 0;
		}
		return 0;
	}
}
