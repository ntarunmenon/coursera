package week3.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import week1.practice.Graph;

public class ShortestPathTree {

	private Graph graph;
	private Queue<Integer> visitQueue = new LinkedList<>();
	private int distance[];
	private int prev[];

	ShortestPathTree(Graph graph) {
		this.graph = graph;
		distance = new int[graph.n];
		prev = new int[graph.n];
	}

	public void doBFSExplore(int startNode) {
		for (int i = 0; i < distance.length; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[startNode] = 0;

		visitQueue.add(startNode);

		while (!visitQueue.isEmpty()) {
			int currentNode = visitQueue.remove();
			for (int index = 0; index < graph.adj[currentNode].size(); index++) {
				int nextNode = graph.adj[currentNode].get(index);
				if (distance[nextNode] == Integer.MAX_VALUE) {
					visitQueue.add(nextNode);
					distance[nextNode] = distance[currentNode] + 1;
					prev[nextNode] = currentNode;
				}
			}
		}
	}

	public String findShortestPath(int toVertex, int origin) {
		doBFSExplore(origin);
		List<Integer> shortestPath = new ArrayList<>();
		while (toVertex != origin) {
			shortestPath.add(toVertex);
			toVertex = prev[toVertex];
		}
		Collections.reverse(shortestPath);
		return Arrays.toString(shortestPath.toArray());
	}

	public static void main(String[] args) {
		Graph graph = new Graph(10);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(0, 4);
		graph.addEdge(1, 5);
		graph.addEdge(1, 6);
		graph.addEdge(1, 7);
		graph.addEdge(2, 8);
		graph.addEdge(8, 9);

		ShortestPathTree breadthFirstSearch = new ShortestPathTree(graph);

		System.out.println(breadthFirstSearch.findShortestPath(9, 0));
	}
}
