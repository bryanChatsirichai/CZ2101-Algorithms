package PrimsExample.PQ;
import java.util.*;
public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("prim adj list and pq");
		AdjList g = new AdjList(7);
		/*
		g.addEdge(0, 1, 4);
		g.addEdge(0, 2, 6);
		g.addEdge(1, 2, 6);
		
		g.addEdge(1, 4, 4);
		g.addEdge(1, 3, 3);
		g.addEdge(2, 3, 1);
		
		g.addEdge(4, 3, 2);
		g.addEdge(4, 5, 7);
		g.addEdge(3, 5, 3);
		*/
		
		//tutorial graph
		g.addEdge(0, 1, 2);
		g.addEdge(1, 2, 4);
		g.addEdge(2, 4, 2);
		g.addEdge(3, 4, 2);
		g.addEdge(5, 3, 5);
		g.addEdge(5, 0, 7);
		g.addEdge(0, 6, 3);
		g.addEdge(6, 4, 3);
		g.addEdge(1, 6, 6);
		g.addEdge(6, 3, 1);


		g.primsAlgo(6);
	}

}
