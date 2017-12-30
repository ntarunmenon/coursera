package week1.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import sun.applet.resources.MsgAppletViewer_pt_BR;

public class TrieWithNode {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return Node.NA;
		}
	}
	
	char indexToLetter (int letter)
	{
		switch (letter)
		{
			case 0: return 'A';
			case 1 : return 'C';
			case 2: return 'G';
			case 3: return 'T';
			default: assert (false); return 'N';
		}
	}
	
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

    /*
     * The index of the list is the initial node of the tree.
     * The value is the terminal edge
     * The key is the label of the edge
     */
	List<Node> buildTrie(String[] patterns) {
        List<Node> trie = new ArrayList<Node>();
        trie.add(new Node());
        int terminalEdge = 0;
        for(String pattern:patterns){
        	int listIndex = 0;
        	for(int patternIndex = 0;patternIndex < pattern.length();patternIndex++){
	        	Node node = trie.get(listIndex);
	        	Character edgeLabel = pattern.charAt(patternIndex);
	        	if(node.next[letterToIndex(edgeLabel)] != Node.NA){
	        		listIndex = node.next[letterToIndex(edgeLabel)];
	        	}else{
	        		terminalEdge++;
	        		listIndex = terminalEdge;
	        		node.next[letterToIndex(edgeLabel)] = listIndex;
	        		trie.add(listIndex, new Node());
	        	}
	        	if(patternIndex == pattern.length() - 1){
	        		trie.get(listIndex).patternEnd = true;
	        	}
	        }
       }
        return trie;
    }


    static public void main(String[] args) throws IOException {
        new TrieWithNode().run();
    }

    public void print(List<Node> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Node node = trie.get(i);
            if(node.patternEnd){
            	System.out.println("Node at index" + i + "pattern end");
            }
            for (int index = 0; index < node.next.length;index++) {
            	if(node.next[index] != Node.NA){
                	System.out.println(i + "->" + node.next[index] + ":" +indexToLetter(index));
                }
            }
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Node> trie = buildTrie(patterns);
        print(trie);
    }
}
