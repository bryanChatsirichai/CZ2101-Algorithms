package knapsack;
import java.util.*;


//value with a knapsack of weight W and
//multiple instances allowed.
public class UboundedKnapsack
{
  

  


 public static void main(String[] args)
 {
     int C = 14; //Bag Capacity  
     int wi[] = {5,6,8};
     int pi[] = {7,6,9};
   
     int n = pi.length;
     System.out.println("Max profit: " + unboundedKnapsack(C, n, pi, wi));
 } 
 
 
 // Returns the maximum value with knapsack
 // of W capacity
 private static int unboundedKnapsack(int C, int n,
                             int[] pi, int[] wi)
 {	
	 //track which object added and how many times 
     //ArrayList<Integer> arr = new ArrayList<>();
	 
     // dp[i] is going to store maximum value
     // with knapsack capacity i.
     int dp[] = new int[C + 1];
      
     // Fill dp[] using above recursive formula
     for(int i = 0; i <= C; i++){
    	 //bottom up start from 0 capacity till Intended capacity
         for(int j = 0; j < n; j++){
        	 //check all the object which most suitable
             if(wi[j] <= i){
            	 //cur object does not exceed cur capacity
                 dp[i] = max(dp[i], dp[i - wi[j]] +
                             pi[j]);
             }

         }
     }

     
     System.out.println("Bottom up dp table");
     displayArr(dp);
     //System.out.println("Objects accroding to object array index added are");
     //System.out.println(arr);
     return dp[C];
 }
 
 
 private static int max(int i, int j)
{
    if(i > j) {
   	 return i;
    }
    else {
   	 return j;
    }
}
 
 private static void displayArr(int dp[]) {
	 System.out.print("[ ");
	 for(int i=0;i<dp.length;i++) {
		 System.out.print(dp[i] + " ");
	 }System.out.print("]");
	 System.out.println();
 }
}