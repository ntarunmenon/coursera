package week3.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KnuthMorrisPratt {
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

    // Find all the occurrences of the pattern in the text and return
    // a list of all positions in the text (starting from 0) where
    // the pattern starts in the text.
    public List<Integer> findPattern(String pattern, String text) {
        ArrayList<Integer> result = new ArrayList<Integer>();

		String combined = pattern + '$' + text;
		int[] prefixFunction = computPrefixFunction(combined);
		int patternLength = pattern.length();
		for(int index = patternLength+1;index < combined.length();index++){
			if(prefixFunction[index] == patternLength){
				result.add(index - 2*patternLength);
			}
		}
		Arrays.toString(result.toArray());
        return result;
    }

    private  int[] computPrefixFunction(String text) {
		int length = text.length();
		int border = 0;
		int[] prefixFunction = new int[length];
		
		for(int index = 1; index < length;index++){
			while(border > 0 && text.charAt(index) != text.charAt(border)){
				border = prefixFunction[border -1];
			}
			if(text.charAt(index) == text.charAt(border)){
				border = border + 1;
			}else{
				border = 0;
			}
			prefixFunction[index] = border;
		}
		return prefixFunction;
	}
    static public void main(String[] args) throws IOException {
        new KnuthMorrisPratt().run();
    }

    public void print(List<Integer> x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String pattern = scanner.next();
        String text = scanner.next();
        List<Integer> positions = findPattern(pattern, text);
        print(positions);
    }
}
