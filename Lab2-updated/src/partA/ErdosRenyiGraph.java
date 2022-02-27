package partA;
import java.util.Random;
import java.util.Scanner;
public class ErdosRenyiGraph {
	 class TestCase {
	        int V;
	        int E;
	    }

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        System.out.print("Please enter desired |V|: ");
	        int V = sc.nextInt();

	        System.out.print("Please enter desired |E|: ");
	        int E = sc.nextInt();

	        System.out.print("Please enter desired probability of every node: ");
	        float P = sc.nextFloat();

	        System.out.printf("Generating Graph(V: %d, P: %f)\n", V, P);

	        Graph g = new Graph(V);
	        Random random = new Random();

	        for(int i = 1; i <= V; i++) {
	            for(int j = 1; j <= V; j++) {
	                float r = random.nextFloat();

	                if(r < P && g.getE() < E) {
	                    g.addEdge(i, j, 1);
	                }
	            }
	        }

	        System.out.println(g);
	        System.out.printf("|E|: %d)\n", g.getE());
	    }
}
