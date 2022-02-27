package knapsack;

import java.util.ArrayList;

public class lab3part2 {
	//knapsack that allow unlimited supplies of each object
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create 2 object array store weight and cost
		int s1[] = new int[] {5,6,8}; //weight
		int p1[] = new int[] {7,6,9}; //profit
		long start,end;
		int result;
		int n = p1.length;
		start = System.nanoTime();
		int capacity = 14;
		result = knapSackDuplicateAllow(capacity, p1, s1, n);
		end = System.nanoTime();
		System.out.println("Bottom up knapSack time taken: " + (end - start));
		System.out.println("result: " + result);
	}
	public static int knapSackDuplicateAllow(int WEIGHT,int p[],int s[],int n) {
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
					//m[i][j] = m[i][j - 1];//set profit assuming object not chosen
					if(s[j - 1] <= i) {
					//i is current capacity level
					//at current weight available level 
					//check enough space store this object
					//JTH object remain in position as could be taken again
					if(m[i][j - 1] < p[j - 1] + m[i - s[j - 1] ][j]) {
						m[i][j] = p[j - 1] + m[ i - s[j - 1] ][j];
					}
					else {
						//JTh object not chosen as sub problem with current capacity and without JTh gets better result
						m[i][j] = m[i][j - 1];
					}
				}
				else {	
						//JTh object not chosen as exceed bag capacity
						//left of current entry is best profit when JTh object not chosen
						m[i][j] = m[i][j - 1];
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
		System.out.println("Bottom Up table");

		for(int i = 0;i <= WEIGHT;i++) {
			//System.out.println("i = " + i);
			for(int j = 0;j <= n;j++) {
				//System.out.println("j = " + j);
				System.out.print(m[i][j] + " ");
			}System.out.println();
		}
	}
	
	public static void getSubsetChosen(int m[][],int WEIGHT,int p[],int s[],int n) {
		System.out.println("Trace sequence for possible solution of which object(s) added");
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
			//if cost of not choosing cur object and cost at cur object same implies cur object not chosen
			if(m[i][j - 1] == curCost) {
				//reduce go to next object to consider as cur object redundant
				//System.out.println("not added");
				j = j - 1;
			}
			//cur object was chosen
			else {
				//recall zero base index for s[] so need -1
				
				i = i - s[j - 1];
				System.out.println("added object " + j);
				arr.add(j);//j position is the  object j but when access need -1 due to zero index
				//back track but cur object could been chosen more than once so test JTh object again
				// DO NOT j = j - 1;
				
			}

			
		}
		
		System.out.println();
		System.out.println("Inserted objects are:");
		System.out.println(arr);
		System.out.println();
	}
}
