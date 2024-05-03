package nl.rug.oop.rts;

import main.java.nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Edge;

import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.view.GraphEditorGUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class of the application. Add more details here.
 */
    public class Main {
        public static void main(String[] args) {
            List<Node> nodes = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                nodes.add(new Node(i, "Node " + i));
            }
            edges.add(new Edge(0, "Edge 1", nodes.get(0), nodes.get(1)));
            edges.add(new Edge(1, "Edge 2", nodes.get(1), nodes.get(2)));
            edges.add(new Edge(2, "Edge 3", nodes.get(2), nodes.get(3)));
            edges.add(new Edge(3, "Edge 4", nodes.get(3), nodes.get(4)));
            edges.add(new Edge(4, "Edge 5", nodes.get(4), nodes.get(0)));

            int[] nodeXPositions = {50, 150, 250, 100, 200};
            int[] nodeYPositions = {50, 100, 150, 200, 250};

            Graph graph = new Graph(nodes, edges);
            GraphEditorGUI gui = new GraphEditorGUI(graph, nodeXPositions, nodeYPositions);
            gui.show();
        }
    }
    