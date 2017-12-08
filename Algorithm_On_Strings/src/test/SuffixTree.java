package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    	 Iterator<SuffixNode> it = adj.get(start.label).iterator();
    	 while (it.hasNext()) {
    		 if (it.next().label == end.label) {
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
	public int label;
	public String path;
	
	
	public SuffixNode(int labe, String path) {
		super();
		this.label = label;
		this.path = path;
	}


	@Override
	public String toString() {
		return "Node [label=" + label + ", path=" + path + "]";
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
        for(int index = text.length() - 1; index >= 0; index--){
        	graph.addEdge(egdeLabel, new SuffixNode(++egdeLabel, text.substring(index, text.length())));
        }
        
        for(Map.Entry<Integer, List<SuffixNode>> entry:graph.adj.entrySet()){
			List<SuffixNode> nodes =  entry.getValue();
			for(SuffixNode node: nodes){
				result.add(node.path);
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
