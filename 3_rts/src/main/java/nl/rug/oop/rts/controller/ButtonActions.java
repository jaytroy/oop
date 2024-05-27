package nl.rug.oop.rts.util.controller;

import nl.rug.oop.rts.model.*;
import nl.rug.oop.rts.model.events.RandomEvent;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.Panel;

import javax.swing.*;

/**
 * This is the class that handles what every button does.
 */
public class ButtonActions {

    private int nextArmyID= 0;
    private int nextEventID= 0;

    /**
     * This function handles adding a node. It shows an input dialog that let's you name your node, then creates one
     * at coordinates (200,200).
     *
     * @param graph     the graph in which we have the node
     * @param panel     the panel that we have to repaint
     * @param mainFrame the frame where this happens
     */
    public void addNode(Graph graph, Panel panel, MainFrame mainFrame) {
        String nodeName = JOptionPane.showInputDialog(mainFrame, "Enter a name for the node:");
        Node node = new Node(graph.getNextNodeId(), nodeName, 200, 200);
        graph.addNode(node);
        panel.repaint();
    }

    /**
     * This function removes the selected node.
     *
     * @param graph the graph in which we have the node
     * @param panel the panel that we have to repaint
     */
    public void removeNode(Graph graph, Panel panel) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            graph.removeNode(selectedNode);
            panel.repaint();
        }
    }

    /**
     * This function handles adding an edge. You can only add an edge if you selected a node beforehand. If yes, then
     * an input dialog asks you to name the edge and then give it the node you want it to lead to.
     *
     * @param graph     the graph in which we have the node
     * @param panel     the panel that we have to repaint
     * @param mainFrame the frame where this happens
     */
    public void addEdge(Graph graph, Panel panel, MainFrame mainFrame) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            String edgeName = JOptionPane.showInputDialog(mainFrame, "Enter a name for the edge:");

            if (edgeName != null && !edgeName.isEmpty()) {
                String nodeName2 = JOptionPane.showInputDialog(mainFrame, "Enter the name of the node you want" +
                        " it to be attached to:");
                Node node2 = graph.getNodeByName(nodeName2);

                if (node2 != null) {
                    Edge edge = new Edge(graph.getNextEdgeId(), edgeName, selectedNode, node2);
                    graph.addEdge(edge);

                    panel.repaint();
                }
            }
        }
    }

    /**
     * This function removes the selected Edge.
     *
     * @param graph the graph in which we have the node
     * @param panel the panel that we have to repaint
     */
    public void removeEdge(Graph graph, Panel panel) {
        Edge selectedEdge = graph.getSelectedEdge();
        if (selectedEdge != null) {
            graph.removeEdge(selectedEdge);
            panel.repaint();
        }
    }

    /**
     * This function should handle adding an army to the selected node. It shows an input dialog that asks you to select
     * the faction of your army.
     *
     * @param graph     the graph in which we have the node
     * @param panel     the panel that we have to repaint
     * @param mainFrame the frame where this happens
     */
    public void addArmyToSelectedNode(Graph graph, Panel panel, MainFrame mainFrame) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            Faction[] factions = Faction.values();
            Faction selectedFaction = (Faction) JOptionPane.showInputDialog(
                    mainFrame,
                    "Select a faction:",
                    "Add Army",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    factions,
                    factions[0]
            );
            if (selectedFaction != null) {
                // Generate a random number of units between 10 and 50
                int units = (int) (Math.random() * 41) + 10;

                // Generate random unit names from the selected faction
                String[] unitNames = generateRandomUnitNames(selectedFaction, units);
                // Create and add the army to the selected node
                Army army = new Army(nextArmyID, selectedFaction);
                selectedNode.addArmy(army);
                nextArmyID++;
                // Repaint the panel
                panel.repaint();
            }
        }
    }

    /**
     * This function generates random unit names.
     *
     * @param faction the faction type
     * @param count   how many units
     * @return returns the list of the unit names
     */
    private String[] generateRandomUnitNames(Faction faction, int count) {
        String[] unitNames = new String[count];
        for (int i = 0; i < count; i++) {
            unitNames[i] = "Unit " + (i + 1);
        }
        return unitNames;
    }

//    public void removeArmyFromSelectedNode(Graph graph, Panel panel) {
//        Node selectedNode = graph.getSelectedNode();
//        if (selectedNode != null) {
//            Army selectedArmy = selectedNode.getSelectedArmy();
//            if (selectedArmy != null) {
//                selectedNode.removeArmy(selectedArmy);
//                panel.repaint();
//            }
//        }
//    }

    public void addEventToSelectedNode(Graph graph) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            selectedNode.addEvent(RandomEvent.startRandomEvent(nextEventID));
            nextEventID++;
            System.out.println("Event added to the selected node.");
        }
    }

    public void removeEventFromSelectedNode(Graph graph) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            selectedNode.getEvents().clear();
            JOptionPane.showMessageDialog(null, "All events have been removed from the node.");
        }
    }




    /**
     * starts the simulation for one step.
     * @param graph the graph the simulation happens in.
     * @param panel the panel the simulation is going to be portrayed on.
     */
    public void simulation1Step(Graph graph, Panel panel) {
        Simulation sim = new Simulation();
        sim.firstFight(graph);
        sim.simulateSingleStep(graph);
        panel.repaint();
    }

}
