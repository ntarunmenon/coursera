import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Supplier;

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

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
        int length = Arrays.stream(patterns).mapToInt(String::length).sum();
        
        List<Integer>[] adj = (List<Integer>[])new ArrayList[length];
        List<Character>[] symbol = (List<Character>[])new ArrayList[length];
        
        Supplier<ArrayList<Integer>> supplier = () -> new ArrayList<Integer>();
        Supplier<ArrayList<Character>> charSupplier = () -> new ArrayList<Character>();
        Arrays.setAll(adj, i -> supplier.get());
        Arrays.setAll(symbol, i -> charSupplier.get());
        
        
        int counter = 1;
        for(String pattern:patterns){
        	int patternIndex = 0;
        	int treeIndex = 0;
        	boolean isPatternComplete = false;
        	while(!isPatternComplete){
        		Character charac =  pattern.charAt(patternIndex);
        		if(!symbol[treeIndex].contains(charac)){
        			adj[treeIndex].add(counter);
        			symbol[treeIndex].add(charac);
        			treeIndex = counter;
        			counter++;
        		}else{
        			treeIndex++;
        		}
        		
        		patternIndex++;
        		
        		if(patternIndex == pattern.length()){
        			isPatternComplete = true;
        		}
        	}
        	
        	
        	
        	/*for(int index = 0; index < pattern.length();index++){
        		Character character =  pattern.charAt(index);
        		if(!symbol[index].contains(character)){
        			adj[index].add(counter);
        			symbol[index].add(character);
        			counter++;
        		}else{
        			
        		}
        }*/
        }
        
        for(int index = 0; index < symbol.length; index++){
        	Map<Character, Integer> map = new HashMap<>();
        	for(int symbIndex = 0 ; symbIndex < symbol[index].size();symbIndex++){
        		map.put(symbol[index].get(symbIndex), adj[index].get(symbIndex));
        	}
        	trie.add(map);
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
