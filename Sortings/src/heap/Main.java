package heap;
import java.util.*;
public class Main {

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
		System.out.println();
		
		System.out.println("MaxHeap");
		MaxHeap maxheap = new MaxHeap(arr1);
		System.out.println(maxheap);
		System.out.println(maxheap.pollMax());
		maxheap.add(13);
		System.out.println(maxheap);
		System.out.println("Extract max");
		System.out.println(maxheap.removeMax());
		System.out.println("remove");
		System.out.println(maxheap.remove(13));
		System.out.println("MaxSort");
		int arrAscending[] = maxheap.maxSort();
		System.out.println(maxheap);
		for(int num:arrAscending) {
			System.out.print(num + " ");
		}System.out.println();
		
		System.out.println();
		
		System.out.println("MinHeap");
		MinHeap minheap = new MinHeap(arr1);
		System.out.println(minheap);
		System.out.println(minheap.pollMin());
		minheap.add(5);
		System.out.println(minheap);
		System.out.println("Extract min");
		System.out.println(minheap.removeMin());
		System.out.println("remove");
		System.out.println(minheap.remove(5));
		System.out.println("MinSort");
		int arrDesecending[] = minheap.minSort();
		System.out.println(minheap);
		for(int num:arrDesecending) {
			System.out.print(num + " ");
		}System.out.println();
		
	}
	public static void displayArr(int arr[]) {
		for(int num:arr) {
			System.out.print(num + " ");
		}System.out.println();
	}
}
