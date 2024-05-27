package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.view.Panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NodeSelectionerListener extends MouseAdapter {
    private Graph graph;
    private Panel panel;
    private Node selectedNode;
    private Edge selectedEdge;
    private int initialX;
    private int initialY;
    private static final int NODE_CLICK_THRESHOLD = 10; // Define NODE_CLICK_THRESHOLD here
    private static final int EDGE_CLICK_THRESHOLD = 10;

    public NodeSelectionerListener(Graph graph, Panel panel) {
        this.graph = graph;
        this.panel = panel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        selectedNode = null;
        selectedEdge = null;

        // Check if the click is near any nodes
        for (Node node : graph.getNodes()) {
            if (isPointNearNode(mouseX, mouseY, node)) {
                selectedNode = node;
                break;
            }
        }

        // Check if the click is near any edges
        if (selectedNode == null) {
            for (Edge edge : graph.getEdges()) {
                if (isPointNearEdge(mouseX, mouseY, edge)) {
                    selectedEdge = edge;
                    break;
                }
            }
        }

        updateSelection();
        panel.repaint();
    }

    private boolean isPointNearNode(int x, int y, Node node) {
        int nodeX = node.getX();
        int nodeY = node.getY();
        double distance = Math.hypot(nodeX - x, nodeY - y);
        return distance <= NODE_CLICK_THRESHOLD;
    }

    private boolean isPointNearEdge(int x, int y, Edge edge) {
        int x1 = edge.getNode1().getX();
        int y1 = edge.getNode1().getY();
        int x2 = edge.getNode2().getX();
        int y2 = edge.getNode2().getY();

        double distance = Utils.pointToSegmentDistance(x1, y1, x2, y2, x, y);
        return distance <= EDGE_CLICK_THRESHOLD;
    }

    private void updateSelection() {
        for (Node node : graph.getNodes()) {
            node.setSelected(node == selectedNode);
        }

        for (Edge edge : graph.getEdges()) {
            edge.setSelected(edge == selectedEdge);
        }
        panel.updateMenus();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (Node node : graph.getNodes()) {
            if (node.isSelected() && node.contains(mouseX, mouseY)) {
                selectedNode = node;
                initialX = mouseX;
                initialY = mouseY;
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedNode != null) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            int dx = mouseX - initialX;
            int dy = mouseY - initialY;

            selectedNode.setX(selectedNode.getX() + dx);
            selectedNode.setY(selectedNode.getY() + dy);

            initialX = mouseX;
            initialY = mouseY;

            panel.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selectedNode = null;
        selectedEdge = null;
        panel.repaint();
    }
}
