package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.*;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.components.GraphPanel;

/**
 * This is the class that handles what every button does.
 */
public class ButtonActions {
    private final NodeActions nodeActions = new NodeActions();
    private final EdgeActions edgeActions = new EdgeActions();
    private final ArmyActions armyActions = new ArmyActions();
    private final EventActions eventActions = new EventActions();
    private final SaveActions saveActions = new SaveActions();

    public void addNode(Graph graph, GraphPanel panel, MainFrame mainFrame) {
        nodeActions.addAction(graph, panel, mainFrame);
    }

    public void removeNode(Graph graph, GraphPanel panel) {
        nodeActions.removeAction(graph, panel);
    }

    public void renameNode(Graph graph, GraphPanel panel, MainFrame mainFrame) {
        nodeActions.renameNode(graph, panel, mainFrame);
    }

    public void addEdge(Graph graph, GraphPanel panel, MainFrame mainFrame) {
        edgeActions.addEdge(graph, panel, mainFrame);
    }

    public void removeEdge(Graph graph, GraphPanel panel) {
        edgeActions.removeEdge(graph, panel);
    }

    public void renameEdge(Graph graph, GraphPanel panel, MainFrame mainFrame) {
        edgeActions.renameEdge(graph, panel, mainFrame);
    }

    public void addArmyToSelectedNode(Graph graph, GraphPanel panel, MainFrame mainFrame) {
        armyActions.addArmyToSelectedNode(graph, panel, mainFrame);
    }

    public void removeArmyFromSelectedNode(Graph graph, GraphPanel panel, MainFrame mainFrame) {
        armyActions.removeArmyFromSelectedNode(graph, panel, mainFrame);
    }

    public void addEventToSelectedElement(Graph graph, GraphPanel panel, MainFrame mainFrame) {
        eventActions.addEventToSelectedElement(graph, panel, mainFrame);
    }

    public void removeEventFromSelectedElement(Graph graph, GraphPanel graphPanel, MainFrame mainFrame) {
        eventActions.removeEventFromSelectedElement(graph, graphPanel, mainFrame);
    }

    public void saveGame(Graph graph) {
        saveActions.saveGame(graph);
    }

    /**
     * starts the simulation for one step.
     *
     * @param graph the graph the simulation happens in.
     * @param panel the panel the simulation is going to be portrayed on.
     */
    public void simulation1Step(Graph graph, GraphPanel panel) {
        Simulation sim = new Simulation();
        sim.simulateSingleStep(graph);
        panel.repaint();
    }
}
