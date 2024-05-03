package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Edge;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawNodes(g);
        drawEdges(g);
    }


    private void drawNodes(Graphics g) {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            int x = nodeXPositions[i];
            int y = nodeYPositions[i];

            g.setColor(Color.BLUE);
            g.fillRect(x - 15, y - 15, 30, 30);
            g.setColor(Color.BLACK);
            g.drawString(node.getName(), x - 15, y - 20);
        }
    }

    private void drawEdges(Graphics g) {
        for (Edge edge : edges) {
            Node node1 = edge.getNode1();
            Node node2 = edge.getNode2();

            int x1 = nodeXPositions[nodes.indexOf(node1)];
            int y1 = nodeYPositions[nodes.indexOf(node1)];
            int x2 = nodeXPositions[nodes.indexOf(node2)];
            int y2 = nodeYPositions[nodes.indexOf(node2)];

            g.setColor(Color.RED);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
