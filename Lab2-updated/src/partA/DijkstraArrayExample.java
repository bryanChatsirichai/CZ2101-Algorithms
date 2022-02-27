package partA;
import java.util.*;
public class DijkstraArrayExample {
	public static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // https://www.geeksforgeeks.org/wp-content/uploads/Fig-11.jpg
        Graph g = new Graph(9);
    
        g.addEdge(1, 2, 4);
        g.addEdge(1, 8, 8);
        
        g.addEdge(2, 3, 8);
        g.addEdge(2, 8, 11);

        
        g.addEdge(3, 4, 7);
        g.addEdge(3, 6, 4);
        g.addEdge(3, 9, 2);


        g.addEdge(4, 5, 9);
        g.addEdge(4, 6, 14);


        g.addEdge(5, 6, 10);


        g.addEdge(6, 7, 2);


        g.addEdge(7, 8, 1);
        g.addEdge(7, 6, 2);

        g.addEdge(8, 9, 7);


        
        
        System.out.println(g);

        Dijikstra(g, 1);
    }

    public static void Dijikstra(Graph g, int source) {
        int V = g.getV();
        Node[] nodes = new Node[V]; //hold V amount of nodes

        for(int i = 0; i < V; i++)
            nodes[i] = new Node(i + 1, INFINITY);//initial lise distance to all nodes from source as infinity
        nodes[source-1].distance = 0;//source to itself is 0

        for(int i = 0; i < V - 1; i++) {
            int u = MinDistance(g, nodes);
            nodes[u].visited = true; //priority is lowest source to next node

            for(int j = 0; j < V; j++) {
                if(!nodes[j].visited && g.matrix[u][j] != 0 && nodes[u].distance != INFINITY && nodes[u].distance + g.matrix[u][j] < nodes[j].distance)
                    nodes[j].distance = nodes[u].distance + g.matrix[u][j];
            }
        }

        Print(g, nodes);
    }

    public static int MinDistance(Graph g, Node[] nodes) {
        int min = INFINITY;
        int index = -1;
        for(int i = 0; i < g.getV(); i++) {
            if((!nodes[i].visited) && nodes[i].distance <= min) {
            	//node not visited/relax and the next smallest
                min = nodes[i].distance;
                index = i;
            }
        }

        return index;
    }

    public static void Print(Graph g, Node nodes[]) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < g.getV(); i++)
            System.out.println(i+1 + " \t\t " + nodes[i].distance);
    }
}
