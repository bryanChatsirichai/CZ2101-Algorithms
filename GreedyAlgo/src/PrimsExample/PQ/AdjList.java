package PrimsExample.PQ;
import java.util.*;
public class AdjList {
	
	static class Edge implements Comparable<Edge> {
	   
		int from, to, cost;
	
	    public Edge(int from, int to, int cost) {
	      this.from = from;
	      this.to = to;
	      this.cost = cost;
	    }

	    @Override
	    public int compareTo(Edge other) {
	      return cost - other.cost;
	    }
	  }
	
	 int minCost;
	 private boolean[] visited;
	 private Edge[] mstSet; //
	 private int[] parent;
	 private int[] dist;
	 private PriorityQueue<Edge> pq;
	 private List<List<Edge>> graph;
	 private int V;
	 private int E;
	 public AdjList(int V){
		 this.V = V;
		 this.E = 0;
		 createEmptyGraph(); //set the adj list
	 }
	 
	 
	 public void primsAlgo(int start) {
		 
		 int n = this.V - 1; //total edges for mst
		 int edgeCount = 0;
		 minCost = 0;  
		 
		 pq = new PriorityQueue<>();
		 
		 visited = new boolean[V];
		 Arrays.fill(visited, false);
		 
		 mstSet = new Edge[n];
		 
		 dist = new int[V];
		 Arrays.fill(dist, Integer.MAX_VALUE);
		 
		 parent = new int[V];
		 Arrays.fill(parent,-1);
		 
		 dist[start] = 0;
		 addAdjcentEdges(start); //start from source
		 
		 while(!pq.isEmpty() && edgeCount != n) {
			 Edge edge = pq.poll(); //get the minimum edge
			 
			 int nodeIndex = edge.to;  // index of smallest edge adjacent node
			 
		      // Skip any edge pointing to an already visited node.
		      if (visited[nodeIndex]) {
		    	  continue;
		      }
		      
		    if(edge.cost < dist[nodeIndex]) {
			     dist[nodeIndex] = edge.cost;
				 parent[nodeIndex] = edge.from;
				 //mstSet[edgeCount] = edge;
				 edgeCount++;
				 minCost = minCost + edge.cost;
				 addAdjcentEdges(nodeIndex);
		    }

		 }
		 
		 System.out.println("min cost " + minCost);
		 getMstCost();
		 System.out.println("edge count " + edgeCount);
		 printMst();
	 }
	 
	 private void addAdjcentEdges(int nodeIndex) {
		 visited[nodeIndex] = true; //visited / relax this node
		 List<Edge> edges= graph.get(nodeIndex);
		 for(Edge e : edges) {
			 if(visited[e.to] == false) {
				 //explore all unvisted edges to node path
				 pq.offer(e);
			 }
		 }
	 }
	 
	 public void getMstCost() {
		 int total = 0;
		 for(int i = 0;i<V;i++) {
			 total = total + dist[i];
		 }
		 System.out.println("min total " + total);
	 }
	 
		public void printMst() {
			System.out.println("Edge\tWeight");
			for(int i = 0;i < V ;i++) {
				int u = parent[i]; //parent
				
				if(u != -1) {
					System.out.println(u + " -> " + i + "    " + dist[i]);
				}
			}
		}
		
	 public void addEdge(int from,int to,int cost) {
		 //undirected
		 graph.get(from).add(0,new Edge(from, to, cost));
		 graph.get(to).add(0,new Edge(to,from,cost));
		 E++;
	 }
	 
	 private void createEmptyGraph() {
		 this.graph = new ArrayList<List<Edge>>(V);
		 for(int i = 0;i<V;i++) {
			 graph.add(new ArrayList<Edge>());
		 }
	 }
	 
		public void printGraph() {
			for(int i = 0;i<V;i++) {
				System.out.print("Vertex " + i);
				List<Edge> edges = graph.get(i);
				for(Edge edge : edges) {
					System.out.print(" -> " + edge.to + "(" + edge.cost+")");
				}System.out.println();
			}
		}
}
