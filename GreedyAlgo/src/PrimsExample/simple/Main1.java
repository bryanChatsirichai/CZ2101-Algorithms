package PrimsExample.simple;
import java.util.*;
public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdjMatrix g = new AdjMatrix(6);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 2, 6);
		g.addEdge(1, 2, 6);
		
		g.addEdge(1, 4, 4);
		g.addEdge(1, 3, 3);
		g.addEdge(2, 3, 1);
		
		g.addEdge(4, 3, 2);
		g.addEdge(4, 5, 7);
		g.addEdge(3, 5, 3);
		
		PrimsAlgo primsAlgo = new PrimsAlgo(g);
		primsAlgo.primMST(0);
	}

}
