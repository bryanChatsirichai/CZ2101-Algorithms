package knapsack;
import java.util.*;


//value with a knapsack of weight W and
//multiple instances allowed.
public class UboundedKnapsack
{
  
 private static int max(int i, int j)
 {
         if(i > j) {
        	 return i;
         }
         else {
        	 return j;
         }
 }
  


 public static void main(String[] args)
 {
     int W = 14; //Bag Capacity  
     int wt[] = {4,6,8};
     int val[] = {7,6,9};
   
     int n = val.length;
     System.out.println(unboundedKnapsack(W, n, val, wt));
 } 
 
 
 // Returns the maximum value with knapsack
 // of W capacity
 private static int unboundedKnapsack(int W, int n,
                             int[] val, int[] wt)
 {	
	 //track which object added and how many times 
     ArrayList<Integer> arr = new ArrayList<>();
	 
     // dp[i] is going to store maximum value
     // with knapsack capacity i.
     int dp[] = new int[W + 1];
      
     // Fill dp[] using above recursive formula
     for(int i = 0; i <= W; i++){
    	 //bottom up start from 0 capacity till Intended capacity
         for(int j = 0; j < n; j++){
        	 //check all the object which most suitable
             if(wt[j] <= i){
            	 //cur object does not exceed cur capacity
                 dp[i] = max(dp[i], dp[i - wt[j]] +
                             val[j]);
             }

         }
     }
     
     //try get what object added
    int i = W;
     while(i > 0) {
    	 //System.out.println(i);
        if(dp[i - 1] == dp[i]) {
        	i--;
        	
        }
        else {
        	for(int j = 0; j < n; j++){
       	 //check all the object which most suitable
            if(dp[i] - dp[i-1] == val[j]){
           	 //cur object does not exceed cur capacity
                arr.add(j);
                i = i - wt[j];
                break;
            }
            //System.out.println(j);
        }
        }

     }
     
     
     displayArr(dp);
     System.out.println("Objects accroding to object array index added are");
     System.out.println(arr);
     return dp[W];
 }
 
 
 
 private static void displayArr(int dp[]) {
	 System.out.print("[ ");
	 for(int i=0;i<dp.length;i++) {
		 System.out.print(dp[i] + " ");
	 }System.out.print("]");
	 System.out.println();
 }
}