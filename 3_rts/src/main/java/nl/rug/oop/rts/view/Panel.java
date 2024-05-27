package nl.rug.oop.rts.view;
import nl.rug.oop.rts.controller.GraphObserver;
import nl.rug.oop.rts.controller.NodeSelectionerListener;
import nl.rug.oop.rts.model.*;
import nl.rug.oop.rts.model.events.Event;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This handles our panels.
 */
public class Panel extends JPanel implements GraphObserver {
    private Graph graph;
    private JPanel nodeMenuPanel;
    private JPanel edgeMenuPanel;
    private JLabel selectedNodeLabel;
    private JLabel selectedEdgeLabel;
    private JTextArea informationJTextArea;

    /**
     * This is the constructor of the panel where we define everything that is going to be inside this panel.
     *
     * @param graph graph it is in
     */
    public Panel(Graph graph) {
        this.graph = graph;
        setBackground(Color.RED);
        setLayout(null);

        NodeSelectionerListener listener = new NodeSelectionerListener(graph, this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setFocusable(true);
        requestFocus();

        createNodeMenu();
        createEdgeMenu();
        createInformationPanel();
        updateMenus();
    }

    private void createNodeMenu() {
        nodeMenuPanel = new JPanel();
        nodeMenuPanel.setBounds(10, 10, 300, 30);
        nodeMenuPanel.setBackground(Color.WHITE);
        nodeMenuPanel.setLayout(new BorderLayout());

        selectedNodeLabel = new JLabel("Selected Node: None");
        selectedNodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedNodeLabel.setForeground(Color.BLACK);

        nodeMenuPanel.add(selectedNodeLabel, BorderLayout.CENTER);
        add(nodeMenuPanel);
    }

    private void createEdgeMenu() {
        edgeMenuPanel = new JPanel();
        edgeMenuPanel.setBounds(10, 50, 300, 30);
        edgeMenuPanel.setBackground(Color.WHITE);

        selectedEdgeLabel = new JLabel("Selected Edge: None");
        selectedEdgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedEdgeLabel.setForeground(Color.BLACK);

        edgeMenuPanel.add(selectedEdgeLabel, BorderLayout.CENTER);
        add(edgeMenuPanel);
    }

    private void createInformationPanel() {
        JPanel informationPanel = new JPanel(new BorderLayout());
        informationPanel.setBackground(Color.WHITE);

        informationJTextArea = new JTextArea();
        informationJTextArea.setEditable(false);
        informationJTextArea.setLineWrap(true);
        informationJTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(informationJTextArea);
        scrollPane.setPreferredSize(new Dimension(200, 120)); // Adjust the dimensions as needed

        informationPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel parentPanel = new JPanel(new BorderLayout());
        parentPanel.setBounds(320, 10, 270, 100);
        parentPanel.setBackground(Color.WHITE);
        parentPanel.add(informationPanel, BorderLayout.CENTER);

        add(parentPanel);
    }

    /**
     * This updates 2 labels for nodes and edges that show us which ones are selected at the moment.
     */
    public void updateMenus() {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            selectedNodeLabel.setText("Selected Node: " + selectedNode.getName());

            StringBuilder armiesInfo = new StringBuilder();
            StringBuilder eventsInfo = new StringBuilder();
            List<Army> armies = selectedNode.getArmies();
            for (Army army : armies) {
                armiesInfo.append("Faction: ").append(army.getFaction()).append(", ");
                armiesInfo.append("Units: ").append(army.getUnits().size()).append("\n");
            }

            if (armiesInfo.length() > 0) {
                armiesInfo.setLength(armiesInfo.length() - 1); // Remove the last newline character
            } else {
                armiesInfo.append("No armies");
            }

            List<Event> events = selectedNode.getEvents();
            for (Event event : events) {
                eventsInfo.append("Events: ").append(event.getDescription()).append(", ");
            }

            if (eventsInfo.length() > 0) {
                eventsInfo.setLength(eventsInfo.length() - 1); // Remove the last newline character
            } else {
                armiesInfo.append("  No events.");
            }

            informationJTextArea.setText(armiesInfo.toString() + eventsInfo.toString());
        } else {
            selectedNodeLabel.setText("Selected Node: None");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Edge edge : graph.getEdges()) {
            paintEdgesSelected((Graphics2D) g, edge, g2d);
        }

        for (Node node : graph.getNodes()) {
            int nodeSize = 70;
            int nameOffset = 10;
            int x = node.getX() - nodeSize / 2;
            int y = node.getY() - nodeSize / 2;

            paintNodesWithArmies((Graphics2D) g, node, x, y, nodeSize);
            paintNodesSelected((Graphics2D) g, node, x, y, nodeSize);

            g.setColor(Color.BLACK);
            g.drawString(node.getName(), node.getX() - g.getFontMetrics().stringWidth(node.getName()) / 2,
                    node.getY() + nodeSize / 2 + nameOffset);
        }
    }

