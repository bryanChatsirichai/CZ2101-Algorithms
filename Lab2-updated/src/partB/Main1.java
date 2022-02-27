package partB;

public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PQueue pq = new PQueue(5);
		Node n1 = new Node(10, 5);
		Node n2 = new Node(1, 2);
		Node n3 = new Node(7, 4);
		Node n4 = new Node(4, 0);
		pq.insert(n1);
		pq.insert(n2);
		pq.insert(n3);
		pq.insert(n4);
		pq.display();
		System.out.println();
		System.out.println(pq.extract_min());
		System.out.println();
		pq.display();
		System.out.println();
		pq.increase_NodeCost(1, 110);
		pq.display();
		System.out.println();
		
		pq.decrease_NodeCost(10,1);
		pq.display();
	}

}
