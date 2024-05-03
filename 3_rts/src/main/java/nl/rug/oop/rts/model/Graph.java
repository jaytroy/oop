package nl.rug.oop.rts.model;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import nl.rug.oop.rts.model.Edge;
import org.w3c.dom.Node;

public class Graph {
    @Getter
    private List<Node> nodes;
    @Getter
    private List<Edge> edges;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    // Method to add a node to the graph
    public void addNode(Node node) {
        nodes.add(node);
    }

    // Method to remove a node from the graph
    public void removeNode(Node node) {
        nodes.remove(node);
        // Remove edges connected to this node
        // Method to remove a node from the graph
            nodes.remove(node);
            // Remove edges connected to this node
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                if (edge.getNode1() == node || edge.getNode2() == node) {
                    edges.remove(i);
                    i--; // Decrement the loop counter since we removed an element
            }
        }
    }

    // Method to add an edge to the graph
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    // Method to remove an edge from the graph
    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }
}
