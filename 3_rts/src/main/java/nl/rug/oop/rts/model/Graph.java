package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.controller.GraphObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles the Graph in our simulator.
 */
public class Graph {
    @Getter
    private List<Node> nodes;
    @Getter
    private List<Edge> edges;
    private List<GraphObserver> observers;
    @Getter
    @Setter
    private Node edgeStartNode;

    /**
     * This is the constructor of our graph. It contains lists of edges, nodes and observers.
     */
    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * This function adds a node to the list of nodes.
     * @param node the node we want to add
     */
    public void addNode(Node node) {
        nodes.add(node);
        notifyObservers();
    }

    /**
     * This function removes the given node from the list of nodes. It also removes any edge that was connected to it.
     * @param node given node
     */
    public void removeNode(Node node) {
        List<Edge> edgesToRemove = new ArrayList<>(node.getEdges());
        for (Edge edge : edgesToRemove) {
            removeEdge(edge);
        }

        nodes.remove(node);
        notifyObservers();
    }

    /**
     * This function adds an edge between 2 nodes.
     * @param edge edge we want to add
     */
    public void addEdge(Edge edge) {
        edges.add(edge);
        edge.getNode1().addEdge(edge);
        edge.getNode2().addEdge(edge);
        notifyObservers();
    }

    /**
     * This function removes a given edge for both nodes it is connected to.
     * @param edge the edge we want to remove
     */
    public void removeEdge(Edge edge) {
        edges.remove(edge);
        edge.getNode1().removeEdge(edge);
        edge.getNode2().removeEdge(edge);
        notifyObservers();
    }

    /**
     * This function adds observers to the list.
     * @param observer the observer we want to add
     */
    public void addObserver(GraphObserver observer) {
        observers.add(observer);
    }

    /**
     * This function notifies the observers about updates.
     */
    public void notifyObservers() {
        for (GraphObserver observer : observers) {
            observer.update(this);
        }
    }

    /**
     * This function gets the selected node from the list.
     * @return the node if any is selected, null if no selected node
     */
    public Node getSelectedNode() {
        for (Node node : nodes) {
            if (node.isSelected()) {
                return node;
            }
        }
        return null;
    }

    /**
     * This function gets the selected edge from the list.
     * @return the edge if any selected, null if no selected edge
     */
    public Edge getSelectedEdge() {
        for (Edge edge : edges) {
            if (edge.isSelected()) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Searches for a node in the list by its name.
     * @param name the matching name
     * @return the node if we found it, null otherwise
     */
    public Node getNodeByName(String name) {
        for (Node node : nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    /**
     * This retrieves the next nodeID to be added.
     * @return size of the list
     */
    public int getNextNodeId() {
        return nodes.size();
    }

    /**
     * Retrieves next edgeID to be added.
     * @return size of the list
     */
    public int getNextEdgeId() {
        return edges.size();
    }

    /**
     * Gets a random edge from the list.
     * @param edges list of edges
     * @return the random edge
     */
    public Edge getRandomEdge(List<Edge> edges) {
        Random random = new Random();
        int index = random.nextInt(edges.size());
        return edges.get(index);
    }

}
