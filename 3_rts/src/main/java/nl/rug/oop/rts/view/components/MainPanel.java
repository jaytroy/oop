package nl.rug.oop.rts.view.components;

import lombok.Getter;
import nl.rug.oop.rts.controller.actions.ButtonActions;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.view.MainFrame;


import javax.swing.*;
import java.awt.*;

@Getter
public class MainPanel extends JPanel {
    private final Graph graph;
    private final GraphPanel graphPanel;
    private final ButtonActions buttonActions;
    private final MainFrame mainFrame;
    private final ConsoleTextArea consoleTextArea;

    public MainPanel(Graph graph, MainFrame mainFrame) {
        this.graph = graph;
        this.mainFrame = mainFrame;
        this.graphPanel = new GraphPanel(graph);
        this.buttonActions = new ButtonActions();
        this.consoleTextArea = new ConsoleTextArea(20, 10);
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());

        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.add(graphPanel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(consoleTextArea);
        optionsPanel.add(scrollPane);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainContentPanel, optionsPanel);
        splitPane.setDividerLocation(600);
        splitPane.setResizeWeight(1.0);

        add(splitPane, BorderLayout.CENTER);
    }
}
