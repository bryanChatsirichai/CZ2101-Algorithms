package PartAver2;
import java.util.Random;

public class Graph {
	public static final int MIN_WEIGHT = 1;
    public static final int MAX_WEIGHT = 20;

    public static void Random(AbstractGraph g, int E) {
        int V = g.getV();
        
        /*
        if(E < V)
            throw new RuntimeException("|E| must be at least >= |V|");
         */
        
        
        Random random = new Random();
        ConnectEdges(g,E);
        GenerateRandomEdges(g, E);
    }

    
    private static void GenerateRandomEdges(AbstractGraph g, int E) {
    	//System.out.println("part2");
        Random random = new Random();
        int V = g.getV();
        int u, v = -1;

        do {
        	if(g.getE() >= E) {
        		break;
        	}
            u = RandomV(random, V);
            v = RandomV(random, V);
            if(u == v) {
            	//System.out.println("cancel self loop");
            	continue; //no self loop
            }
            
            if(g.getWeight(u, v) != 0) {
            	//System.out.println("cancel overwrite");
            	continue;
            }
            int weight = RandomWeight(random);
            g.addEdge(u, v, weight);
            //System.out.println(u + "->" + (v) + " weight " + weight );
            //System.out.println("edge count " + g.getE());
            
        }while(g.getE() < E);
    }

    private static void ConnectEdges(AbstractGraph g,int E) {
    	//System.out.println("part1");
        Random random = new Random();
        int V = g.getV();
        
        for(int u = 0; u < V && g.getE() < E; u++) {
            int v = (u+1) % V;
            int weight = RandomWeight(random);

            g.addEdge(u+1, v+1, weight);
            //System.out.println(u+1 + "->" + (v +1) + " weight " + weight );
            //System.out.println("edge count " + g.getE());
        }

        //g.addEdge(V, 1, RandomWeight(random));
        //System.out.println("exiting");
        //System.out.println("edge count " + g.getE());
        //System.out.println();

    }

    private static int RandomV(Random rand, int V) {
        return rand.nextInt(V) + 1; // Generates from 1 - V;
    }

    private static int RandomWeight(Random rand) {
        return MIN_WEIGHT + rand.nextInt(MAX_WEIGHT - MIN_WEIGHT); // Generates from MIN_WEIGHT - MAX_WEIGHT;
    }
}
