package partB;
import java.util.*;

public class Graph {
	
	//an edge class represent a directed edge
	//between two nodes with a certain cost
	public static class Edge{
		int to;
		int cost;
		public Edge(int to,int cost) {
			this.to = to;
			this.cost = cost;
		
			
		}
	}
	
	private final int n;
	private List<List<Edge>> adj_list;
	private Integer[] prev; //keep track node parent
	private Integer[] dist; //keep track smallest cost from source to specific Node
	private PQueue pq;
	
	public Graph(int n) {
		this.n = n;
		
		createEmptyGraph(); //set adj list
	}
	
	//construct an empty graph in adj_list with n nodes including source and sink nodes
	private void createEmptyGraph() {
		adj_list = new ArrayList<List<Edge>>(n);
		for(int i = 0;i < n;i++) {
			adj_list.add(new ArrayList<>()); //each index is an list of edge
		}
	}
	
	private void findShortestPath(int start,int end) {
		List<Integer> path = new LinkedList<>();
		for(Integer at = end;at != null; at = prev[at]) {
			path.add(0, at);
		}
		System.out.println("shortest path:");
		System.out.println(path);
	}
	
	public void dijkstra(int start,int end) {
		
		//keep an index priority queue of the next most promising node to visit
		pq = new PQueue(n);
		Node startNode = new Node(start,0); //set source Node and the cost reach there 0 since it is itself
		pq.insert(startNode);
		
		//maintain an array of the minimum distance from source to each node
		dist = new Integer[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0; //optimal to start 0 as start from there
		
		
		boolean visited[] = new boolean[n]; //keep track which node processed
		prev = new Integer[n]; //keep track current node parent
		Arrays.fill(prev, null);
		
		while(!pq.isEmpty()) {
			Node curNode = pq.extract_min(); //remove node with min value to process
			System.out.println(curNode);
			int nodeId = curNode.getNode();
			int minValue = curNode.getCost();
			visited[nodeId] = true; //mark as visited so don't revisit
			
			/*
			//already found a better path routing through other set of nodes before processing this node
			//no point processing this node
			if(dist[nodeId] < minValue) {
				continue;
			}
			*/
			
			//get all edges going out from curNode
			for(Edge edge:adj_list.get(nodeId)) {
				//System.out.println("visiting node " + edge.to);
				//cannot shorter a path by revisiting a node that already visited/process
				if(visited[edge.to] == true) {
					continue;
				}
				
				//relax edge by updating minimal cost if applicable
				//System.out.println(dist[nodeId] + " + " + edge.cost );
				int newDist = dist[nodeId] + edge.cost;
				// System.out.println("distance " + newDist);
				//System.out.println(newDist + " < " + dist[edge.to]);
				if(newDist < dist[edge.to]) {
					dist[edge.to] = newDist;
					
					//if shorter path found parent updated
					//prev edge discarded and selected new edge 
					prev[edge.to] = nodeId; 
				
				//insert the cost of going to a node for the first time in the PQ
				//or try and update it to a better value by calling decrease
				if(!pq.contains(edge.to)) {
					Node newNode = new Node(edge.to,newDist);
					pq.insert(newNode);
				}
				else {
					pq.decrease_NodeCost(edge.to, newDist);
				}
			}
			/*once process the end node can return early
			without necessarily visiting the whole graph
			as we cannot get a shorter path by routing any other nodes
			since Dijkstra is greedy and there are no negative weights
			if(nodeId == end) {
				return;
			}*/
		}
		
	}		
		if(visited[end] != false) {
			findShortestPath(start, end);
		}
	printArr();
}
	
	public void addEdge(int from,int to,int cost) {
		//cost cannot be negative;
		adj_list.get(from).add(new Edge(to, cost)); //node at index i points to node j with cost x
	}
	
	public void printArr() {
		System.out.println("Node    Distance from Source");
		for(int i = 0;i<n;i++) {
			System.out.println(i + "   	" + dist[i]);
		}
	}
	
	public void displayGraph() {
		System.out.println("The graph is:");
		int src_node = 0;
		for(List<Edge> list : adj_list) {
			for(Edge edge:list) {
				System.out.println("Node:" + src_node + " -> " + edge.to + " (" + edge.cost + ")");
			}
			src_node++;
		}
	}
}
