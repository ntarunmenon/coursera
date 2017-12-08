package test;

import java.util.Arrays;

public class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];
	public boolean patternEnd;

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
		patternEnd = false;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(next) + "  " + patternEnd;
	}

	public boolean isLeaf() {
		 for(int b : next) if(b !=NA ) return false;
		    return true;
	}
}