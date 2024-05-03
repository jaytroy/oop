package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.view.GraphPanel;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphEditorGUI extends JFrame {
    private GraphPanel graphPanel;

    public GraphEditorGUI(Graph graph, int[] nodeXPositions, int[] nodeYPositions) {
        setTitle("Graph Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Retrieve nodes and edges from the Graph object
        List<Node> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();

        // Create GraphPanel with nodes, edges, and positions
        graphPanel = new GraphPanel(nodes, edges, nodeXPositions, nodeYPositions);
        add(graphPanel);

        setVisible(true);
    }
}
