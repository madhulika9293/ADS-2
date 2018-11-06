import java.util.Scanner;
import java.util.HashMap;


/**
 * Class for solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {
		// unused
	}
	/**
	 * Main function.
	 *
	 * @param      args  The arguments
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String[] grInp = scan.nextLine().split(" ");
		int vertices = Integer.parseInt(grInp[0]);
		int edges = Integer.parseInt(grInp[1]);
		EdgeWeightedGraph eg = new EdgeWeightedGraph(vertices);

		HashMap<String, Integer> stations = new HashMap<>();
		String[] stNames = scan.nextLine().split(" ");
		for (int i = 0; i < stNames.length; i++) {
			stations.putIfAbsent(stNames[i], i);
		}

		for (int i = 0; i < edges; i++) {
			String[] inputs = scan.nextLine().split(" ");
			int v = stations.get(inputs[0]);
			int w = stations.get(inputs[1]);
			double wght = Double.parseDouble(inputs[2]);
			Edge e = new Edge(v, w, wght);
			eg.addEdge(e);
		}

		int queries = Integer.parseInt(scan.nextLine());

		for (int i = 0; i < queries; i++) {
			String[] inp = scan.nextLine().split(" ");
			int vertFrom  = stations.get(inp[0]);
			int vertTo  = stations.get(inp[1]);
			DijkstraUndirectedSP dusp = new DijkstraUndirectedSP(eg, vertFrom);
			System.out.println((int) dusp.distTo(vertTo));
		}
	}
}
