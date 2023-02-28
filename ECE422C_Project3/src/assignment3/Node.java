package assignment3;

import java.util.ArrayList;
import java.util.Set;

public class Node {
    private String node;
    private ArrayList<Node> adjacent;

    public Node(String node){
        this.node = node;
        this.adjacent = new ArrayList<Node>();
    }

    public String getNode(){
        return this.node;
    }

    public ArrayList<Node> getAdjacencyList(){
        return this.adjacent;
    }

    public void addAdjacent(Node adjacent){
        this.adjacent.add(adjacent);
    }
}
