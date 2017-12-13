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
    
    public void removeEdge(SuffixNode start, SuffixNode end) {
    	 Iterator<SuffixNode> it = adj.get(start.startIndex).iterator();
    	 while (it.hasNext()) {
    		 if (it.next().startIndex == end.startIndex) {
    			  it.remove();
                  return;
    		 }
    	 }
    }
    
    public boolean hasEdge(int i, int j) {
        return adj.get(i).contains(j);
    }
    
  
}

 
class SuffixNode {
	public int startIndex;
	public int endIndex;
	public String edgeLabel;
	public boolean visited;
	
	public SuffixNode(int startNode, int endNode, String path) {
		super();
		this.startIndex = startNode;
		this.endIndex = endNode;
		this.edgeLabel = path;
		visited = false;
		
	}

	

	public boolean equals(Object arg0) {
		return startIndex == ((SuffixNode)arg0).startIndex;
	}



	@Override
	public String toString() {
		return "Node [startIndex=" + startIndex + ", endIndex=" + endIndex + ", path=" + edgeLabel + "]";
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
        int egdeLabel = 0;
        egdeLabel++;
        SuffixNode node = new SuffixNode(egdeLabel -1 ,egdeLabel, text.charAt(text.length() - 1)+"");
        graph.addEdge(0, node);
        
        for(int index = text.length() - 1; index >= 0; index--){
        	String currentSuffixString = text.substring(index,text.length());
	        int mapIndex = 0;
	        int listIndex = 0;
	        while(true){
	        	SuffixNode currentNode= graph.adj.get(mapIndex).get(listIndex);
	            if(currentSuffixString.equals("$")){
	            	break;
		        }
	            
	        	/*
	        	 * Is the first character of the currentNode equal to the first character of the currentSuffixString
	        	 *  if Yes
	        	 */
	            List<SuffixNode> adjList = graph.adj.get(currentNode.startIndex);
	            if(adjList != null){
		        	for(int nodeIndex = 0;nodeIndex < adjList.size(); nodeIndex ++){
			        	SuffixNode iterNode = adjList.get(nodeIndex);
						if(currentSuffixString.charAt(0) == iterNode.edgeLabel.charAt(0)){
			        		
			        		/**
			        		 * The assumption is that if the current label is a string of length one then there is no need for a split then we have to naivgate.
			        		 * 
			        		 * If the first character matches and then length of the label is greater than 1 then split.
			        		 *  -- For splitting do the following things.
			        		 *  	-- Substring the label taking the first character out.
			        		 *  	-- The first character will be the new node.
			        		 *  	-- The substring will be the one node.
			        		 *  	-- The character after removing the first node will be the second child.
			        		 * else navigate
			        		 */
			        		
			        		System.out.println("Found a Match with character" + iterNode.edgeLabel.charAt(0));
			        		if(iterNode.edgeLabel.length() > 1){
			        			String leftNode = iterNode.edgeLabel.substring(1, iterNode.edgeLabel.length());
			        			egdeLabel++;
			        			graph.addEdge(iterNode.endIndex, new SuffixNode(iterNode.endIndex, egdeLabel, leftNode));
			        			String rightNode = currentSuffixString.substring(1, currentSuffixString.length());
			        			egdeLabel++;
			        			graph.addEdge(iterNode.endIndex, new SuffixNode(iterNode.endIndex, egdeLabel, rightNode));
			        			iterNode.edgeLabel=iterNode.edgeLabel.charAt(0)+"";
			        		}else{
			        			mapIndex = iterNode.endIndex;
			        			listIndex = nodeIndex;
			        		}
			        		break;
			        	}else{	
			        		egdeLabel++;
			        		SuffixNode newNode=new SuffixNode(currentNode.startIndex, egdeLabel, currentSuffixString);
			        		graph.addEdge(currentNode.startIndex, newNode);
			        		break;
			        	}
		        	}
	            }else{
	            	egdeLabel++;
	        		SuffixNode newNode=new SuffixNode(currentNode.startIndex, egdeLabel, currentSuffixString);
	        		graph.addEdge(currentNode.startIndex, newNode);
	        		break;
	            }
        	break;
	        }
	    }
        
        for( Map.Entry<Integer,List<SuffixNode>> entry :  graph.adj.entrySet()){
        	if( entry.getValue() != null &&  entry.getValue().size() > 0){
	        	for(SuffixNode suffixNode: entry.getValue()){
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
