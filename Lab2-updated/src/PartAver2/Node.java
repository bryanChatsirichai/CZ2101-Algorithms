package PartAver2;

public class Node {
	public int value;
    public int distance;
    public boolean visited;

    public Node(int value) {
        this.value = value;
        this.distance = 0;
        this.visited = false;
    }
    

    public Node(int value, int distance) {
        this.value = value;
        this.distance = distance;
        this.visited = false;
    }
}
