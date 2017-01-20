package week4.practice.fastestRoute;

import java.util.Arrays;
import java.util.List;

import week4.practice.fastestRoute.WeightedGraph.Node;

public class NaiveAlgorithm {

	private WeightedGraph weightedGraph;
	private int distance[];
	private int prev[];

	public NaiveAlgorithm(WeightedGraph weightedGraph) {
		this.weightedGraph = weightedGraph;
		this.distance = new int[weightedGraph.n];
		this.prev = new int[weightedGraph.n];
	}

	private void computeFastestRoute(int start) {
		for (int index = 0; index < distance.length; index++) {
			distance[index] = Integer.MAX_VALUE;
			prev[index] = -1;
		}

		distance[start] = 0;

		boolean doRelax = true;

		while (doRelax) {
			doRelax = false;
			for (int index = 0; index < weightedGraph.n; index++) {
				List<Node> edges = weightedGraph.adj[index];
				for (Node nextNode : edges) {
					if (relax(index, nextNode)) {
						doRelax = true;
					}
				}
			}
		}
	}

	private boolean relax(int index, Node nextNode) {
		if (distance[nextNode.label] > distance[index] + nextNode.weight) {
			distance[nextNode.label] = distance[index] + nextNode.weight;
			prev[nextNode.label] = index;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		WeightedGraph weightedGraph = new WeightedGraph(5);
		
		weightedGraph.addEdge(0, weightedGraph.new Node(1, 5));
		weightedGraph.addEdge(0, weightedGraph.new Node(2, 10));
		weightedGraph.addEdge(1, weightedGraph.new Node(3, 1));
		weightedGraph.addEdge(3, weightedGraph.new Node(2, 3));
		weightedGraph.addEdge(3, weightedGraph.new Node(4, 15));
		weightedGraph.addEdge(2, weightedGraph.new Node(4, 2));
		
		NaiveAlgorithm naiveAlgorithm = new NaiveAlgorithm(weightedGraph);
		
		naiveAlgorithm.computeFastestRoute(0);
		
		System.out.println(Arrays.toString(naiveAlgorithm.prev));
	}
}
