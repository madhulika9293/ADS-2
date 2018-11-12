import java.util.Scanner;
import java.util.Arrays;

/**
 * Class for Solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {
		// unused
	}
	/**
	 * Main method.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int numInp = Integer.parseInt(scan.nextLine());

		String[] inp = new String[numInp];
		for (int i = 0; i < numInp; i++) {
			inp[i] = scan.nextLine();
		}
		// System.out.println(Arrays.toString(inp));
		Quick3string.sort(inp);
		System.out.println(Arrays.toString(inp));
		
	}
}
