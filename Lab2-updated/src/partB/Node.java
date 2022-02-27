package partB;

public class Node {
	private int node;
	private int cost;
    public Node(int node, int cost) { 
        this.node = node; 
        this.cost = cost; 
    }
    
    public int getCost() {
    	return this.cost;
    }
    
    public int getNode() {
    	return this.node;
    }
    
    public void setCost(int c) {
    	this.cost = c;
    }
    
    public String toString() {
    	return "node " + this.node + " cost is " + this.cost;
    }
    
    
}