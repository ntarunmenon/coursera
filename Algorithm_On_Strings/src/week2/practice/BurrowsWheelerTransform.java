package week2.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
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

    String BWT(String text) {
		List<String> result = new ArrayList<>();
		for(int index= text.length() - 1 ; index >= 0; index--){
			char lastChar = text.charAt(text.length() - 1);
			text = lastChar + text.substring(0, text.length() - 1);
			result.add(text);
		}
		Collections.sort(result.subList(1, result.size()));
		String bwt = "";
		for(String currentText:result){
			bwt = bwt + currentText.charAt(currentText.length() - 1);
		}
		return bwt;
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
