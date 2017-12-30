package week2.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {
	 int letterToIndex (char letter)
	{
		switch (letter)
		{
			case '$': return 0;
			case 'A': return 1;
			case 'C': return 2;
			case 'G': return 3;
			case 'T': return 4;
			default: assert (false); return -1;
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

    String inverseBWT(String text) {
		int[] noOfSmallerChars = new int[5];
		int[] noOfOccurences = new int[5];
		
		long startTime = System.nanoTime();
		int[] indexOfRepetition = new int[text.length()];
		int[] leftToFirst = new int[text.length()];
		noOfOccurences(text,indexOfRepetition,noOfOccurences);
		noOfSmallerChars(noOfSmallerChars, noOfOccurences);
		
		for (int i = 0; i < text.length(); i++) {
		    char charAt = text.charAt(i);
		    leftToFirst[i] = indexOfRepetition[i] + noOfSmallerChars[letterToIndex(charAt)];
		}
		
		StringBuffer buffer = new StringBuffer();
		int r = 1;
		char[] bwt = text.toCharArray();
		char c = bwt[r-1];
		
		while (c!= '$'){
			buffer.append(c);
			r = leftToFirst[r - 1];
			c = bwt[r - 1];
		}
		buffer.reverse().append("$");
		return buffer.toString();
	}
    
    private  void noOfSmallerChars(int[] noOfSmallerChars, int[] noOfOccurences) {
		noOfSmallerChars[letterToIndex('$')]=0;
		noOfSmallerChars[letterToIndex('A')]=1;
		noOfSmallerChars[letterToIndex('C')]=noOfOccurences[letterToIndex('$')] + noOfOccurences[letterToIndex('A')];
		noOfSmallerChars[letterToIndex('G')]=	noOfSmallerChars[letterToIndex('C')] + noOfOccurences[letterToIndex('C')];
		noOfSmallerChars[letterToIndex('T')]=	noOfSmallerChars[letterToIndex('G')] + noOfOccurences[letterToIndex('G')];
	}

	private  int[] noOfOccurences(String text,int[] indexOfRepetition,int[] noOfOccurences) {
		for (int i = 0; i < text.length(); i++) {
		    char charAt = text.charAt(i);
		    noOfOccurences[letterToIndex(charAt)]++;
		    indexOfRepetition[i] = noOfOccurences[letterToIndex(charAt)];
		}
		return noOfOccurences;
	}
	
	
    public  int noOfOccurenceCharacterinString(String line,char symbol){
		int count = 0;
		for (int i = 0; i < line.length(); i++) {
		    if (line.charAt(i) == symbol) {
		        count++;
		    }
		}
		return count;
	}
	
	public  int ordinalIndexOf(String str, char symbol, int n) {
	    int pos = str.indexOf(symbol+"");
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(symbol, pos + 1);
	    return pos;
	}

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