    /**
     * This handles painting the nodes that have armies.
     * @param g the drawing
     * @param node node where the army is
     * @param x x-axis coordinates
     * @param y y-axis coordinates
     * @param nodeSize size of the node
     */
    public void paintNodesWithArmies(Graphics2D g, Node node, int x, int y, int nodeSize) {
        // Draw the node icon
        g.setColor(Color.BLUE); // Set the default color to blue
        g.fillOval(x, y, nodeSize, nodeSize);

        // Draw army indicators if there are any
        if (node.isHasArmy()) {
            int armySize = 10; // Adjust the army size here
            int armyOffset = 5; // Adjust the army offset here
            List<Army> armies = node.getArmies();
            int armyX = x + nodeSize / 2 - (armySize * armies.size() + armyOffset * (armies.size() - 1)) / 2;
            int armyY = y + nodeSize / 2 - armySize / 2;

            for (Army army : armies) {
                Color factionColor = getFactionColor(army.getFaction());
                g.setColor(factionColor);
                g.fillRect(armyX, armyY, armySize, armySize);
                armyX += armySize + armyOffset;
            }
        }
    }





    /**
     * This handles painting the node that is selected.
     * @param g the drawing
     * @param node node that is selected
     * @param x x-axis coordinates
     * @param y y-axis coordinates
     * @param nodeSize size of the node
     */
    public void paintNodesSelected(Graphics2D g, Node node, int x, int y, int nodeSize) {
        if (node.isSelected()) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, nodeSize, nodeSize);
        }
    }

    /**
     * This handles painting the selected edge.
     * @param g drawing
     * @param edge the selected edge
     * @param g2d make the line thicker for edge
     */
    public void paintEdgesSelected(Graphics2D g, Edge edge, Graphics2D g2d){
        if (edge.isSelected()) {
            g.setColor(Color.YELLOW);
            g2d.setStroke(new BasicStroke(3)); // Set thicker line for selected edge
            g.drawLine(edge.getNode1().getX(), edge.getNode1().getY(), edge.getNode2().getX(),
                    edge.getNode2().getY());
        } else {
            g.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1)); // Set default line thickness
            g.drawLine(edge.getNode1().getX(), edge.getNode1().getY(), edge.getNode2().getX(),
                    edge.getNode2().getY());
        }
    }

    private Color getFactionColor(Faction faction) {
        switch (faction) {
            case MEN:
                return Color.WHITE;
            case ELVES:
                return Color.GREEN;
            case DWARVES:
                return Color.GRAY;
            case MORDOR:
                return Color.MAGENTA;
            default:
                return Color.PINK;
        }
    }

    @Override
    public void update(Graph graph) {
        this.graph = graph;

        boolean nodeSelected = false;
        boolean edgeSelected = false;

        for (Node node : graph.getNodes()) {
            if (node.isSelected()) {
                nodeSelected = true;
                break;
            }
        }

        for (Edge edge : graph.getEdges()) {
            if (edge.isSelected()) {
                edgeSelected = true;
                break;
            }
        }

        if (!nodeSelected) {
            selectedNodeLabel.setText("Selected Node: None");
        }

        if (!edgeSelected) {
            selectedEdgeLabel.setText("Selected Edge: None");
        }
        repaint();
        updateMenus();
    }
}
