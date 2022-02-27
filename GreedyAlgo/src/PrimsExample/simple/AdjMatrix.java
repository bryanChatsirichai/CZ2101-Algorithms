package PrimsExample.simple;

import java.util.Arrays;

public class AdjMatrix extends AbstractGraph{
	
	int[][] matrix = new int [V][V]; //matrix
	
	public AdjMatrix(int V) {
		super(V);
		
	}

	@Override
	public void addEdge(int u, int v, int weight) {
		//undirected graph
		matrix[u][v] = weight;
		matrix[v][u] = weight;
		this.E++;
	}

	@Override
	public int getWeight(int u, int v) {
		return matrix[u][v];
	}
	
	public String printGraph() {
        return Arrays.deepToString(matrix).replace("], ", "]\n");

	}
}
