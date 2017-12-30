package test;

import java.io.*;
import java.util.*;




public class TrieMatchingExtended implements Runnable {
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
	
	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
		// write your code here
		List<Node> trie = buildTrie(patterns.toArray(new String[0]));
		 for(int index = 0; index < text.length(); index++){
			 boolean foundPattern = false;
			 int trieIndex = 0;
			 int textIndex = index;
			 while(true){
				 Character currentChar = text.charAt(textIndex); // Get the current character under consideration.
				 Node node = trie.get(trieIndex); // get the current trie index.
				 int nextTrieIndex = node.next[letterToIndex(currentChar)]; //Compare the current character and text
				 if( nextTrieIndex != -1){// Pattern is Matching. 
					 trieIndex = nextTrieIndex;
					 //Check if trie is leaf.
					if(trie.get(trieIndex).patternEnd && !trie.get(trieIndex).isLeaf() && !result.contains(textIndex)){
						result.add(textIndex);
					}
					 if(trie.get(trieIndex).isLeaf()){
						 foundPattern = true;
						 break;
					 }else{
						 if(textIndex == text.length()-1){
								 foundPattern = false;
							 break;
						 }else{
							 textIndex++;
						 }
					 }
					
				 }else{
					 foundPattern = false; // break and start with the next character
					 break;
				 }
			 }
			 
			 if(foundPattern && !result.contains(index)){
				 result.add(index);
			 }
		 }
		return result;
	}

	private boolean isTrieALeaf(int trieIndex) {
		// TODO Auto-generated method stub
		return false;
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
		new Thread (new TrieMatchingExtended ()).start ();
	}
}
