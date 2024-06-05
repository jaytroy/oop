package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.*;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.Panel;

/**
 * This is the class that handles what every button does.
 */
public class ButtonActions {
    private NodeActions nodeActions = new NodeActions();
    private EdgeActions edgeActions = new EdgeActions();
    private ArmyActions armyActions = new ArmyActions();
    private EventActions eventActions = new EventActions();

    public void addNode(Graph graph, Panel panel, MainFrame mainFrame) {
        nodeActions.addAction(graph, panel, mainFrame);
    }

    public void removeNode(Graph graph, Panel panel) {
        nodeActions.removeAction(graph, panel);
    }

    public void addEdge(Graph graph, Panel panel, MainFrame mainFrame) {
        edgeActions.addEdge(graph, panel, mainFrame);
    }

    public void removeEdge(Graph graph, Panel panel) {
        edgeActions.removeEdge(graph, panel);
    }

    public void addArmyToSelectedNode(Graph graph, Panel panel, MainFrame mainFrame) {
        armyActions.addArmyToSelectedNode(graph, panel, mainFrame);
    }

    public void removeArmyFromSelectedNode(Graph graph, Panel panel, MainFrame mainFrame) {
        armyActions.removeArmyFromSelectedNode(graph, panel, mainFrame);
    }

    public void addEventToSelectedElement(Graph graph, Panel panel, MainFrame mainFrame) {
        eventActions.addEventToSelectedElement(graph, panel, mainFrame);
    }

    public void removeEventFromSelectedElement(Graph graph, Panel graphPanel, MainFrame mainFrame) {
        eventActions.removeEventFromSelectedElement(graph, graphPanel, mainFrame);
    }

    /**
     * starts the simulation for one step.
     *
     * @param graph the graph the simulation happens in.
     * @param panel the panel the simulation is going to be portrayed on.
     */
    public void simulation1Step(Graph graph, Panel panel) {
        Simulation sim = new Simulation();
        sim.simulateSingleStep(graph);
        panel.repaint();
    }

}
