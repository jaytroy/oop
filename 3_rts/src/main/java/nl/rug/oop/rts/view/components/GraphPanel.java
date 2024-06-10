package nl.rug.oop.rts.view.components;

import nl.rug.oop.rts.controller.MouseSelector;
import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Faction;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.view.components.menus.Indicators.CurrentEdgeIndicator;
import nl.rug.oop.rts.view.components.menus.Indicators.CurrentNodeIndicator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class handles the panel of the game.
 */
public class GraphPanel extends JPanel implements GraphObserver {
    private Graph graph;
    private CurrentNodeIndicator nodeMenu;
    private CurrentEdgeIndicator edgeMenu;
    private JTextArea informationJTextArea;

    /**
     * This is the constructor of the panel where we define everything that is going to be inside this panel.
     *
     * @param graph graph it is in
     */
    public GraphPanel(Graph graph) {
        this.graph = graph;
        setBackground(Color.RED);
        setLayout(null);

        MouseSelector listener = new MouseSelector(graph, this);
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
        nodeMenu = new CurrentNodeIndicator();
        add(nodeMenu);
    }

    private void createEdgeMenu() {
        edgeMenu = new CurrentEdgeIndicator();
        add(edgeMenu);
    }

    private void createInformationPanel() {
        JPanel informationPanel = new JPanel(new BorderLayout());
        informationPanel.setBackground(Color.WHITE);

        informationJTextArea = new JTextArea();
        informationJTextArea.setEditable(false);
        informationJTextArea.setLineWrap(true);
        informationJTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(informationJTextArea);
        scrollPane.setPreferredSize(new Dimension(200, 120));

        informationPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel parentPanel = new JPanel(new BorderLayout());
        parentPanel.setBounds(320, 10, 270, 100);
        parentPanel.setBackground(Color.WHITE);
        parentPanel.add(informationPanel, BorderLayout.CENTER);

        add(parentPanel);
    }

    /**
     * This updates two labels for nodes and edges that show us which ones are selected at the moment.
     */
    public void updateMenus() {
        Node selectedNode = graph.getSelectedNode();
        Edge selectedEdge = graph.getSelectedEdge();
        if (selectedNode != null) {
            nodeMenu.updateSelectedNodeLabel("Selected Node: " + selectedNode.getName());

            StringBuilder infoBuilder = new StringBuilder();
            List<Army> armies = selectedNode.getArmies();
            if (!armies.isEmpty()) {
                infoBuilder.append("Armies:\n");
                for (Army army : armies) {
                    infoBuilder.append("- Faction: ").append(army.getFaction()).append(", Units: ").append(army.getUnits().size()).append("\n");
                }
            } else {
                infoBuilder.append("No armies\n");
            }

            List<Event> events = selectedNode.getEvents();
            if (!events.isEmpty()) {
                infoBuilder.append("\nEvents:\n");
                for (Event event : events) {
                    infoBuilder.append("- ").append(event.getDescription()).append("\n");
                }
            } else {
                infoBuilder.append("\nNo events.\n");
            }

            informationJTextArea.setText(infoBuilder.toString());
        } else if (selectedEdge != null) {
            edgeMenu.updateSelectedEdgeLabel("Selected Edge: " + selectedEdge.getName());

            StringBuilder infoBuilder = new StringBuilder();
            List<Event> events = selectedEdge.getEvents();
            if (!events.isEmpty()) {
                infoBuilder.append("Events:\n");
                for (Event event : events) {
                    infoBuilder.append("- ").append(event.getDescription()).append("\n");
                }
            } else {
                infoBuilder.append("No events.\n");
            }

            informationJTextArea.setText(infoBuilder.toString());
        } else {
            nodeMenu.updateSelectedNodeLabel("Selected Node: None");
            edgeMenu.updateSelectedEdgeLabel("Selected Edge: None");
            informationJTextArea.setText("");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Edge edge : graph.getEdges()) {
            paintEdgesSelected((Graphics2D) g, edge, g2d);

            int midX = (edge.getNode1().getX() + edge.getNode2().getX()) / 2;
            int midY = (edge.getNode1().getY() + edge.getNode2().getY()) / 2;

            g.setColor(Color.BLACK);
            g.drawString(edge.getName(), midX, midY);
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
     *
     * @param g        the drawing
     * @param node     node where the army is
     * @param x        x-axis coordinates
     * @param y        y-axis coordinates
     * @param nodeSize size of the node
     */
    public void paintNodesWithArmies(Graphics2D g, Node node, int x, int y, int nodeSize) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, nodeSize, nodeSize);

        if (node.isHasArmy()) {
            int armySize = 10;
            int armyOffset = 5;
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
     *
     * @param g        the drawing
     * @param node     node that is selected
     * @param x        x-axis coordinates
     * @param y        y-axis coordinates
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
     *
     * @param g    drawing
     * @param edge the selected edge
     * @param g2d  make the line thicker for edge
     */
    public void paintEdgesSelected(Graphics2D g, Edge edge, Graphics2D g2d) {
        if (edge.isSelected()) {
            g.setColor(Color.YELLOW);
            g2d.setStroke(new BasicStroke(3));
            g.drawLine(edge.getNode1().getX(), edge.getNode1().getY(), edge.getNode2().getX(),
                    edge.getNode2().getY());

            int midX = (edge.getNode1().getX() + edge.getNode2().getX()) / 2;
            int midY = (edge.getNode1().getY() + edge.getNode2().getY()) / 2;

            g.setColor(Color.BLACK);
            g.drawString(edge.getName(), midX, midY);
        } else {
            g.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));
            g.drawLine(edge.getNode1().getX(), edge.getNode1().getY(), edge.getNode2().getX(),
                    edge.getNode2().getY());
        }
    }


    private Color getFactionColor(Faction faction) {
        return switch (faction) {
            case MEN -> Color.WHITE;
            case ELVES -> Color.GREEN;
            case DWARVES -> Color.GRAY;
            case MORDOR -> Color.MAGENTA;
            default -> Color.PINK;
        };
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
            nodeMenu.updateSelectedNodeLabel("Selected Node: None");
        }

        if (!edgeSelected) {
            edgeMenu.updateSelectedEdgeLabel("Selected Edge: None");
        }
        repaint();
        updateMenus();
    }
}
