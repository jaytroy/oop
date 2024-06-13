package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

/**
 * Action handler for edges.
 */
public class EdgeActions {
    /**
     * This function handles adding an edge. You can only add an edge if you selected a node beforehand. If yes, then
     * an input dialogue asks you to name the edge and then give it the node you want it to lead to.
     *
     * @param graph     The graph in which we have the node
     * @param mainFrame The frame where this happens
     */
    public void addEdge(Graph graph, MainFrame mainFrame) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            String edgeName = getUniqueEdgeName(graph, mainFrame);
            if (edgeName == null) {
                return;
            }

            Node node2 = getValidNodeByName(graph, mainFrame);
            if (node2 == null) {
                return;
            }

            if (!selectedNode.isConnected(node2)) {
                Edge edge = new Edge(graph.getNextEdgeId(), edgeName, selectedNode, node2);
                graph.addEdge(edge);
                graph.notifyObservers();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "These nodes are already connected. Please " +
                        "choose different nodes.");
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No node is selected. Please select a node first.");
        }
    }

    private String getUniqueEdgeName(Graph graph, MainFrame mainFrame) {
        String edgeName;
        boolean nameExists;

        do {
            edgeName = JOptionPane.showInputDialog(mainFrame, "Enter a name for the edge:");
            if (edgeName == null) {
                return null;
            }

            nameExists = false;
            for (Edge existingEdge : graph.getEdges()) {
                if (existingEdge.getName().equals(edgeName)) {
                    nameExists = true;
                    JOptionPane.showMessageDialog(mainFrame, "This edge name already exists. Please pick" +
                            " a new one.");
                    break;
                }
            }
        } while (nameExists || edgeName.isEmpty());

        return edgeName;
    }

    private Node getValidNodeByName(Graph graph, MainFrame mainFrame) {
        String nodeName2;
        Node node2;

        while (true) {
            nodeName2 = JOptionPane.showInputDialog(mainFrame, "Enter the name of the node you want " +
                    "it to be attached to:");
            if (nodeName2 == null) {
                return null;
            }
            node2 = graph.getNodeByName(nodeName2);
            if (node2 != null) {
                break;
            }
            JOptionPane.showMessageDialog(mainFrame, "Node not found. Please enter a valid node name.");
        }

        return node2;
    }

    /**
     * This function removes the selected Edge.
     *
     * @param graph the graph in which we have the node
     */
    public void removeEdge(Graph graph) {
        Edge selectedEdge = graph.getSelectedEdge();
        if (selectedEdge != null) {
            graph.removeEdge(selectedEdge);
            graph.notifyObservers();
        }
    }

    /**
     * Renames an edge.
     *
     * @param graph     The graph containing the edge.
     * @param mainFrame The frame where the renaming happens.
     */
    public void renameEdge(Graph graph, MainFrame mainFrame) {
        String newEdgeName;
        Edge selectedEdge = graph.getSelectedEdge();
        if (selectedEdge != null) {
            newEdgeName = JOptionPane.showInputDialog(mainFrame, "Enter the new name for the edge:");
            selectedEdge.setName(newEdgeName);
            graph.notifyObservers();
        }
    }
}
