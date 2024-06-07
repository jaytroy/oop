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
        ButtonActions buttonActions = mainFrame.getButtonActions();
        addNodeItem.addActionListener(e -> buttonActions.addNode(mainFrame.getGraph(), mainFrame.getGraphPanel(), mainFrame));
        removeNodeItem.addActionListener(e -> buttonActions.removeNode(mainFrame.getGraph(), mainFrame.getGraphPanel()));
        addEdgeItem.addActionListener(e -> buttonActions.addEdge(mainFrame.getGraph(), mainFrame.getGraphPanel(), mainFrame));
        removeEdgeItem.addActionListener(e -> buttonActions.removeEdge(mainFrame.getGraph(), mainFrame.getGraphPanel()));
        addArmyItem.addActionListener(e -> buttonActions.addArmyToSelectedNode(mainFrame.getGraph(), mainFrame.getGraphPanel(), mainFrame));
        removeArmyItem.addActionListener(e -> buttonActions.removeArmyFromSelectedNode(mainFrame.getGraph(), mainFrame.getGraphPanel(), mainFrame));
        addEventItem.addActionListener(e -> buttonActions.addEventToSelectedElement(mainFrame.getGraph(), mainFrame.getGraphPanel(), mainFrame));
        removeEventItem.addActionListener(e -> buttonActions.removeEventFromSelectedElement(mainFrame.getGraph(), mainFrame.getGraphPanel(), mainFrame));
        sim1Step.addActionListener(e -> buttonActions.simulation1Step(mainFrame.getGraph(), mainFrame.getGraphPanel()));
    }
}
