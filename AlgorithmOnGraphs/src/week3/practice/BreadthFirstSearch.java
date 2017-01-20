package week3.practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import week1.practice.Graph;

public class BreadthFirstSearch {

	private Graph graph;
	private Queue<Integer> visitQueue = new LinkedList<>();
	private int distance[];
	BreadthFirstSearch(Graph graph){
		this.graph = graph;
		distance = new int[graph.n];
	}
	public void doBFSExplore(int startNode){
		for (int i = 0; i < distance.length; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[startNode] = 0;
		
		visitQueue.add(startNode);
		
		while (!visitQueue.isEmpty()) {
			int currentNode = visitQueue.remove();
			for(int index = 0; index < graph.adj[currentNode].size();index++){
				int nextNode = graph.adj[currentNode].get(index);
				if(distance[nextNode] == Integer.MAX_VALUE){
					visitQueue.add(nextNode);
					distance[nextNode] = distance[currentNode] + 1;
				}
			}
		}
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
		
		BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(graph);
		breadthFirstSearch.doBFSExplore(0);
		System.out.println(Arrays.toString(breadthFirstSearch.distance));
	}
}
