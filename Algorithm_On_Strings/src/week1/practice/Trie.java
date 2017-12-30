package week1.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import sun.applet.resources.MsgAppletViewer_pt_BR;

public class Trie {
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
    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
        trie.add(new HashMap<>());
        int terminalEdge = 0;
        for(String pattern:patterns){
        	int listIndex = 0;
        	for(int patternIndex = 0;patternIndex < pattern.length();patternIndex++){
	        	Map<Character, Integer> map = trie.get(listIndex);
	        	Character edgeLabel = pattern.charAt(patternIndex);
	        	if(map.containsKey(edgeLabel)){
	        		listIndex = map.get(edgeLabel);
	        	}else{
	        		terminalEdge++;
	        		listIndex = terminalEdge;
	        		map.put(edgeLabel, listIndex);
	        		trie.add(listIndex, new HashMap<>());
	        	}
	        }
       }
        return trie;
    }

    static public void main(String[] args) throws IOException {
        new Trie().run();
    }

    public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
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
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }
}
