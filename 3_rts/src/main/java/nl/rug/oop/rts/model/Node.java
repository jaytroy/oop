package nl.rug.oop.rts.model;

import nl.rug.oop.rts.model.Edge;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int id;
    private String name;
    private List<Edge> edges;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
        this.edges = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    // Method to add an edge to the node
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    // Method to remove an edge from the node
    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }
}
