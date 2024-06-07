package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.controller.actions.ButtonActions;

import javax.swing.*;

@Getter
public class MenuItems {
    private final MainFrame mainFrame;

    public MenuItems(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void createMenuItems(JMenu nodeMenu, JMenu edgeMenu, JMenu armyMenu, JMenu eventMenu, JMenu simulationMenu) {
        JMenuItem addNodeItem = new JMenuItem("Add Node");
        JMenuItem removeNodeItem = new JMenuItem("Remove Node");
        nodeMenu.add(addNodeItem);
        nodeMenu.add(removeNodeItem);

        JMenuItem addEdgeItem = new JMenuItem("Add Edge");
        JMenuItem removeEdgeItem = new JMenuItem("Remove Edge");
        edgeMenu.add(addEdgeItem);
        edgeMenu.add(removeEdgeItem);

        JMenuItem addArmyItem = new JMenuItem("Add Army");
        JMenuItem removeArmyItem = new JMenuItem("Remove Army");
        armyMenu.add(addArmyItem);
        armyMenu.add(removeArmyItem);

        JMenuItem addEventItem = new JMenuItem("Add Event");
        JMenuItem removeEventItem = new JMenuItem("Remove Event");
        eventMenu.add(addEventItem);
        eventMenu.add(removeEventItem);

        JMenuItem sim1Step = new JMenuItem("Simulate Single Time Step");
        simulationMenu.add(sim1Step);

        createActionListeners(addNodeItem, removeNodeItem, addEdgeItem, removeEdgeItem, addArmyItem, removeArmyItem, addEventItem, removeEventItem, sim1Step);
    }

    private void createActionListeners(JMenuItem addNodeItem, JMenuItem removeNodeItem, JMenuItem addEdgeItem, JMenuItem removeEdgeItem, JMenuItem addArmyItem, JMenuItem removeArmyItem, JMenuItem addEventItem, JMenuItem removeEventItem, JMenuItem sim1Step) {
        ButtonActions buttonActions = mainFrame.getMainPanel().getButtonActions();
        var mainPanel = mainFrame.getMainPanel();
        addNodeItem.addActionListener(e -> buttonActions.addNode(mainPanel.getGraph(), mainPanel.getGraphPanel(), mainFrame));
        removeNodeItem.addActionListener(e -> buttonActions.removeNode(mainPanel.getGraph(), mainPanel.getGraphPanel()));
        addEdgeItem.addActionListener(e -> buttonActions.addEdge(mainPanel.getGraph(), mainPanel.getGraphPanel(), mainFrame));
        removeEdgeItem.addActionListener(e -> buttonActions.removeEdge(mainPanel.getGraph(), mainPanel.getGraphPanel()));
        addArmyItem.addActionListener(e -> buttonActions.addArmyToSelectedNode(mainPanel.getGraph(), mainPanel.getGraphPanel(), mainFrame));
        removeArmyItem.addActionListener(e -> buttonActions.removeArmyFromSelectedNode(mainPanel.getGraph(), mainPanel.getGraphPanel(), mainFrame));
        addEventItem.addActionListener(e -> buttonActions.addEventToSelectedElement(mainPanel.getGraph(), mainPanel.getGraphPanel(), mainFrame));
        removeEventItem.addActionListener(e -> buttonActions.removeEventFromSelectedElement(mainPanel.getGraph(), mainPanel.getGraphPanel(), mainFrame));
        sim1Step.addActionListener(e -> buttonActions.simulation1Step(mainPanel.getGraph(), mainPanel.getGraphPanel()));
    }
}
