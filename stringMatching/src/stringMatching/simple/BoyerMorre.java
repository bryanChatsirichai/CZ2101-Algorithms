package stringMatching.simple;
import java.util.*;
public class BoyerMorre {
	
	public static int alpha = 128; //ASCII
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "ABABABCCAC";
		String pattern = "CAC";
		char[]p = pattern.toCharArray();
		char[]t = text.toCharArray();
		int m = p.length;
		int n = t.length;
		
		int[] charJump = new int[alpha];
		computeJumps(p, m, alpha, charJump);
		int result = simpleBmscan(p, t, m, n, charJump);
		System.out.println("result  = " + result);
	}
	public static void computeJumps(char[] p,int m,int alpha,int[] charJump) {
		char ch;
		int k;
		for(ch = 0;ch<alpha;ch++) {
			//chars not in pattern will cause pattern to jump m, j = j + m
			charJump[ch] = m;
		}
		for(k = 1;k <= m;k++) {
			//zero base index
			charJump[ p[k -1] ] = (m - k) ;//how far away from last char
		}
	}
	public static int simpleBmscan(char []p,char[]t,int m,int n,int[] charJumps) {
		
		int j,k;
		j = m; //track the text
		k = m;//track the pattern
		while(j <= n) {
			if(k < 1) {
				return j + 1; //match found
		}	
			//zero base index
			if(t[j -1] == p[k -1]) {
				j--;
				k--;
			}
			else {
				//pair miss match
				j = j + Math.max(charJumps[t[j - 1]], m-k+1);
				k = m;//reset
			}
		
		
		}
		return -1;
	}
}