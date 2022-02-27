package fibo;

import java.util.Arrays;

public class Fibonacci {
	
	
	public int fib(int n) {
		//normal divide and conquer approach
		if(n <= 1) {
			return n;
		}
		else {
			return fib(n - 1) + fib(n - 2);
		}
	}
	public int fibTopDown(int n) {
		//keep track
		int arr[] = new int[n + 1];
		//initialize with -1
		Arrays.fill(arr, -1);
		return fib2(n,arr);
		
	}
	private int fib2(int n,int arr[]) {
		int f1,f2;
		if(n == 0 || n == 1) {
			storeArr(arr, n, n);
			return n;
		}
		else {
			
			if(!isMember(arr, n-1)) {
				//haven visited yet
				//recursive call to sub problem
				f1 = fib2(n-1,arr);
			}
			else {
				//retrieve
				//solved before the sub problem retrieve solution from array
				f1 = retrieve(arr, n-1);
			}
			
			if(!isMember(arr, n-2)) {
				f2 = fib2(n-2,arr);
			}
			else {
				f2 = retrieve(arr, n-2);
			}
		}
		int result = f1 + f2;
		//before returning solution,store in array,memorization
		storeArr(arr, n, result);
		return result;
	}
	private void storeArr(int arr[],int index,int n) {
		arr[index] = n;
	}
	private boolean isMember(int arr[],int index) {
		return arr[index] != -1;
	}
	private int retrieve(int arr[],int index) {
		return arr[index];
	}
	
	public int fibBotUp(int n) {
		int arr[] = new int[n + 1];
		if(n == 0) {
			arr[0] = 0;
		}
		else  {
			arr[0] = 0;
			arr[1] = 1;
		}
		for(int i = 2;i<=n;i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr[n];
	}
}
