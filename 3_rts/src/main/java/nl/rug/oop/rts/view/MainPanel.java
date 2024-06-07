package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.controller.actions.ButtonActions;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.view.components.GraphPanel;

import javax.swing.*;
import java.awt.*;

@Getter
public class MainPanel extends JPanel {
    private final Graph graph;
    private final GraphPanel graphPanel;
    private final ButtonActions buttonActions;
    private JTextField nodeTextField;
    private JTextField edgeTextField;
    private final MainFrame mainFrame;

    public MainPanel(Graph graph, MainFrame mainFrame) {
        this.graph = graph;
        this.mainFrame = mainFrame;
        this.graphPanel = new GraphPanel(graph);
        this.buttonActions = new ButtonActions();
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());

        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.add(graphPanel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        JButton editNodeButton = new JButton("Edit Node Name");
        JButton editEdgeButton = new JButton("Edit Edge Name");
        createTextFields(editNodeButton, editEdgeButton);

        optionsPanel.add(nodeTextField);
        optionsPanel.add(editNodeButton);
        optionsPanel.add(edgeTextField);
        optionsPanel.add(editEdgeButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainContentPanel, optionsPanel);
        splitPane.setDividerLocation(600);
        splitPane.setResizeWeight(1.0);

        add(splitPane, BorderLayout.CENTER);
    }

    private void createTextFields(JButton editNodeButton, JButton editEdgeButton) {
        nodeTextField = new JTextField();
        editNodeButton.addActionListener(e -> {
            String newName = nodeTextField.getText();
            var selectedNode = graph.getSelectedNode();
            if (selectedNode != null && !newName.isEmpty()) {
                selectedNode.setName(newName);
                graphPanel.repaint();
            }
        });

        edgeTextField = new JTextField();
        editEdgeButton.addActionListener(e -> {
            String newName = edgeTextField.getText();
            var selectedEdge = graph.getSelectedEdge();
            if (selectedEdge != null && !newName.isEmpty()) {
                selectedEdge.setName(newName);
                graphPanel.repaint();
            }
        });
    }
}
