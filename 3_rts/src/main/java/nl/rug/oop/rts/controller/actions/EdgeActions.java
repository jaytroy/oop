package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.Panel;

import javax.swing.*;

public class EdgeActions {
    /**
     * This function handles adding an edge. You can only add an edge if you selected a node beforehand. If yes, then
     * an input dialog asks you to name the edge and then give it the node you want it to lead to.
     *
     * @param graph     the graph in which we have the node
     * @param panel     the panel that we have to repaint
     * @param mainFrame the frame where this happens
     */
    public void addEdge(Graph graph, Panel panel, MainFrame mainFrame) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            String edgeName = JOptionPane.showInputDialog(mainFrame, "Enter a name for the edge:");

            if (edgeName != null && !edgeName.isEmpty()) {
                String nodeName2 = JOptionPane.showInputDialog(mainFrame, "Enter the name of the node you want" +
                        " it to be attached to:");
                Node node2 = graph.getNodeByName(nodeName2);

                if (node2 != null && !selectedNode.isConnected(node2)) {
                    Edge edge = new Edge(graph.getNextEdgeId(), edgeName, selectedNode, node2);
                    graph.addEdge(edge);

                    panel.repaint();
                }
            }
        }
    }

    /**
     * This function removes the selected Edge.
     *
     * @param graph the graph in which we have the node
     * @param panel the panel that we have to repaint
     */
    public void removeEdge(Graph graph, Panel panel) {
        Edge selectedEdge = graph.getSelectedEdge();
        if (selectedEdge != null) {
            graph.removeEdge(selectedEdge);
            panel.repaint();
        }
    }
}
