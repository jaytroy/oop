package nl.rug.oop.rts.model;

public class Edge {
    private int id;
    private String name;
    private Node node1;
    private Node node2;

    public Edge(int id, String name, Node node1, Node node2) {
        this.id = id;
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
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

    public Node getNode1() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }
}
