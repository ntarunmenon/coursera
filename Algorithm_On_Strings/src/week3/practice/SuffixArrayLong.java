package week3.practice;

import java.util.*;
import java.io.*;
import java.util.zip.CheckedInputStream;

public class SuffixArrayLong {
    
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

    public class Suffix implements Comparable {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            Suffix other = (Suffix) o;
            return suffix.compareTo(other.suffix);
        }
    }

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
		int[] order = countSort(text);
		int[] charclass = computeCharClasses(text, order);
		int lengthOfCyclicShift = 1;
		while (lengthOfCyclicShift <text.length()){
			order = sortDoubled(text, lengthOfCyclicShift, order, charclass);
			charclass = updateClasses(order, charclass, lengthOfCyclicShift);
			lengthOfCyclicShift = 2*lengthOfCyclicShift;
		}
		return order;
	}

    public int[] countSort(String text){
		int[] counter = new int[5];
		int[] order = new int[text.length()];
		
		char[] chars = text.toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
			counter[letterToIndex(chars[i])]++;
		}
		for (int index = 1; index < counter.length; index++) {
			counter[index] = counter[index] + counter[index -1];
		}
		for(int index = chars.length -1; index > 0; index--){
			char c = chars[index];
			counter[letterToIndex(c)]--;
			order[counter[letterToIndex(c)]] = index;
		}
		return order;
	}
	
	public int[] computeCharClasses(String text,int[] order){
		int[] charClass = new int[text.length()];
		charClass[order[0]] = 0;
		
		char[] chars = text.toCharArray();
		
		for (int i = 1; i < chars.length; i++) {
			if(chars[order[i]] != chars[order[i -1]]){
				charClass[order[i]] =  charClass[order[i - 1]] + 1;
			}else{
				charClass[order[i]] =  charClass[order[i - 1]];
			}
		}
		return charClass;
	}
	
	public int[] sortDoubled(String text,int lengthOfCyclicShift,int[] order, int[] charclass){
		int[] count = new int[charclass.length];
		int[] newOrder = new int[text.length()];
		int lengthOfString = newOrder.length;
		
		for (int i = 0; i < lengthOfString; i++) {
			count[charclass[i]]++;
		}
		
		for (int j = 1; j < lengthOfString; j++) {
			count[j] = count[j] + count[j-1];
		}
		for (int i = lengthOfString - 1; i >= 0; i--) {
			int start = (order[i] - lengthOfCyclicShift + lengthOfString) % lengthOfString;  
			int cl = charclass[start];
			count[cl] = count[cl] - 1;
			newOrder[count[cl]] = start;
		}
		return newOrder;
	}
	
	public int[] updateClasses(int[] newOrder, int[] charClass, int lengthOfCyclicShift){
		int lengthOfText = newOrder.length;
		int[] newClass = new int[lengthOfText];
		
		newClass[newOrder[0]] = 0;
		for (int i = 1; i < newClass.length; i++) {
			int cur = newOrder[i];
			int prev = newOrder[i - 1];
			int mid = cur + lengthOfCyclicShift;
			int midPrev = (prev + lengthOfCyclicShift)%lengthOfText;
			
			if(charClass[cur] != charClass[prev] || charClass[mid] != charClass[midPrev]){
				newClass[cur] = newClass[prev] + 1;
			}else{
				newClass[cur] = newClass[prev];
			}
		}
		return newClass;
	}
	
	
    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
