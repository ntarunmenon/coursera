package week1.exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {
	private int  n = -1;
    private List<Integer>[] adj;
	
    public Graph(int n) {
    	this.n = n;
    	adj = new List[n];
    	for (int i = 0; i < n; i++) 
        adj[i] = new ArrayList<Integer>();
    }
    
    public void addEdge(int i, int j) {
        adj[i].add(j);
    }
    
    public void removeEdge(int i, int j) {
    	 Iterator<Integer> it = adj[i].iterator();
    	 while (it.hasNext()) {
    		 if (it.next() == j) {
    			  it.remove();
                  return;
    		 }
    	 }
    }
    
    public boolean hasEdge(int i, int j) {
        return adj[i].contains(j);
    }
}
