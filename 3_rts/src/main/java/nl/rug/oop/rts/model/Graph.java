package main.java.nl.rug.oop.rts.model;

import java.util.List;


import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Node;

public class Graph {

    private List<Node> nodes;

    private List<Edge> edges;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes(){
        return nodes;
    }

    public List<Edge> getEdges(){
        return edges;
    }


    public void addNode(Node node) {
        nodes.add(node);
    }

    public void removeNode(Node node) {
        nodes.remove(node);
        nodes.remove(node);
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                if (edge.getNode1() == node || edge.getNode2() == node) {
                    edges.remove(i);
                    i--;
            }
        }
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }
}
