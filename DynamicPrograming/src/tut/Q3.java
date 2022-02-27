package tut;
import java.util.*;
public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter n");
		int n = scan.nextInt();
		System.out.println("Enter k");
		int k = scan.nextInt();
		long start = System.nanoTime();
		int result = binomialCoeffRecursive(n, k);
		long end = System.nanoTime();
		System.out.println("Time taken for recursive approach: " + (end - start));
		System.out.println("Result is: " + result);
		
		
		System.out.println();
		
		start = System.nanoTime();
		result = tutTopDown(n, k);
		end = System.nanoTime();
		System.out.println("Time taken for Tutorial TopDown recursive approach: " + (end - start));
		System.out.println("Result is: " + result);
		
		System.out.println();
		
		start = System.nanoTime();
		result = binomialTopDown(n, k);
		end = System.nanoTime();
		System.out.println("Time taken for My TopDown recursive approach: " + (end - start));
		System.out.println("Result is: " + result);
		
		
		System.out.println();
		start = System.nanoTime();
		result = tutBottomUp(n, k);
		end = System.nanoTime();
		System.out.println("Time taken for Tutorial BottomUp approach: " + (end - start));
		System.out.println("Result is: " + result);
		
		System.out.println();
		start = System.nanoTime();
		result = bottomUpDp(n, k);
		end = System.nanoTime();
		System.out.println("Time taken for My BottomUp approach: " + (end - start));
		System.out.println("Result is: " + result);

	}
	
	public static int binomialCoeffRecursive(int n,int k) {
		if(k > n) {
			//cannot choose more than what is AVA
			return 0;
		}
		else if(n == 0) {
			return 0;
		}
		else if(k == 0 || k == n ) {
			return 1;
		}
		return binomialCoeffRecursive(n - 1, k - 1) + binomialCoeffRecursive(n - 1, k);
	}
	
	
	//tutorial version top down
	public static int tutTopDown(int n,int k) {
		int dic[][] = new int [n + 1][k + 1];
		for(int i = 0;i<n + 1;i++) {
			for(int j = 0;j<k + 1;j++) {
				dic[i][j] = -1;
			}
		}
		return tutTopDownRecursion(n, k, dic);
	}
	
	public static int tutTopDownRecursion(int n,int k, int dic[][]) {
		//base cases
		int c1,c2;
		if(k == 0) {
			dic[n][0] = 1;
			return 1;
		}
		if(n == 0) {
			dic[0][k] = 0;
			return 0;
		}
		
		if(dic[n - 1][k - 1] == -1) {
			//solution not in dictionary
			c1 = tutTopDownRecursion(n - 1, k - 1, dic);
		}
		else {
			c1 = dic[n - 1][k - 1];
		}
		if(dic[n-1][k] == -1) {
			//solution not in dictionary
			c2 = tutTopDownRecursion(n - 1, k, dic);
		}
		else {
			c2 = dic[n-1][k];
		}
		dic[n][k] = c1 + c2;
		return dic[n][k];
	}
	
	
	//my version
	public static int binomialTopDown(int n,int k) {
		  // Make a temporary lookup table
		ArrayList<Integer> dict[] = new ArrayList[n + 1]; //array that index of each array is an array list
		  // Loop to create table dynamically
		for(int i = 0;i<n+1;i++) {
			dict[i] = new ArrayList<>();
			for(int j = 0;j <= k;j++) {
				dict[i].add(-1);
			}
		}
		return topDownRecursion(n, k, dict);
	}
	public static int topDownRecursion(int n,int k,ArrayList<Integer> dict[]) {
		// If value in lookup table
	    // then return
		if(dict[n].get(k) != -1) {
			return dict[n].get(k);
		}
		// base cases
		// store value in a table
		// before return
		if(k == 0) {
			dict[n].add(k, 1);
			return dict[n].get(k);
		}
		if(k == n) {
			dict[n].add(k,1);
			return dict[n].get(k);
		}
		
		int result = topDownRecursion(n-1, k-1, dict) + topDownRecursion(n - 1, k, dict);
		//save result before returning
		dict[n].add(k, result);
		return result;
	}
	
	//tutorial version bottom up
	public static int tutBottomUp(int n,int k) {
		int dic[][] = new int [n+1][k+1];
		//fill in first row
		for(int i = 1;i< k + 1;i++) {
			dic[0][i] = 0;
		}
		//fill in the first column
		for(int i = 0;i<n+1;i++) {
			dic[i][0] = 1;
		}
		//fill in the remaining entries row wise
		for(int i = 1;i<n+1;i++) {
			for(int j = 1;j<k+1;j++) {
				dic[i][j] = dic[i-1][j-1] + dic[i-1][j];
			}
		}
		for(int i = 0;i<n+1;i++) {
			for(int j = 0;j<k+1;j++) {
				System.out.print(dic[i][j] + " ");
			}System.out.println();
		}
		return dic[n][k];
	}
	
	
	//my version bottom up
	public static int bottomUpDp(int n,int k) {
		int matrix[][] = new int[k + 1][n + 1];
		for(int i = 0;i<k+1;i++) {
			for(int j = 0;j<n+1;j++) {
				
				if(i == 0) {
					matrix[i][j] = 1;
				}
				else if(i == j) {
					matrix[i][j] = 1;
				}
				else if(i > j) {
					matrix[i][j] = 0;
				}
				else {
					int result = matrix[i-1][j-1] + matrix[i][j-1];
					matrix[i][j] = result;
				}
			}
		}
		return matrix[k][n];
	}
}
