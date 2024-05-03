package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class GraphPanel extends JPanel {
    private List<Node> nodes;
    private List<Edge> edges;
    private int[] nodeXPositions;
    private int[] nodeYPositions;

    public GraphPanel(List<Node> nodes, List<Edge> edges, int[] nodeXPositions, int[] nodeYPositions) {
        this.nodes = nodes;
        this.edges = edges;
        this.nodeXPositions = nodeXPositions;
        this.nodeYPositions = nodeYPositions;
        setBackground(Color.WHITE);
    }

    // Override paintComponent method to draw the graph
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw nodes
        drawNodes(g);

        // Draw edges
        drawEdges(g);
    }

    // Method to draw nodes
    private void drawNodes(Graphics g) {
        // Draw each node at the specified position
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            int x = nodeXPositions[i];
            int y = nodeYPositions[i];

            // Draw node as a rectangle
            g.setColor(Color.BLUE); // Set node color
            g.fillRect(x - 15, y - 15, 30, 30); // Draw rectangle centered at (x, y) with width and height of 30

            // Draw node name
            g.setColor(Color.BLACK); // Set text color
            g.drawString(node.getName(), x - 15, y - 20); // Draw node name above the rectangle
        }
    }

    // Method to draw edges
    private void drawEdges(Graphics g) {
        // Draw each edge
        for (Edge edge : edges) {
            Node node1 = edge.getNode1();
            Node node2 = edge.getNode2();

            int x1 = nodeXPositions[nodes.indexOf(node1)];
            int y1 = nodeYPositions[nodes.indexOf(node1)];
            int x2 = nodeXPositions[nodes.indexOf(node2)];
            int y2 = nodeYPositions[nodes.indexOf(node2)];

            // Draw edge as a line
            g.setColor(Color.RED); // Set edge color
            g.drawLine(x1, y1, x2, y2); // Draw line connecting the two nodes
        }
    }
}
