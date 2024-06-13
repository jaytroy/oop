package nl.rug.oop.rts.controller;

import lombok.extern.slf4j.Slf4j;
import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.view.components.GraphPanel;

import java.awt.event.*;

public class MouseSelector extends MouseAdapter {
    private Graph graph;
    private GraphPanel panel;
    private Node selectedNode;
    private Edge selectedEdge;
    private int initialX;
    private int initialY;
    private static final int NODE_CLICK_THRESHOLD = 50;
    private static final int EDGE_CLICK_THRESHOLD = 50;

    public MouseSelector(Graph graph, GraphPanel panel) {
        this.graph = graph;
        this.panel = panel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        selectedNode = null;
        selectedEdge = null;

        for (Node node : graph.getNodes()) {
            if (isPointNearNode(mouseX, mouseY, node)) {
                selectedNode = node;
                initialX = mouseX;
                initialY = mouseY;
                break;
            }
        }

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

        double distance = pointToSegmentDistance(x1, y1, x2, y2, x, y);

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

    //Sets the mouse position when a press is registered
    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (Node node : graph.getNodes()) {
            if (node.contains(mouseX, mouseY)) {
                selectedNode = node;
                initialX = mouseX;
                initialY = mouseY;
                break;
            }
        }
    }

    /**
     * Listens for the mouse being dragged on a node and adjusts its positions.
     * Nodes do not leave game frame bounds.
     *
     * @param e The mouse event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedNode != null) {
            //Get the mouse position
            int mouseX = e.getX();
            int mouseY = e.getY();

            //Get the change in position
            int dx = mouseX - initialX;
            int dy = mouseY - initialY;

            //Get the new position of the node
            int newX = selectedNode.getX() + dx;
            int newY = selectedNode.getY() + dy;

            //Get the size of the panel
            int panelWidth = panel.getWidth();
            int panelHeight = panel.getHeight();

            //Check if the new position is within the bounds of the panel
            if (newX - selectedNode.getWidth() / 2 >= 0 &&
                    newX + selectedNode.getWidth() / 2 <= panelWidth &&
                    newY - selectedNode.getHeight() / 2 >= 0 &&
                    newY + selectedNode.getHeight() / 2 <= panelHeight) {
                selectedNode.setX(newX);
                selectedNode.setY(newY);
            }

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

    private double pointToSegmentDistance(int x1, int y1, int x2, int y2, int px, int py) {
        double lineLengthSquared = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
        if (lineLengthSquared == 0) return Math.hypot(px - x1, py - y1);

        double t = ((px - x1) * (x2 - x1) + (py - y1) * (y2 - y1)) / lineLengthSquared;
        t = Math.max(0, Math.min(1, t));
        double projectionX = x1 + t * (x2 - x1);
        double projectionY = y1 + t * (y2 - y1);
        return Math.hypot(px - projectionX, py - projectionY);

    }
}
