package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.Panel;

import javax.swing.*;

public class NodeActions implements Action {
    /**
     * This function handles adding a node. It shows an input dialog that let's you name your node, then creates one
     * at coordinates (200,200).
     *
     * @param graph     the graph in which we have the node
     * @param panel     the panel that we have to repaint
     * @param mainFrame the frame where this happens
     */
    public void addAction(Graph graph, Panel panel, MainFrame mainFrame) {
        String nodeName = JOptionPane.showInputDialog(mainFrame, "Enter a name for the node:");
        Node node = new Node(graph.getNextNodeId(), nodeName, 200, 200);
        graph.addNode(node);
        panel.repaint();
    }

    /**
     * This function removes the selected node.
     *
     * @param graph the graph in which we have the node
     * @param panel the panel that we have to repaint
     */
    public void removeAction(Graph graph, Panel panel) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            graph.removeNode(selectedNode);
            panel.repaint();
        }
    }
}
