package PartAver2;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DijkstraArrayExample {

	 	public static final int INFINITY = Integer.MAX_VALUE;
		public static final int CONSTANT = 10;

	    public static void main(String[] args) throws Exception{
	    	System.out.println("Part A");
	    	
	    	Scanner sc = new Scanner(System.in);
	    	int count;//
	    	System.out.println("Enter how many times");
	    	int size = sc.nextInt(); //number of iteration
		    List<Long> timeList = new ArrayList<>(size);
		    List<Integer> numberOfNodesList = new ArrayList<Integer>(size);
		    List<Integer> numberOfEdgesList = new ArrayList<Integer>(size);

	    	for(count = 1;count <= size;count++){
		    		

		       // System.out.print("Please enter |V|: ");
		       // int V = sc.nextInt();
	    		System.out.println("count " + count);
		        int V = count * CONSTANT;
		        System.out.println("number of vertex " + V);
		        if(V < 4) {
		        	throw new RuntimeException("V should be higher");
		        }
	
		        //System.out.printf("Please enter |E|: ");
		        //int E = sc.nextInt();
		        int n = count * CONSTANT;
		        
		        //minimum of edge to connect  spanning tree * 3
		        //int E =  (n-1) * 2;
		        float F = (n * (n-1) / 2) / 5;
		        int E = Math.round(F);
		        //Complete graph
		        //int E = ((n*(n-1)/2));
		        
		        System.out.println("Number of edge " + E);
		        /*
		        if(E < V) {
		            throw new RuntimeException("|E| has to be >= |V|");
		        }
		        */
		        if(E < V - 1 || E > (V*(V-1))/2) {
		        	//minimum number of edges should at least have path from 1st node to last node
		        	//max number of edges is for complete graph
		        	throw new RuntimeException("Too edge insufficent or too much");
		        }
		        AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(V);
		        Graph.Random(g, E);
		        //System.out.println(g);
		        long start = System.nanoTime();
		        Dijikstra(g, 1);
		        long end = System.nanoTime();
		        long timeTaken = end - start;
		        System.out.println("time taken " + timeTaken);
		    	timeList.add(timeTaken);
		    	numberOfNodesList.add(V);
		    	numberOfEdgesList.add(E);
	    	}
	    	extractData(numberOfNodesList,numberOfEdgesList, timeList);
	    	System.out.println("end");
	    }
	    
	    public static void extractData(List<Integer> numberOfNodeList,List<Integer> numberOfEdgesList ,List<Long> timeList) throws IOException {
			//enter your own path file
	    	//FileWriter writer = new FileWriter("D:\\NTU\\Year2sem1\\CZ2002\\Lab2PartA-output-Non-CompleteGraph.txt");
	    	FileWriter writer = new FileWriter("C:\\NTU\\Lab2PartA-output-Non-completeGraph.txt");
	    	//FileWriter writer = new FileWriter("D:\\NTU\\Year2sem1\\CZ2002\\Lab2PartA-output-completeGraph.txt");
			
	    	writer.write("No. of Vertices \tNo. of Edges \tTime(nano)\n");
	    	for(int i = 0 ;i < numberOfNodeList.size() ;i++) {
				writer.write(numberOfNodeList.get(i) + "\t" + numberOfEdgesList.get(i) + "\t" + timeList.get(i) + System.lineSeparator());
			}
			writer.close();
	    }
	    
	    public static void Dijikstra(AdjacencyMatrixGraph g, int source) {
	        int V = g.getV();
	        Node[] nodes = new Node[V];

	        for(int i = 0; i < V; i++){
	        //make source node as 0 and rest as infinity
	        nodes[i] = new Node(i + 1, INFINITY);
	        }
	        nodes[source-1].distance = 0;
	        	
	        //O(V) relax V number of vertices
	        for(int i = 0; i < V - 1; i++) {
	        	
	        	//linear search to get minimum distance node from source to relax
	        	//cannot select already visited/relaxed node
	        	//O(V)
	            int u = MinDistance(g, nodes); 
	            nodes[u].visited = true;
	            
	            
	            //select adjacent vertices not in SPG/relaxed 
	            //O(V)
	            for(int j = 0; j < V; j++){	
	            	/* 
	            	3 Condition to  relax
	            	Edge is present from u to j
	            	Vertex j not in shortest path graph(relaxed)
	            	edge weight is smaller than current edge weight from other path from source to  j 
	            	 */
	                if(!nodes[j].visited && g.matrix[u][j] != 0 && nodes[u].distance != INFINITY && nodes[u].distance + g.matrix[u][j] < nodes[j].distance) {
	                	nodes[j].distance = nodes[u].distance + g.matrix[u][j];
	                }
	                    
	            }
	        }
	        //Print(g, nodes);
	    }

	    public static int MinDistance(AdjacencyMatrixGraph g, Node[] nodes) {
	        int min = INFINITY;
	        int index = -1;
	        for(int i = 0; i < g.getV(); i++) {
	            if(!nodes[i].visited && nodes[i].distance <= min) {
	                min = nodes[i].distance;
	                index = i;
	            }
	        }

	        return index;
	    }

	    public static void Print(AdjacencyMatrixGraph g, Node nodes[]) {
	        System.out.println("Vertex \t\t Distance from Source");
	        for (int i = 0; i < g.getV(); i++)
	            System.out.println(i+1 + " \t\t " + nodes[i].distance);
	    }
}
