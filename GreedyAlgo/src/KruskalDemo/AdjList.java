package KruskalDemo;
import java.util.*;

public class AdjList {
	
	static class Edge implements Comparable<Edge>{
		
	    // 'from' and 'to' are nodes indexes and 'cost' is the cost of taking this edge.
		int from,to,cost;
		
		public Edge(int from,int to,int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
		public String toString() {
			return from + " <--> " + to + " (" + cost + ")";
		}
	}
	
	static class Node{
		//int id;
		int rank;
		int parent;
		
		public Node(int rank,int parent) {
			//this.id = id;
			this.rank = rank;
			this.parent = parent;
		}
	}
	private int V;
	private int E;
	private LinkedList<Edge> unSortedEdges;
	private PriorityQueue<Edge> pq;
	private List<List<Edge>> graph;
	
	//stuff got algorithm
	private Edge[] mst; //output for minimum spanning tree
	private int minCost; //minimum cost of the tree
	private int sz[]; //sizes of each components
	private Node dsuf[]; //stores the nodes info index of array is node id as well
 
	
	
	public AdjList(int V) {
		this.V = V;
		this.E = 0;
		createEmptyGraph(V);
		//pq = new PriorityQueue<>();
		unSortedEdges = new LinkedList<>();
	}
	
	private int findAbsoluteParent(int v) {
		//find the AP of node v and do path compression
		if(dsuf[v].parent == -1) {
			return v; //found the parent index in the array
		}
		return dsuf[v].parent = findAbsoluteParent(dsuf[v].parent);
		
	}
	
	private void unionByRank(int fromAP,int toAP) {
		
		if(isConnected(fromAP, toAP)) {
			//if already in same group do nothing
			return;
		}
		
		//merging lower rank subset to the higher rank subset
		if(dsuf[fromAP].rank > dsuf[toAP].rank) {
			//rank of fromAP higher
			dsuf[toAP].parent = fromAP;
		}
		else if(dsuf[fromAP].rank < dsuf[toAP].rank) {
			//rank of toAP higher
			dsuf[fromAP].parent = toAP;
		}
		else {
			//both same so either can
			//must increase the rank of 1 of them that is the new AP
			//assume fromAP will be the new AP
			//rank only increase when 2 set same rank get merge
			dsuf[toAP].parent = fromAP;
			dsuf[fromAP].rank++;
		}
	}
	
	private boolean isConnected(int from,int to) {
		return findAbsoluteParent(from) == findAbsoluteParent(to);
	}
	
	
	public void kruskalSolveByRank() {
		System.out.println("solve by rank");
		int numOfEdges = 0;
		minCost = 0;
		mst = new Edge[V - 1];
		dsuf = new Node[V];
	
		for(int i = 0;i<V;i++) {
			dsuf[i] = new Node(0, -1); //id same as index, rank is height of initial tree start from 0, assume all nodes disjoint sets and no parent(AP parent of itself)
		}
		
		
		pq = new PriorityQueue<>(unSortedEdges);
		
		while(!pq.isEmpty() && numOfEdges < V - 1) {
			Edge edge = pq.poll();
			
			//find AP of sub set the node belongs to
			int fromAP = findAbsoluteParent(edge.from);
			int toAp = findAbsoluteParent(edge.to);
			//System.out.println("fromAP " + fromAP);
			//System.out.println("toAp " + toAp);
			if(fromAP == toAp) {
			    // Skip this edge to avoid creating a cycle in MST.
				continue; 
			}
			//union both sets as no cycle form
			unionByRank(fromAP, toAp);
			
			//update MST
			minCost = minCost + edge.cost; //update the MIN cost
			mst[numOfEdges] = edge; //update what edges use to construct MST
			numOfEdges++;//Total edges should be V - 1 for graph that is not disjoint
		}
		if(numOfEdges != V - 1) {
			System.out.println("graph disjoint no MST formed");
		}
		else {
			System.out.println("MST can be formed");
			System.out.println("MIN cost: "  + minCost);
		}
	}
	
	
	private void unionBySize(int fromAP,int toAP) {
		
		//elements already same group do nothing
		int root1 = findAbsoluteParent(fromAP);
		int root2 = findAbsoluteParent(toAP);
		if(root1 == root2) {
			return;
		}
		
		//merging two components/sets together
		//merge smaller component set into the larger one
		if(sz[root1] < sz[root2]) {
			sz[root2] = sz[root2] + sz[root1];
			dsuf[root1].parent = root2;
		}
		else if(sz[root1] > sz[root2]) {
			sz[root1] = sz[root2] + sz[root1];
			dsuf[root2].parent = root1;
		}
		else {
			//both same so either can
			//must increase the size of 1 of them that is the new AP
			//assume root2 will be the new AP
			sz[root2] = sz[root2] + sz[root1];
			dsuf[root1].parent = root2;
		}
		
	}
	
	public void kruskalSolveBySize() {
		System.out.println("solve by size");
		int numOfComponents = V;
		System.out.println("Starting number of components " + numOfComponents);
		
		int numOfEdges = 0;
		minCost = 0;
		mst = new Edge[V - 1];
		sz = new int[V];
		dsuf = new Node[V];
		
		for(int i = 0;i<V;i++) {
			sz[i] = 1; //each component is originally of size 1;
			dsuf[i] = new Node(0, -1); //id same as index, rank is height of initial tree start from 0, assume all nodes disjoint sets and no parent(AP parent of itself)
		}
		
		pq = new PriorityQueue<>(unSortedEdges);

		while(!pq.isEmpty() && numOfEdges < V - 1) {
			Edge edge = pq.poll();
			
			//check the AP of the node and see if same set
			// Skip this edge to avoid creating a cycle in MST.
			int fromAP = findAbsoluteParent(edge.from);
			int toAp = findAbsoluteParent(edge.to);
			if(isConnected(fromAP, toAp)) {
				continue;
			}
			//union both sets as no cycle form
			unionBySize(fromAP,toAp);
			
			//since the roots found are different 
			//number of components sets has decrease by 1
			//if have MST number of components should be 1 the entire MST with 1 AP
			//1 set with all the nodes
			numOfComponents--;
			System.out.println("number of components left after union " + numOfComponents);
			
			//update MST
			minCost = minCost + edge.cost; //update the MIN cost
			mst[numOfEdges] = edge; //update what edges use to construct MST
			numOfEdges++;//Total edges should be V - 1 for graph that is not disjoint
		}
		if(numOfEdges != V - 1) {
			System.out.println("graph disjoint no MST formed");
		}
		else {
			System.out.println("MST can be formed");
			System.out.println("MIN cost: "  + minCost);
		}
		/*
		for(int i = 0;i<sz.length;i++) {
			System.out.println(sz[i]);
		}
		*/
	}
	
	
	public void printMSTGraph() {
		for(Edge edge : mst) {
			System.out.println(edge);
		}
	}
	
	public void addEdge(int from,int to,int cost) {
		
		//undirected graph
		Edge uv = new Edge(from,to,cost);
		graph.get(from).add(uv);
		
		//pq.add(uv);
		unSortedEdges.add(0, uv);
		
		Edge vu = new Edge(to, from, cost);
		graph.get(to).add(vu);
		this.E++;
	}
	
	private void createEmptyGraph(int V) {
		graph = new ArrayList<List<Edge>>(V);
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
	public void printPQ() {
		for(Edge edge : pq) {
			System.out.println(edge);
		}
		
	}
}
