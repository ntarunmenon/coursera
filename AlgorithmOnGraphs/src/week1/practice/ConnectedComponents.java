package week1.practice;

import java.util.List;

public class ConnectedComponents {

	private boolean visitied[];
	private Graph graph;
	
	public  ConnectedComponents(Graph graph){
		visitied = new boolean[graph.n];
		this.graph = graph;
	}
	
	public void explore(Integer startNode){
		System.out.println("Exploring Node " + startNode);
		for(Integer nighbour:graph.adj[0]){
			if(!visitied[nighbour])
				explore(startNode);
		}
	}
}
