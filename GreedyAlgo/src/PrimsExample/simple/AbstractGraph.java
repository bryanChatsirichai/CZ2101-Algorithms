package PrimsExample.simple;

public abstract class AbstractGraph {
    protected int V;
    protected int E;

    public AbstractGraph(int V) {
        assert V > 0;
        this.V = V;
        this.E = 0;
        
    }

    public int getV() { 
    	return V;
    	}
    public int getE() { 
    	return E;
    	}

    public abstract void addEdge(int u, int v, int weight);
    
    public abstract int getWeight(int u, int v);
}
