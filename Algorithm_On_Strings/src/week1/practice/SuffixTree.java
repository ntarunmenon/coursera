package week1.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class SuffixNode {
	public int endIndex;
	public String edgeLabel;
	public boolean visited;

	public SuffixNode(int endNode, String path) {
		super();
		this.endIndex = endNode;
		this.edgeLabel = path;
		visited = false;
	}

	@Override
	public boolean equals(Object obj) {
		SuffixNode other = (SuffixNode) obj;

		if (other != null && other.endIndex == this.endIndex && other.edgeLabel != null
				&& other.edgeLabel.equals(this.edgeLabel)) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "endIndex=" + endIndex + "   edgeLabel=" + edgeLabel;
	}
}

public class SuffixTree {
	List<String> paths = new ArrayList<String>();
	int edgeIndex = 0;
	public Map<Integer, List<SuffixNode>> adj = new HashMap<>();
	private boolean[] visited;

	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}

		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	// Build a suffix tree of the string text and return a list
	// with all of the labels of its edges (the corresponding
	// substrings of the text) in any order.
	public List<String> computeSuffixTreeEdges(String text) {
		for (int index = text.length() - 1; index >= 0; index--) {
			String currentSuffixString = text.substring(index, text.length());
			int currentIndexinGraph = 0;

			boolean stopCurrentSuffixIteration = false;
			while (!stopCurrentSuffixIteration) {
				List<SuffixNode> adjList = adj.get(currentIndexinGraph);
				if (adjList != null) {
					int matchNodeIndex = -1;

					for (int nodeIndex = 0; nodeIndex < adjList.size(); nodeIndex++) {
						SuffixNode node = adjList.get(nodeIndex);
						if (node.edgeLabel.charAt(0) == currentSuffixString.charAt(0)) {
							matchNodeIndex = nodeIndex;
							break;
						}
					}
					if (matchNodeIndex != -1) {
						SuffixNode node = adjList.get(matchNodeIndex);
						// If the length of the node in graph is 1
						if (node.edgeLabel.length() == 1) {
							currentSuffixString = currentSuffixString.substring(1);
							currentIndexinGraph = node.endIndex;
						} else {
							int noOfCommonMatches = findNoOfCommonMatches(node.edgeLabel, currentSuffixString);
							String currentNode = node.edgeLabel;
							node.edgeLabel = currentNode.substring(0, noOfCommonMatches);
							if(!(noOfCommonMatches == currentNode.length())){
								currentNode = currentNode.substring(noOfCommonMatches, currentNode.length());
								currentIndexinGraph = addnewNode(currentNode, node.endIndex);
							}
							currentSuffixString = currentSuffixString.substring(noOfCommonMatches,
									currentSuffixString.length());
							currentIndexinGraph = addnewNode(currentSuffixString, node.endIndex);
							stopCurrentSuffixIteration = true;
						}
					} else {
						currentIndexinGraph = addnewNode(currentSuffixString, currentIndexinGraph);
						stopCurrentSuffixIteration = true;
					}

				} else {
					currentIndexinGraph = addnewNode(currentSuffixString, currentIndexinGraph);
					stopCurrentSuffixIteration = true;
				}
			}
		}

		List<String> result = new ArrayList<String>();
		for (Map.Entry<Integer, List<SuffixNode>> entry : adj.entrySet()) {
			if (entry.getValue() != null && entry.getValue().size() > 0) {
				for (SuffixNode suffixNode : entry.getValue()) {
					if (suffixNode.edgeLabel != null && !suffixNode.edgeLabel.equals(""))
						result.add(suffixNode.edgeLabel);
				}
			}
		}
		System.out.println();
		visited = new boolean[edgeIndex + 1];
		paths.add("");
		explore(0);
		return result;
	}

	private void explore(Integer startNode) {
		visited[startNode] = true;
		
		if(!adj.containsKey(startNode)){
			printPath();
			paths.remove(paths.size() - 1);
			return;
		}
		
		
		List<SuffixNode> nodes = adj.get(startNode);
		for (SuffixNode neighbour : nodes) {
			if (!visited[neighbour.endIndex]) {
				neighbour.visited = true;
				paths.add(neighbour.edgeLabel);
				explore(neighbour.endIndex);
			}
		}
		paths.remove(paths.size() - 1);
	}

	private void printPath() {
		String path = "";
		for (String node : paths) {
			path = path + node;
		}
		System.out.println(path);
	}

	private int findNoOfCommonMatches(String edgeLabel, String currentSuffixString) {
		int index = 0;
		while (index < edgeLabel.length() && edgeLabel.charAt(index) == currentSuffixString.charAt(index)) {
			index++;
		}
		return index;
	}

	private int addnewNode(String currentSuffixString, int currentIndexinGraph) {
		edgeIndex++;
		SuffixNode newNode = new SuffixNode(edgeIndex, currentSuffixString);
		if (adj.get(currentIndexinGraph) != null) {
			adj.get(currentIndexinGraph).add(newNode);
		} else {
			List<SuffixNode> node = new ArrayList<>();
			node.add(newNode);
			adj.put(currentIndexinGraph, node);
		}
		return edgeIndex;
	}

	static public void main(String[] args) throws IOException {
		new SuffixTree().run();
	}

	public void print(List<String> x) {
		for (String a : x) {
			//System.out.println(a);
		}
	}

	public void run() throws IOException {
		FastScanner scanner = new FastScanner();
		String text = scanner.next();
		List<String> edges = computeSuffixTreeEdges(text);
		print(edges);
	}
}
