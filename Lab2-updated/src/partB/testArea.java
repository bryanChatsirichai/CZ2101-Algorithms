package partB;

public class testArea {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new Graph(9);
	
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 7, 8);
		graph.addEdge(1, 2, 8);
		graph.addEdge(1, 7, 11);
		graph.addEdge(2, 3, 7);
		graph.addEdge(2, 8, 2);
		graph.addEdge(2, 5, 4);
		graph.addEdge(3, 4, 9);
		graph.addEdge(3, 5, 14);
		graph.addEdge(4, 5, 10);
		graph.addEdge(5, 6, 2);
		graph.addEdge(6, 7, 1);
		graph.addEdge(6, 8, 6);
		graph.addEdge(7, 8, 7);
		
		graph.addEdge(1, 0, 4);
		graph.addEdge(7, 0, 8);
		graph.addEdge(2, 1, 8);
		graph.addEdge(7, 1, 11);
		graph.addEdge(3, 2, 7);
		graph.addEdge(8, 2, 2);
		graph.addEdge(5, 2, 4);
		graph.addEdge(4, 3, 9);
		graph.addEdge(5, 3, 14);
		graph.addEdge(5, 4, 10);
		graph.addEdge(6, 5, 2);
		graph.addEdge(7, 6, 1);
		graph.addEdge(8, 6, 6);
		graph.addEdge(8, 7, 7);
		graph.dijkstra(0, 4);
		
		/*
		graph.addEdge(0, 1, 5);
		graph.addEdge(0, 3, 21);
		graph.addEdge(1, 2, 40);
		graph.addEdge(2, 3, 13);
		graph.addEdge(3, 5, 41);
		graph.addEdge(2, 4, 19);
		graph.addEdge(4, 5, 32);
		graph.addEdge(4, 6, 14);
		graph.addEdge(5, 6, 8);
		graph.dijkstra(0, 6);
		*/
		/*
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 2, 4);
		graph.addEdge(1, 4, 7);
		graph.addEdge(1, 2, 4);
		graph.addEdge(2, 3, 3);
		graph.addEdge(1, 3, 2);
		graph.addEdge(2, 4, 5);
		graph.addEdge(4, 3, 4);
		graph.addEdge(4, 5, 7);
		graph.addEdge(3, 5, 6);
		
		graph.addEdge(1, 0, 1);
		graph.addEdge(2, 0, 4);
		graph.addEdge(4, 1, 7);
		graph.addEdge(2, 1, 4);
		graph.addEdge(3, 2, 3);
		graph.addEdge(3, 1, 2);
		graph.addEdge(4, 2, 5);
		graph.addEdge(3, 4, 4);
		graph.addEdge(5, 4, 7);
		graph.addEdge(5, 3, 6);
		graph.displayGraph();
		graph.dijkstra(0, 5);
		*/
	}

}
