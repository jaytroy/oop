package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Faction;
import nl.rug.oop.rts.model.events.ReinforcementsEvent;
import nl.rug.oop.rts.view.components.menus.NavBar;
import nl.rug.oop.rts.view.components.MainPanel;

import javax.swing.*;

/**
 * MainFrame is the main window of the application.
 * It contains the graph and the main panel.
 */
@Getter
public class MainFrame extends JFrame {
    private final Graph graph;
    private final MainPanel mainPanel;

    /**
     * Constructor for the MainFrame.
     */
    public MainFrame() {
        setTitle("Graph Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        graph = new Graph();

        initializeGraph();

        mainPanel = new MainPanel(graph, this);
        setContentPane(mainPanel);

        NavBar mainMenu = new NavBar(this);
        setJMenuBar(mainMenu.createMenuBar());
    }

    /**
     * Initializes the graph with some nodes and edges.
     */
    private void initializeGraph() {
        Node node1 = new Node(1, "Groningen", 150, 200);
        Node node2 = new Node(2, "Leeuwarden", 300, 300);
        Node node3 = new Node(3, "Amsterdam", 110, 400);
        node1.addArmy(new Army(1, Faction.MEN));
        node1.addEvent(new ReinforcementsEvent());

        Edge edge1 = new Edge(1, "Edge 1", node1, node2);
        Edge edge2 = new Edge(2, "Edge 2", node2, node3);
        Edge edge3 = new Edge(3, "Edge 3", node1, node3);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
    }
}
