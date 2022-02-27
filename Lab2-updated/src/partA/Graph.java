package partA;
import java.util.Arrays;

public class Graph {
	  public int matrix[][];

	    private int V;
	    private int E;

	    public Graph(int V) {
	    	
	        assert V > 0;
	        this.V = V;
	        this.E = 0;

	        matrix = new int[V][V];
	    }

	    public int getV() { 
	    	return V; 
	    	}
	    public int getE() {
	    	return E; 
	    	}

	    public void addEdge(int u, int v, int weight) {
	        assert u > 0 && u <= V;
	        assert v > 0 && v <= V;

	        matrix[u-1][v-1] = weight;
	        matrix[v-1][u-1] = weight;
	        E++;
	    }

	    @Override
	    public String toString() {
	        return Arrays.deepToString(matrix).replace("], ", "]\n");
	    }
}
