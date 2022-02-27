package stringMatching.simple;
import java.util.*;
public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "ABABABCCAC";
		String pattern = "ABABC";
		char[] t = text.toCharArray();
		char[] p = pattern.toCharArray();
		int result = simpleScan(p, t, p.length, t.length);
		int result2 = simpleScan2(p, t, p.length, t.length);

		System.out.println("sub string at index: " + result);
		System.out.println("sub string at index: " + result2);

		
	}
	public static int simpleScan(char[] p,char [] t,int m,int n) {
		int i,j,k;
		k = 0; //position within pattern
		j = 0; //position within text
		i = 0; //current guess of where P begins in T
	
		while(j < n) {
			//while haven check all text char
			//System.out.println("j " + j + " k " + k);
			System.out.println("p["+k+"] = " +  p[k] + " == " + t[j] + " = t["+j+"]");
			if(p[k] != t[j]) {
				//current pair of char no match
				//shift pattern right 1 position and restart from beginning of pattern and new starting position of text
				
				i++;
				j = i;
				
				if(j > n - m) {
					/*
					 * current position of jth till last char of text will have less characters then pattern
					 * so there fore not possible have string matching
					 */
					break;
				}
				
				k = 0;
			}
			else {
				//current pair match
				j++;
				k++;
				if(k == m) {
					//index through the whole P and found sub string in T
					//starting from index i in T
					return i;
				}
				
			}
			
		}
		//end of loop no possible pattern sub string found in Text
		return -1;
	}
	
	//remove variable i
	public static int simpleScan2(char[] p,char [] t,int m,int n) {
		int i,j,k;
		k = 0; //position within pattern
		j = 0; //position within text
		//i = 0; //current guess of where P begins in T
	
		while(j < n) {
			//while haven check all text char
			//System.out.println("j " + j + " k " + k);
			if(p[k] != t[j]) {
				//current pair of char no match
				//shift pattern right 1 position and restart from beginning of pattern and new starting position of text
				
				j = j - k;
				j = j + 1;
				
				k = 0;
			}
			else {
				//current pair match
				j++;
				k++;
				if(k == m) {
					//index through the whole P and found sub string in T
					//starting from index i in T
					return j - k;
				}
				
			}
			
		}
		//end of loop no possible pattern sub string found in Text
		return -1;
	}
}
