package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.*;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.view.MainFrame;

/**
 * This is the class that handles what every button does.
 */
public class ButtonActions {
    private final NodeActions nodeActions = new NodeActions();
    private final EdgeActions edgeActions = new EdgeActions();
    private final ArmyActions armyActions = new ArmyActions();
    private final EventActions eventActions = new EventActions();
    private final SaveActions saveActions = new SaveActions();

    public void addNode(Graph graph, MainFrame mainFrame) {
        nodeActions.addAction(graph, mainFrame);
    }

    public void removeNode(Graph graph) {
        nodeActions.removeAction(graph);
    }

    public void renameNode(Graph graph, MainFrame mainFrame) {
        nodeActions.renameNode(graph, mainFrame);
    }

    public void addEdge(Graph graph, MainFrame mainFrame) {
        edgeActions.addEdge(graph, mainFrame);
    }

    public void removeEdge(Graph graph) {
        edgeActions.removeEdge(graph);
    }

    public void renameEdge(Graph graph, MainFrame mainFrame) {
        edgeActions.renameEdge(graph, mainFrame);
    }

    public void addArmyToSelectedNode(Graph graph, MainFrame mainFrame) {
        armyActions.addArmyToSelectedNode(graph, mainFrame);
    }

    public void removeArmyFromSelectedNode(Graph graph, MainFrame mainFrame) {
        armyActions.removeArmyFromSelectedNode(graph, mainFrame);
    }

    public void addEventToSelectedElement(Graph graph, MainFrame mainFrame) {
        eventActions.addEventToSelectedElement(graph, mainFrame);
    }

    public void removeEventFromSelectedElement(Graph graph, MainFrame mainFrame) {
        eventActions.removeEventFromSelectedElement(graph, mainFrame);
    }

    public void saveGame(Graph graph) {
        saveActions.saveGame(graph);
    }

    /**
     * starts the simulation for one step.
     *
     * @param graph the graph the simulation happens in.
     */
    public void simulation1Step(Graph graph) {
        Simulation sim = new Simulation();
        sim.simulateSingleStep(graph);
        graph.notifyObservers();
    }
}
