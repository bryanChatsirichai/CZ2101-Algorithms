package sorts;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		System.out.println("Enter array length");
		int arrLength = scan.nextInt();
		int arr1[] = new int[arrLength];
		int arr2[] = new int[arrLength];
		int arr3[] = new int[arrLength];
		int arr4[] = new int[arrLength];
		for(int i = 0;i<arrLength;i++) {
			int num = rand.nextInt(20);
			arr1[i] = num;
			arr2[i] = num;
			arr3[i] = num;
			arr4[i] = num;
		}
		displayArr(arr1);
		long start = System.nanoTime();
		insertionSort(arr1, arrLength);
		long end = System.nanoTime();
		System.out.println("Insertion Sort: " + (end - start));
		displayArr(arr1);
	
		System.out.println();
	
		displayArr(arr2);
		start = System.nanoTime();
		mergeSort(arr2, arrLength);
		end = System.nanoTime();
		System.out.println("Merge Sort: " + (end - start));
		displayArr(arr2);
		
		System.out.println();
		
		displayArr(arr3);
		start = System.nanoTime();
		mergeSort2(arr3, 0, arrLength - 1);
		end = System.nanoTime();
		System.out.println("Merge Sort2: " + (end - start));
		displayArr(arr3);
		
		System.out.println();
		
		displayArr(arr4);
		start = System.nanoTime();
		quickSort(arr4, 0, arrLength - 1);
		end = System.nanoTime();
		System.out.println("QuickSort: " + (end - start));
		displayArr(arr4);
	}
	
	
	public static void insertionSort(int arr[],int arrLength) {
		if(arrLength < 2) {
			return;
		}
		for(int i = 1;i<arrLength;i++) {
			int h = i;
			while(h > 0 &&arr[h] < arr[h-1]) {

				int temp = arr[h];
				arr[h] = arr[h-1];
				arr[h-1] = temp;
				h--;
				
			}
		}
	}	
	public static void mergeSort(int arr[],int arrLength) {
		int n = arrLength;
		if(n < 2) {
			return;
		}
		else {
			int mid = arrLength / 2;
			int leftArr[] = new int[mid];
			int rightArr[] = new int[arrLength - mid];
			for(int i = 0;i<mid;i++) {
				leftArr[i] = arr[i];
			}
			for(int i = mid;i<arrLength;i++) {
				rightArr[i - mid] = arr[i];
			}
			mergeSort(leftArr, mid);
			mergeSort(rightArr, arrLength - mid);
			merge(arr, leftArr, rightArr, mid, arrLength - mid);
		}
	}
	private static void merge(int arr[],int leftArr[],int rightArr[],int leftLength,int rightLength) {
		int l = leftLength;
		int i = 0; //index left array
		int r = rightLength;
		int j = 0;//index right array
		//index parent array
		int k = 0;
		while(i < l && j < r) {
			if(leftArr[i] < rightArr[j]) {
				arr[k] = leftArr[i];
				i++;
			}
			else if(rightArr[j] < leftArr[i]) {
				arr[k] = rightArr[j];
				j++;
			}
			else {
				//equal take from leftArr
				arr[k] = leftArr[i];
				i++;
			}
			k++;
		}
		
		//throw the remainder 1 array will finish first or both end same time
		while(i < l) {
			arr[k] = leftArr[i];
			k++;
			i++;
		}
		while(j < r) {
			arr[k] = rightArr[j];
			k++;
			j++;
		}
		return;
	}
	public static void mergeSort2(int arr[],int l,int r) {
		//passing indexes
		if(l < r) {
			
			//find mid point
			int m = (l + r)/2;
			//sort left and right half
			mergeSort2(arr, l, m);
			mergeSort2(arr, m + 1, r);
			//merge the result
			merge2(arr, l, m, r);
		}
	}
	private static void merge2(int arr[],int l,int m,int r) {		
		//get the sizes of the sub arrays
		int n1 = m - l + 1;
		int n2 = r - m;
		
		int leftArr[] = new int[n1];
		int rightArr[] = new int[n2];
		
		//copy data to temp sub arrays
		for(int i = 0;i<n1;i++) {
			leftArr[i] = arr[l+i];
		}
		for(int j = 0;j<n2;j++) {
			rightArr[j] = arr[m + 1 + j];
		}
		
		int i = 0; //track left array
		int j = 0; //track right array
		int k = l; //track parent array
		
		while(i < n1 && j < n2) {
			if(leftArr[i] <= rightArr[j]) {
				arr[k] = leftArr[i];
				i++;
				k++;
				
			}
			else {
				arr[k] = rightArr[j];
				j++;
				k++;
			}
		}
		//throw the rest of not finish array to parent array
		while(i < n1) {
			arr[k] = leftArr[i];
			i++;
			k++;
		}
		while(j < n2) {
			arr[k] = rightArr[j];
			j++;
			k++;
		}
	return;
	}
	
	
	public static void quickSort(int arr[],int l,int r) {
		if(l > r) {
			return;
		}
		int pivot_pos = partition(arr, l, r);
		quickSort(arr, l, pivot_pos - 1);
		quickSort(arr, pivot_pos + 1, r);
	}
	private static int partition(int arr[],int start,int end) {
		int mid = (start + end) /2;
		//select middle element to be pivot
		int pivotValue = arr[mid];
		//swap pivot place in front
	
		int temp = arr[start];
		arr[start] = pivotValue;
		arr[mid] = temp;
		
		int pivotIndex = start; //if all values bigger than pivot pivot index will be here
		for(int i = start + 1;i <= end;i++) {
			
			if(arr[i] < pivotValue) {
				pivotIndex++;
				temp = arr[i];
				arr[i] = arr[pivotIndex];
				arr[pivotIndex] = temp;
				
			}
		}
		//swap so left of pivot value smaller and right of pivot value is larger
		arr[start] = arr[pivotIndex];
		arr[pivotIndex] = pivotValue;
		return pivotIndex;
	}
	public static void displayArr(int arr[]) {
		for(int num:arr) {
			System.out.print(num + " ");
		}System.out.println();
	}
}
