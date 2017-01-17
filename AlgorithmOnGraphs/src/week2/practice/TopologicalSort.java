package week2.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import week1.practice.Graph;

/**
 * This is nothing but DFS and postorder
 * @author ntarunmenon
 *
 */
public class TopologicalSort {

	private boolean visitied[];
	private Graph graph;
	List<Integer> postVisitOrder = new ArrayList<>();

	public TopologicalSort(Graph graph) {
		visitied = new boolean[graph.n];
		this.graph = graph;
	}

	private void explore(Integer startNode) {
		visitied[startNode] = true;
		for (Integer nighbour : graph.adj[startNode]) {
			if (!visitied[nighbour]) {
				explore(nighbour);
			}
		}
		postVisit(startNode);
	}

	private void postVisit(Integer currentNode) {
		postVisitOrder.add(currentNode);
	}

	public static void main(String[] args) {
		Graph graph = new Graph(5);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(0, 4);
		graph.addEdge(0, 3);

		TopologicalSort sort = new TopologicalSort(graph);
		System.out.println(Arrays.toString(sort.dosort(0).toArray()));
	}

	private List<Integer> dosort(int startVertex) {
		explore(0);
		return this.postVisitOrder;
	}
}
