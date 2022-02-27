package lcs;
import java.util.*;
public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		LowestCommonSubsequence lcs = new LowestCommonSubsequence();
		String s1 = "CGGTAT";
		String s2 = "AGTTGC";
		int i = s1.length();
		int j = s2.length();
		long start = System.nanoTime();
		int result = lcs.recursive(s1, s2, i - 1, j - 1);
		long end = System.nanoTime();
		System.out.println("LCS recursive approach: " + result);
		System.out.println("time taken: " + (end - start));
		
		System.out.println();
		
		start = System.nanoTime();
		result = lcs.topDown(s1, s2, i , j );
		end = System.nanoTime();
		System.out.println("LCS Top/Down approach: " + result);
		System.out.println("time taken: " + (end - start));
	
		System.out.println();
		
		start = System.nanoTime();
		result = lcs.bottomUp(s1, s2, i, j);
		end = System.nanoTime();
		System.out.println("LCS Bottom/Up approach: " + result);
		System.out.println("time taken: " + (end - start));
	}

}
