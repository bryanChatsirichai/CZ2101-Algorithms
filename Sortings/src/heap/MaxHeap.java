package heap;
import java.util.*;
public class MaxHeap {
	private List<Integer> heap = null;

	  // Construct and initially empty priority queue
	  public MaxHeap() {
	    this(1);
	  }

	  // Construct a priority queue with an initial capacity
	  public MaxHeap(int sz) {
	    heap = new ArrayList<>(sz);
	  }
	  
	  public MaxHeap(int[] arr) {
		  heap = new ArrayList<Integer>(arr.length);
		 //place all elements to the heap
		  for(int i = 0;i<arr.length;i++) {
			  heap.add(arr[i]);
		  }
		  
		  //HEAPIFY process O(n)
		  for(int i = (heap.size()/2) - 1;i>=0;i--) {
			  sink(i);
		  }
		  
	  }
	  // Adds an element to the priority queue, the
	  // element must not be null, O(log(n))
	  public void add(int e) {
		  heap.add(e);
		  int hSize = size();
		  int indexOfLastElement = hSize - 1;
		  swim(indexOfLastElement);
	  }
	  
	  
	  public int[] maxSort() {
		  int arr[] = new int[size()];
		  for(int i = heap.size() -1 ;i>=0;i--) {
			  int maxNum = heap.get(0);
			  swap(i, 0);
			  heap.remove(i);
			  arr[i] = maxNum;
			  sink(0);
		  }

		  return arr;
	  }
	  
	  // Perform bottom up node swim, O(log(n))
	  private void swim(int i) {
		  int parent = (i-1) / 2;
		  while(i > 0 && heap.get(i) > heap.get(parent)) {
			  swap(i, parent);
			  i = parent;
			  
		      // Grab the index of the next parent node WRT to o
		      parent = (i - 1) / 2;
		  }
	  }
	  
	  
	  // Top down node sink, O(log(n))
	  public void sink(int i) {
		  int heapSize = heap.size();
		  int left = 2*i + 1;
		  int right = 2*i + 2;
		  int largest;
		  if(left < heapSize && heap.get(i) < heap.get(left)) {
			  largest = left;
		  }
		  else {
			  largest = i;
		  }
		  if(right < heapSize && heap.get(largest) < heap.get(right)) {
			  largest = right;
		  }
		  if(i != largest) {
			  swap(i, largest);
			  sink(largest);
		  }
	  }
	  
	  public int removeMax() {
		  int maxNum = heap.get(0);
		  //swap root and last leaf
		  int lastIndex = size() - 1;
		  swap(0, lastIndex);
		  heap.remove(lastIndex);
		  sink(0);
		  return maxNum;
	  }
	  
	  public boolean remove(int num) {
		// Linear removal via search, O(n)
		    for (int i = 0; i < size(); i++) {
		      if (num == heap.get(i)) {
		        removeAt(i);
		        return true;
		      }
		    }
		    return false;
	  }
	  
	  
	  private void removeAt(int i) {
		  if (isEmpty()) {
			  return;
		  }
		  int indexOfLastElement = size()-1;
		  int removeData = heap.get(i);
		  swap(i, indexOfLastElement);
		  
		    // Obliterate the value
		  heap.remove(indexOfLastElement);
		  
		    // Check if the last element was removed
		  if(i == indexOfLastElement) {
			  return;//no need swim or sink
		  }
		  
		  //make sure heap property maintain
		  sink(i);
		  swim(i);
		  return;
	  }
	  
	  public int pollMax() {
		  return heap.get(0);
	  }
	  
	  // Returns true/false depending on if the priority queue is empty
	  public boolean isEmpty() {
	    return size() == 0;
	  }

	  // Clears everything inside the heap, O(n)
	  public void clear() {
	    heap.clear();
	  }

	  // Return the size of the heap
	  public int size() {
	    return heap.size();
	  }
	  private void swap(int a,int b) {
		  int tempA = heap.get(a);
		  int tempB = heap.get(b);
		  
		  heap.set(a, tempB);
		  heap.set(b, tempA);
	  }
	  @Override
	  public String toString() {
	    return heap.toString();
	  }
}
