package partB;



public class PQueue {
	public Node arr[];
	int capacity; // maximum possible size of min heap
    int heap_size; // Current number of elements in min heap
	public PQueue(int size) {
		this.capacity = size;
		this.heap_size = 0;
		this.arr = new Node[this.capacity];
	}
	
	public void insert(Node node) {
		if(heap_size == capacity) {
			System.out.println("heap is full");
			return;
		}
		//insert new key in the end
		heap_size++;
		int i = heap_size - 1;
		this.arr[i] = node;
		
	    // Fix the min heap property if it is violated bottom up
		while(i > 0 && this.arr[(i-1)/2].getCost() > this.arr[i].getCost() ) {
			//swap
			Node temp = this.arr[i];
			this.arr[i] = this.arr[(i-1)/2];
			this.arr[(i-1)/2] = temp;
			i = (i-1)/2;
		}
	}
	
	public Node extract_min() {
		if(heap_size < 1) {
			System.out.println("heap empty nothing to extract");
			return null;
		}
		Node minNode = arr[0]; //root always the smallest
		arr[0] = arr[heap_size - 1];//last leaf node move to root
		
		//reduce heap size by 1 to 'remove' that node	
		arr[heap_size] = null;
		heap_size--;
		min_heapify(0); //perform assuming rest of the nodes are already in heap form
		return minNode;
	}
	
	public boolean contains(int nodeId) {
		//System.out.println("heap size " + heap_size);		
		for(int i = 0;i < heap_size;i++) {
			if(arr[i].getNode() == nodeId) {
				return true;
			}
		}
		return false;
	}
	
	public Node peek_min() {
		if(heap_size < 1) {
			System.out.println("heap empty nothing to extract");
			return null;
		}
		Node minNode = arr[0]; //root always the smallest
		return minNode;
	}
	public void increase_NodeCost(int node,int cost) {
		//perform linear search as could be any node
		int index = -1;
		for(int i = 0;i < heap_size;i++) {
			if(arr[i].getNode() == node) {
				index = i;
				break;
			}
		}
		if(index == -1) {
			System.out.println("node not found");
			return;
		}
		if(cost < arr[index].getCost()) {
			System.out.println("value too small");
			return;
		}
	    //new number may have to go down
		arr[index].setCost(cost); //update value
		min_heapify(index);
	}
	
	public void decrease_NodeCost(int node,int cost) {
		//perform linear search as could be any node
		int index = -1;
		for(int i = 0;i < heap_size;i++) {
			if(arr[i].getNode() == node) {
				index = i;
				break;
			}
		}
		if(index == -1) {
			System.out.println("node not found");
			return;
		}
		if(cost > arr[index].getCost()) {
			System.out.println("cost is " + cost);
			System.out.println("value too big");
			return;
		}
		arr[index].setCost(cost); //update value
	    //new number may have to go up
		while(index > 0 && arr[index].getCost() < arr[(index - 1)/2].getCost()) {
			//swap
			Node temp = this.arr[index];
			this.arr[index] = this.arr[(index-1)/2];
			this.arr[(index-1)/2] = temp;
			index = (index-1)/2;
		}
	}
	
	
	private void min_heapify(int i){
		int left = (i * 2) + 1;
		int right = (i * 2) + 2;
		int smallest;
		if(left < heap_size && this.arr[left].getCost() < this.arr[i].getCost()) {
			smallest = left;
		}
		else {
			smallest = i;
		}
		if(right < heap_size && this.arr[right].getCost() < this.arr[smallest].getCost()) {
			smallest = right;
		}
		if(smallest != i) {
			//swap
			Node temp = this.arr[smallest];
			arr[smallest] = arr[i];
			arr[i] = temp;
		}

	}
	
	public boolean isEmpty() {
		return this.heap_size == 0;
	}
	public void display() {
		for(int i = 0;i<heap_size;i++) {
			System.out.println(arr[i]);
		}
	}
}
