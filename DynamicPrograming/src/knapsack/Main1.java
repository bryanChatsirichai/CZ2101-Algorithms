package knapsack;
import java.util.*;
public class Main1 {
//lecture and tutorial example 0/1 knapsack
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create 2 object array store weight and cost
		int p[] = new int[] {10,40,30,50};
		int s[] = new int[] {5,4,6,3};
		long start,end;
		int result;
		int n = p.length;
		start = System.nanoTime();
		result = knapSack(10, p, s, n);
		end = System.nanoTime();
		System.out.println("Bottom up knapSack time taken: " + (end - start));
		System.out.println("result: " + result);
	}
	public static int knapSack(int WEIGHT,int p[],int s[],int n) {
		int w = WEIGHT;
		int m[][] = new int [w+1][n+1];
		
		for(int i = 0;i <= w;i++) {
			//System.out.println("i = " + i);
			for(int j = 0;j <= n;j++) {
				//System.out.println("j = " + j);
				if(i == 0 || j == 0) {
					//no item or zero Weight available
					m[i][j] = 0; 
				}
				else {
					//assume previous sub problem 
					//same weight remaining but lesser 1 object
					
					/*
					p[j] and s[j] use -1
					p[j - 1] and s[j - 1] due to 0 base index in the array 
					*/
					m[i][j] = m[i][j - 1];
					if(s[j - 1] <= i) {
					//at current weight available level 
					//check enough space store this object
					if(m[i][j] < p[j - 1] + m[i - s[j - 1]][j-1]) {
						m[i][j] = p[j - 1] + m[ i - s[j - 1] ][j-1];
					}
				}
			}
		}	
	}
	
	printMatrix(m, w, n);
	System.out.println();
	getSubsetChosen(m, WEIGHT, p, s, n);
	return m[w][n];
	}
	public static void printMatrix(int m[][],int WEIGHT,int n) {
		for(int i = 0;i <= WEIGHT;i++) {
			//System.out.println("i = " + i);
			for(int j = 0;j <= n;j++) {
				//System.out.println("j = " + j);
				System.out.print(m[i][j] + " ");
			}System.out.println();
		}
	}
	
	public static void getSubsetChosen(int m[][],int WEIGHT,int p[],int s[],int n) {
		
		int i = WEIGHT;
		int j = n;
		ArrayList<Integer> arr = new ArrayList<Integer>(); //keep track which object store 
		//back track to see which object included into the knapSack
		//start from the final result
		while(i != 0 && j != 0) {
			int curCost = m[i][j];
			System.out.println("m["+i+"]["+j+"]");
			//check if have included the object or not
			
			//if have not chosen the cur object
			if(m[i][j - 1] == curCost) {
				j = j - 1;
			}
			//cur object was chosen
			else {
				//recall zero base index for s[] so need -1
				//back track 'remove' cur object as if have not insert to bag
				i = i - s[j - 1];
				arr.add(0, j);//j position is the  object j but when access need -1 due to zero index
				j = j - 1;
				
			}
			
		}
		
		System.out.println();
		System.out.println("Inserted objects are:");
		System.out.println(arr);
		System.out.println();
	}
}
