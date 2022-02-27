package partA;
import java.util.*;

class Emp implements Comparable<Emp>{
	String name;
	int pay;
	public Emp(String n,int p) {
		this.name = n;
		this.pay = p;
	}
	@Override
	public int compareTo(Emp o) {
		return this.pay - o.pay;
	}
	
	public String toString() {
		return this.name;
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<Emp> queue = new PriorityQueue<>();
		queue.add(new Emp("ss", 10));
		queue.add(new Emp("aa", 2));
		System.out.println(queue.poll());
	}

}
