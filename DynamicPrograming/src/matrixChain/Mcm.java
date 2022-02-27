package matrixChain;

import java.util.ArrayList;
import java.util.Stack;

public class Mcm {
	ArrayList<String> s =  new ArrayList<String>();
	
	public  int bottomUp(int n,int d[]) {
		int[][] cost = new int[n+1][n+1];
		int [][] last = new int[n+1][n+1];
		return matrixChainMultiplication(n, d, cost, last);
	}
	
	private  int matrixChainMultiplication(int n,int d[],int cost[][],int last[][]) {
		//matrix fill diagonally upwards
		for(int i = 0;i<=n-1;i++) {
			cost[i][i+1] = 0; //single matrix no cost
		}
		for(int diff = 2;diff<= n;diff++) {
			
			for(int i = 0;i<= n - diff;i++) {
				int j = i + diff;
				cost[i][j] = Integer.MAX_VALUE;
				for(int k = i + 1; k <= j - 1;k++) {
					int result = cost[i][k] + cost[k][j] + (d[i] * d[k] * d[j]);
					if(result < cost[i][j]) {
						cost[i][j] = result;
						last[i][j] = k;
					}
				}
			}
		}
		printLastTable(last, n);
		printSequence(last, 0, n);
		System.out.println(s);
		return cost[0][n];
	}
	private void printLastTable(int[][] last,int n) {
		for(int i = 0;i <= n;i++) {
			for(int j = 0;j <= n;j++) {
				System.out.print(last[i][j] + " ");
			}System.out.println();
		}
	}
	private void printSequence(int [][] last,int start,int end) {
		if(end - start <= 1) {
			String str = "A";
			str = str + end;
			//s.add("(");
			s.add(str);
			return;
		}
		int i = last[start][end];
		printSequence(last, start, i);
		printSequence(last, i, end);
		String m2 = s.remove(s.size()-1);
		String m1 = s.remove(s.size()-1);
		String str = "(" + m1 + "x" + m2 + ")";
		s.add(str);
		
		
	}
}
