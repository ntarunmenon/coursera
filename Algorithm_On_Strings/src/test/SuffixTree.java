package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class WeightedGraph {
	public int  n = -1;
	public Map<Integer,List<SuffixNode>> adj= new HashMap<>();
    
    public void addEdge(int startNode, SuffixNode j) {
    	List<SuffixNode> nodes= adj.get(startNode);
    	if(nodes == null){
    		nodes = new ArrayList<>();
        }
    	nodes.add(j);
    	adj.put(startNode, nodes);
    }
    
    
    public boolean hasEdge(int i, int j) {
        return adj.get(i).contains(j);
    }
    
  
}

 
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
	public String toString() {
		return "endIndex " + endIndex + "   edgeLabel " +  edgeLabel;
	}
}


public class SuffixTree {
	
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
        List<String> result = new ArrayList<String>();
        WeightedGraph graph = new WeightedGraph();
        int edgeIndex = 0;
        edgeIndex++;
        SuffixNode node = new SuffixNode(edgeIndex, text.charAt(text.length() - 1)+"");
        graph.addEdge(0, node);
        
        for(int index = text.length() - 1; index >= 0; index--){
        	String currentSuffixString = text.substring(index,text.length());
	        int mapIndex = 0;
	        
	        /**
	        * This while does the following.
	        * 
	        *  1. For Each Suffix
	        *  	a. navigare the tree. If there is a match then either split or navigate the tree.
	        *   b. If there is no match then insert entry into the tree,
	        */
	      boolean stopCurrentSuffixIteration = false;
	        while(!stopCurrentSuffixIteration){
	            if(currentSuffixString.equals("$")){
	            	break;
		        }
	            
	        	/*
	        	 * Is the first character of the currentNode equal to the first character of the currentSuffixString
	        	 *  if Yes
	        	 */
	            List<SuffixNode> adjList = graph.adj.get(mapIndex);
	            if(adjList != null){
	            	boolean addNode=true;
	            	for(int nodeIndex = 0;nodeIndex < adjList.size(); nodeIndex ++){
		        		SuffixNode iterNode = adjList.get(nodeIndex);
			        	String iterNodeEdgeLabel = iterNode.edgeLabel;
						if(currentSuffixString.charAt(0) == iterNodeEdgeLabel.charAt(0)){
							if(iterNodeEdgeLabel.length() == 1){
			        			mapIndex = iterNode.endIndex;
			        			currentSuffixString = currentSuffixString.substring(1, currentSuffixString.length());
			        			addNode = false;
				        		break;
							}
							int newIndex = iterNode.endIndex;
							String newEdge = "";
							while(iterNodeEdgeLabel.length() > 0){
								if(currentSuffixString.charAt(0) == iterNodeEdgeLabel.charAt(0)){
									newEdge = newEdge + currentSuffixString.charAt(0);
									currentSuffixString = currentSuffixString.substring(1, currentSuffixString.length());
									iterNodeEdgeLabel = iterNodeEdgeLabel.substring(1, iterNodeEdgeLabel.length());
								}else{
									break;
								}
							}
							iterNode.edgeLabel=newEdge;
		        			graph.addEdge(newIndex, new SuffixNode(edgeIndex, iterNodeEdgeLabel));
		        			edgeIndex++;
		        			graph.addEdge(newIndex, new SuffixNode(edgeIndex, currentSuffixString));
		        			stopCurrentSuffixIteration = true;
		        			addNode=false;
		        			break;
							// Found a character match
			        	}
		        	}
	               	if(addNode){
		        		edgeIndex++;
		        		SuffixNode newNode=new SuffixNode(edgeIndex, currentSuffixString);
		        		graph.addEdge(mapIndex, newNode);
		        		stopCurrentSuffixIteration = true;
		        	}
	               	if(stopCurrentSuffixIteration){
		        		break;
		        	}
	            }else{
	            	edgeIndex++;
	        		SuffixNode newNode=new SuffixNode(edgeIndex, currentSuffixString);
	        		graph.addEdge(mapIndex, newNode);
	        		break;
	            }
	        }
	    }
        
        for( Map.Entry<Integer,List<SuffixNode>> entry :  graph.adj.entrySet()){
        	if( entry.getValue() != null &&  entry.getValue().size() > 0){
	        	for(SuffixNode suffixNode: entry.getValue()){
	        		if(suffixNode.edgeLabel != null && !suffixNode.edgeLabel.equals(""))	
	        		result.add(suffixNode.edgeLabel);
	        	}
        	}
        }
        return result;
    }


    static public void main(String[] args) throws IOException {
        new SuffixTree().run();
    }

    public void print(List<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text);
        print(edges);
    }
}
