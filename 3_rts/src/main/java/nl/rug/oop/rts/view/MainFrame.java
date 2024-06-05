package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.controller.ButtonActions;
import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Faction;
import nl.rug.oop.rts.model.events.ReinforcementsEvent;

import javax.swing.*;
import java.awt.*;

/**
 * This handles our main frame for the simulation.
 */
public class MainFrame extends JFrame {
    @Getter
    private int nextArmyID;
    private JMenuItem addNodeItem;
    private JMenuItem removeNodeItem;
    private JMenuItem addEdgeItem;
    private JMenuItem removeEdgeItem;
    private JMenuItem addArmyItem;
    private JMenuItem removeArmyItem;

    private JMenuItem addEventItem;
    private JMenuItem removeEventItem;
    private JMenuItem sim1Step;

    private JButton addNodeButton;
    private JButton removeNodeButton;
    private JButton addEdgeButton;
    private JButton removeEdgeButton;
    private JButton addArmyButton;
    private JButton removeArmyButton;
    private JButton simulateSingleButton;
    private JButton addEventButton;
    private JButton removeEventButton;

    private Panel graphPanel;
    private Graph graph;
    private JTextField nodeTextField;
    private JTextField edgeTextField;

    private ButtonActions buttonActions = new ButtonActions();

    /**
     * This is our main frame, where we define the graph and panel and add everything (nodes, edges, armies etc.).
     */
    public MainFrame() {
        setTitle("Graph Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        graph = new Graph();
        Node node1 = new Node(1, "Node 1", 150, 200);
        Node node2 = new Node(2, "Node 2", 300, 300);
        Node node3 = new Node(3, "Node 3", 110, 400);
        node1.addArmy(new Army(1, Faction.MEN));
        node1.addEvent(new ReinforcementsEvent(1, "hey"));

        Edge edge1 = new Edge(1, "Edge 1", node1, node2);
        Edge edge2 = new Edge(2, "Edge 2", node2, node3);
        Edge edge3 = new Edge(3, "Edge 3", node1, node3);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);

        graphPanel = new Panel(graph);
        graphPanel.setFocusable(true);
        graphPanel.requestFocusInWindow();
        setContentPane(createMainPanel(graphPanel));
        createMenuBar();
    }

    /**
     * This is where we create the main panel.
     * @param graphPanel the panel of the graph
     * @return the main panel
     */
    private JPanel createMainPanel(Panel graphPanel) {
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

    /**
     * This handles the creation of the fields that help us edit the nodes and the edges.
     * @param editNodeButton button to confirm editing the node
     * @param editEdgeButton button to confirm editing the edge
     */
    public void createTextFields(JButton editNodeButton, JButton editEdgeButton) {
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

    /**
     * This creates the menu bar with all of our options of adding/removing or simulating.
     */
    public void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu nodeMenu = new JMenu("Node");
        JMenu edgeMenu = new JMenu("Edge");
        JMenu armyMenu = new JMenu("Army");
        JMenu eventMenu = new JMenu("Events");
        JMenu simulationMenu = new JMenu("Simulation");

        createMenuItems(nodeMenu, edgeMenu, armyMenu, eventMenu, simulationMenu);

        menuBar.add(nodeMenu);
        menuBar.add(edgeMenu);
        menuBar.add(armyMenu);
        menuBar.add(eventMenu);
        menuBar.add(simulationMenu);

        setJMenuBar(menuBar);

        createButtons();
        createActionListeners();

        nodeMenu.add(addNodeItem);
        nodeMenu.add(removeNodeItem);
        edgeMenu.add(addEdgeItem);
        edgeMenu.add(removeEdgeItem);
        armyMenu.add(addArmyItem);
        armyMenu.add(removeArmyItem);
        eventMenu.add(addEventButton);
        eventMenu.add(addEventItem);
        eventMenu.add(removeEventButton);
        eventMenu.add(removeEventItem);
    }


    /**
     * This creates the actual menu items to do the previous things.
     * @param nodeMenu menu handling actions on nodes
     * @param edgeMenu menu handling actions on edges
     * @param armyMenu menu handling actions on armies
     * @param eventMenu menu handling actions on events
     * @param simulationMenu menu handling actions on the simulation
     */
    public void createMenuItems(JMenu nodeMenu, JMenu edgeMenu, JMenu armyMenu, JMenu eventMenu, JMenu simulationMenu) {
        addNodeItem = new JMenuItem("Add Node");
        removeNodeItem = new JMenuItem("Remove Node");
        nodeMenu.add(addNodeItem);
        nodeMenu.add(removeNodeItem);

        addEdgeItem = new JMenuItem("Add Edge");
        removeEdgeItem = new JMenuItem("Remove Edge");
        edgeMenu.add(addEdgeItem);
        edgeMenu.add(removeEdgeItem);

        addArmyItem = new JMenuItem("Add Army");
        removeArmyItem = new JMenuItem("Remove Army");
        armyMenu.add(addArmyItem);
        armyMenu.add(removeArmyItem);

        addEventItem = new JMenuItem("Add Event");
        removeEventItem = new JMenuItem("Remove Event");
        eventMenu.add(addEventItem);
        eventMenu.add(removeEventItem);

        sim1Step = new JMenuItem("Simulate Single Time Step");
        simulationMenu.add(sim1Step);
    }

    /**
     * This creates the buttons to handle the actions needed.
     */
    public void createButtons() {
        addNodeButton = new JButton("Add Node");
        removeNodeButton = new JButton("Remove Node");
        addEdgeButton = new JButton("Add Edge");
        removeEdgeButton = new JButton("Remove Edge");
        addArmyButton = new JButton("Add Army");
        removeArmyButton = new JButton("Remove Army");
        addEventButton = new JButton("Add Event");
        removeEventButton = new JButton("Remove Event");
        simulateSingleButton = new JButton("Simulate Single Time Step");
    }

    /**
     * This function handles the actual behaviour of the buttons.
     */
    public void createActionListeners() {
        addNodeItem.addActionListener(e -> buttonActions.addNode(graph, graphPanel, this));
        removeNodeItem.addActionListener(e -> buttonActions.removeNode(graph, graphPanel));
        addEdgeItem.addActionListener(e -> buttonActions.addEdge(graph, graphPanel, this));
        removeEdgeItem.addActionListener(e -> buttonActions.removeEdge(graph, graphPanel));
        addArmyItem.addActionListener(e -> buttonActions.addArmyToSelectedNode(graph, graphPanel, this));
        removeArmyItem.addActionListener(e -> buttonActions.removeArmyFromSelectedNode(graph, graphPanel, this));
        addEventButton.addActionListener(e -> buttonActions.addEventToSelectedElement(graph, graphPanel, this));
        removeEventButton.addActionListener(e -> buttonActions.removeEventFromSelectedElement(graph, graphPanel, this));
        sim1Step.addActionListener(e -> buttonActions.simulation1Step(graph, graphPanel));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
