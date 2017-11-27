package org.patternmatch;

public class PatternMatch {

	public int patternmatch_BruteForce(String text,String pattern){
		int counter = 0;
		for(int textStartIndex = 0; textStartIndex < text.length(); textStartIndex++ ){
			int textCurrentIndex = textStartIndex;
			int patternStartIndex = 0;
			boolean completeMatch = false;
			while(patternStartIndex < pattern.length() && text.charAt(textCurrentIndex) == pattern.charAt(patternStartIndex)
					&& !completeMatch){
				textCurrentIndex++;
				patternStartIndex++;
				if(patternStartIndex == pattern.length()){
					completeMatch = true;
				}
			}
			if(completeMatch){
				counter++;
			}
		}
		
		return counter;
	}
	
	public static void main(String[] args) {
		System.out.println(new PatternMatch().patternmatch_BruteForce("panamabananas", "an"));
	}
}
