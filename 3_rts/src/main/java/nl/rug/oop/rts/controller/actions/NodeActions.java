package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.components.GraphPanel;

import javax.swing.*;

public class NodeActions implements Action {
    /**
     * This function handles adding a node. It shows an input dialog that lets you name your node, then creates one
     * at coordinates (200,200).
     *
     * @param graph     the graph in which we have the node
     * @param mainFrame the frame where this happens
     */

    public void addAction(Graph graph, MainFrame mainFrame) {
        String nodeName;
        boolean nameExists;

        do {
            nodeName = JOptionPane.showInputDialog(mainFrame, "Enter a name for the node:");
            if (nodeName == null) {
                return;
            }

            nameExists = false;

            for (Node existingNode : graph.getNodes()) {
                if (existingNode.getName().equals(nodeName)) {
                    nameExists = true;
                    JOptionPane.showMessageDialog(mainFrame, "This name already exists. Please pick a new one.");
                    break;
                }
            }
        } while (nameExists);

        Node node = new Node(graph.getNextNodeId(), nodeName, 200, 200);
        graph.addNode(node);
        graph.notifyObservers();
    }
    /**
     * This function removes the selected node.
     *
     * @param graph the graph in which we have the node
     */
    public void removeAction(Graph graph) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            graph.removeNode(selectedNode);
            graph.notifyObservers();
        }
    }

    public void renameNode(Graph graph, MainFrame mainFrame) {
        String newNodeName;
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            newNodeName = JOptionPane.showInputDialog(mainFrame, "Enter the new name for the node:");
            selectedNode.setName(newNodeName);
            graph.notifyObservers();
        }
    }
}
