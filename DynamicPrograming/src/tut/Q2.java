package tut;
import java.util.*;
public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the n value");
		int n = scan.nextInt();
		long start = System.nanoTime();
		int result = hRecursive(n);
		long end = System.nanoTime();
		System.out.println("Recursive way: " + (end - start));
		System.out.println("value : " + result);
		//System.out.println("value2 : " + hRecursive2(n));

		System.out.println();
		
		start = System.nanoTime();
		result = hn_Dp(n);
		end = System.nanoTime();
		System.out.println("Tutorial bottomUp way: " + (end - start));
		System.out.println("value : " + result);
		
		System.out.println();
		start = System.nanoTime();
		result = H(n);
		end = System.nanoTime();
		System.out.println("My bottomUp way: " + (end - start));
		System.out.println("value : " + result);
	}
	
	
	//Recursive approach
	public static int hRecursive(int n) {
		if(n == 0) {
			return 1;
		}
		else {
			int result = 0;
			int j;
			if(n % 2 == 1) {
				//n is odd
				j = n - 1;
			}
			else {
				//n is even
				j = n - 2;
			}
			/*
			for(int k = 0;k<= j;k+=2) {
				result = result +  hRecursive(k);
			}
			*/
			
			for(int k = j;k >= 0;k -=2) {
				result = result + hRecursive(k);
			}
			
			return result;
		}
		
	}
	

	//Tutorial version
	public static int hn_Dp(int n) {
		//make use of an array for tubulation
		int arr[] = new int[n + 1];
		arr[0] = 1;
		int j;
		for(int i = 1;i <= n;i++) {
			//arr[i] = 0;
			int result = 0;
			if(i % 2 == 1) {
				j = i - 1;
			}
			else {
				j = i - 2;
			}
			while(j >= 0) {
				//arr[i] = arr[i] + arr[j];
				result = result + arr[j];
				j =  j - 2;
			}
			arr[i] = result;
		}
		return arr[n];
	}
	
	
	//My version of bottom up
	public static int H(int n) {
		//O(n^2)
		int arr[] = new int[n+1];
		//base case
		arr[0] = 1;
		
		for(int i = 1;i<=n;i++) {
			int result = 0;
			int j;
			if(i % 2 == 0) {
				//even
				j = i - 2;
			}
			else {
				//odd
				j = i - 1;
			}
			while(j >= 0) {
				result = result + arr[j];
				j = j - 2;
			}
			arr[i] = result;
		}
		
		
		return arr[n];
	}
}
