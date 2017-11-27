package test;

import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
	}
}

public class TrieMatching implements Runnable {
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
	 
	 
	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
		// write your code here
		List<Map<Character, Integer>> trie = buildTrie((String[])patterns.toArray());
		 for(int index = 0; index < text.length(); index++){
			 Character currentChar = text.charAt(index);
			 boolean foundPattern = false;
			 int initialIndex = 0;
			 while(true){
				 if(trie.get(initialIndex).size() == 0){
					 
				 }
				 Map<Character, Integer> map = trie.get(initialIndex);
				 if(map.containsKey(currentChar)){
					 initialIndex = map.get(currentChar);
				 }else{
					 if(map.size() == 0){
						 foundPattern = true;
					 }
					 break;
				 }
			 }
			 if(foundPattern){
				 result.add(index);
			 }
		 }
		return result;
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatching ()).start ();
	}
}
