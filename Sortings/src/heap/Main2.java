package heap;

import java.util.Random;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		System.out.println("Enter array length");
		int arrLength = scan.nextInt();
		int arr1[] = new int[arrLength];
		for(int i = 0;i<arrLength;i++) {
			int num = rand.nextInt(20);
			arr1[i] = num;

		}
		displayArr(arr1);
		System.out.println("heapifying");
		buildMaxHeap(arr1, arrLength);
		displayArr(arr1);
		//System.out.println("get Max: " + extractMax(arr1, arrLength));
		System.out.println("heapsort");
		heapSort(arr1, arrLength);
		displayArr(arr1);
	
	}
	
	public static void heapSort(int arr[],int hSize) {
		for(int i = hSize - 1;i>=0;i--) {
			int maxNum = arr[0]; //root is max
			//swap last leaf and root
			arr[0] = arr[i];
			arr[i] = maxNum;
			heapify(arr, 0, i);
		}
	}
	
	public static void buildMaxHeap(int arr[],int hSize) {
		for(int i = (hSize/2)-1;i>=0;i--) {
			heapify(arr, i, hSize);
		}
	}
	
	
	// To HEAPIFY a subtree rooted with node i which is
    // an index in arrAY[]. n is size of heap
	public static void heapify(int arr[],int i,int hSize) {
		int largest = i;
		int l = (2*i) + 1;
		int r = (2*i) + 2;
		
        // If left child is larger than root
		if(l < hSize && arr[i] < arr[l]) {
			largest = l;
		}
		else {
			largest = i;
		}
        // If right child is larger than largest so far
		if(r < hSize && arr[largest] < arr[r]) {
			largest = r;
		}
		
        // If largest is not root
		if(largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
           // Recursively HEAPIFY the affected sub-tree
			heapify(arr, largest, hSize);
		}
	}
	
	public static int extractMax(int arr[],int hSize) {
		int maxNum = arr[0];
		arr[0] = arr[hSize - 1];
		hSize--;
		heapify(arr, 0, hSize);
		return maxNum;
	}
	
	
	public static void displayArr(int arr[]) {
		for(int num:arr) {
			System.out.print(num + " ");
		}System.out.println();
	}
}
