package PrimsExample.simple;
import java.util.*;
public class PrimsAlgo {
	private AdjMatrix g;
	private int V;
	private Boolean[] mstSet;
	private Integer[] dist;
	private Integer[] parent;
	private int mstCost = 0;
	
	public PrimsAlgo(AdjMatrix g) {
		this.g = g;
		this.V = g.getV();
		this.mstSet = new Boolean[V];
		this.dist = new Integer[V];
		this.parent = new Integer[V];
		
	}
	
	public void primMST(int start) {
		
		//set all unvisited
		Arrays.fill(mstSet, false);
		
		//keep track of weight assign to each node
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		//keep track of edges of mst to print, the parent of the nodes
		Arrays.fill(parent, -1);
		
		//set the starting
		int n = g.V - 1; //number of edge for mst
		dist[start] = 0; //to get 1st pick
		parent[start] = -1; //start node has no parent
		for(int i = 0;i<n;i++) {
			int u = selectMinVertex(mstSet, dist); //get relax Node and smallest distance
			mstSet[u] = true; //mark as visited
			
			//relax adjacent vertices not in mstSet
			for(int j = 0;j<g.V;j++) {
				/*
				 * 3 constraints to relax
				 * edge is present from u to j
				 * vertex j not in mstSet
				 * edge weight is smaller than current edge weight
				 */
				if(g.matrix[u][j] != 0 && mstSet[j] == false && g.matrix[u][j] < dist[j]) {
					dist[j] = g.matrix[u][j];
					parent[j] = u;
					
				}
			}
				
		}
		getMstCost(dist);
		printMst();
	}
	
	private int selectMinVertex(Boolean[] mstSet,Integer[] dist) {
		int minValue = Integer.MAX_VALUE;
		int minVertex = -1;
		for(int i = 0;i < g.V;i++) {
			if(mstSet[i] == false && dist[i] < minValue) {
				minVertex = i;
				minValue = dist[i];
			}
		}
		System.out.println("min vertex " + minVertex);
		return minVertex;
	}
	private void getMstCost(Integer[] dist) {
		int total = 0;
		for(int i = 0;i<this.V;i++) {
			total = total + dist[i];
		}
		mstCost = total;
		System.out.println(mstCost);
	}
	
	public void printMst() {
		for(int i = 0;i<V;i++) {
			int u = parent[i]; //parent
			System.out.println("Edge\tWeight");
			if(u != -1) {
				System.out.println(u + " -> " + i + "    " + dist[i]);
			}
		}
	}
	
}
