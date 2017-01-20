package week4.practice.fastestRoute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeightedGraph {
	public int  n = -1;
	public List<Node>[] adj;
    public WeightedGraph(int n) {
    	this.n = n;
    	adj = new List[n];
    	for (int i = 0; i < n; i++) 
        adj[i] = new ArrayList<Node>();
    }
    
    public void addEdge(int startNode, Node j) {
        adj[startNode].add(j);
    }
    
    public void removeEdge(Node start, Node end) {
    	 Iterator<Node> it = adj[start.label].iterator();
    	 while (it.hasNext()) {
    		 if (it.next().label == end.label) {
    			  it.remove();
                  return;
    		 }
    	 }
    }
    
    public boolean hasEdge(int i, int j) {
        return adj[i].contains(j);
    }
    
    public class Node {
    	public int label;
    	public int weight;
		
    	
    	public Node(int label, int weight) {
			super();
			this.label = label;
			this.weight = weight;
		}


		@Override
		public String toString() {
			return "Node [label=" + label + ", weight=" + weight + "]";
		}
    	
    }
}
