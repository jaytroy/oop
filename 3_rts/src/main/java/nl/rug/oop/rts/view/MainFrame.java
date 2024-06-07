package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.controller.actions.ButtonActions;
import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Faction;
import nl.rug.oop.rts.model.events.ReinforcementsEvent;
import nl.rug.oop.rts.view.components.GraphPanel;

import javax.swing.*;
import java.awt.*;

@Getter
public class MainFrame extends JFrame {
    private final GraphPanel graphPanel;
    private final Graph graph;
    private final ButtonActions buttonActions = new ButtonActions();
    private JTextField nodeTextField;
    private JTextField edgeTextField;

    public MainFrame() {
        setTitle("Graph Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        graph = new Graph();

        initializeGraph();
        graphPanel = new GraphPanel(graph);
        graphPanel.setFocusable(true);
        graphPanel.requestFocusInWindow();

        setContentPane(createMainPanel(graphPanel));

        MainMenu mainMenu = new MainMenu(this);
        setJMenuBar(mainMenu.createMenuBar());
    }

    private void initializeGraph() {
        Node node1 = new Node(1, "Node 1", 150, 200);
        Node node2 = new Node(2, "Node 2", 300, 300);
        Node node3 = new Node(3, "Node 3", 110, 400);
        node1.addArmy(new Army(1, Faction.MEN));
        node1.addEvent(new ReinforcementsEvent(1));

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

    private JPanel createMainPanel(GraphPanel graphPanel) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(graphPanel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        JButton editNodeButton = new JButton("Edit Node Name");
        JButton editEdgeButton = new JButton("Edit Edge Name");
        createTextFields(editNodeButton, editEdgeButton);

        optionsPanel.add(nodeTextField);
        optionsPanel.add(editNodeButton);
        optionsPanel.add(edgeTextField);
        optionsPanel.add(editEdgeButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainPanel, optionsPanel);
        splitPane.setDividerLocation(600);
        splitPane.setResizeWeight(1.0);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(splitPane, BorderLayout.CENTER);
        return panel;
    }

    private void createTextFields(JButton editNodeButton, JButton editEdgeButton) {
        nodeTextField = new JTextField();
        editNodeButton.addActionListener(e -> {
            String newName = nodeTextField.getText();
            Node selectedNode = graph.getSelectedNode();
            if (selectedNode != null && !newName.isEmpty()) {
                selectedNode.setName(newName);
                graphPanel.repaint();
            }
        });

        edgeTextField = new JTextField();
        editEdgeButton.addActionListener(e -> {
            String newName = edgeTextField.getText();
            Edge selectedEdge = graph.getSelectedEdge();
            if (selectedEdge != null && !newName.isEmpty()) {
                selectedEdge.setName(newName);
                graphPanel.repaint();
            }
        });
    }
}
