package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.*;
import nl.rug.oop.rts.model.events.*;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.Panel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

                if (node2 != null && !selectedNode.isConnected(node2)) {
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
                int units = (int) (Math.random() * 41) + 10;

                String[] unitNames = generateRandomUnitNames(selectedFaction, units);
                Army army = new Army(nextArmyID, selectedFaction);
                selectedNode.addArmy(army);
                nextArmyID++;
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

    /**
     * This function handles the removal of an army from the selected node.
     *
     * @param graph     the graph in which we have the node
     * @param panel     the panel that we have to repaint
     * @param mainFrame the frame where this happens
     */
    public void removeArmyFromSelectedNode(Graph graph, Panel panel, MainFrame mainFrame) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null && selectedNode.isHasArmy()) {
            List<Army> armies = selectedNode.getArmies();
            List<String> armyOptions = new ArrayList<>();
            for (Army army : armies) {
                armyOptions.add(army.getFaction().toString() + " - Units: " + army.getUnits().size());
            }
            String[] armyOptionsArray = armyOptions.toArray(new String[0]);
            String selectedArmyString = (String) JOptionPane.showInputDialog(
                    mainFrame,
                    "Select an army to remove:",
                    "Remove Army",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    armyOptionsArray,
                    armyOptionsArray[0]
            );
            if (selectedArmyString != null) {
                String[] parts = selectedArmyString.split(" - Units: ");
                String factionString = parts[0];
                Faction faction = Faction.valueOf(factionString);
                int unitCount = Integer.parseInt(parts[1]);

                Army selectedArmy = null;
                for (Army army : armies) {
                    if (army.getFaction() == faction && army.getUnits().size() == unitCount) {
                        selectedArmy = army;
                        break;
                    }
                }

                if (selectedArmy != null) {
                    selectedNode.removeArmy(selectedArmy);
                    panel.repaint();
                }
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No armies to remove from the selected node.");
        }
    }

    public void addEventToSelectedElement(Graph graph, Panel panel, MainFrame mainFrame) {
        if (graph.getSelectedNode() != null) {
            Node selectedNode = graph.getSelectedNode();
                EventTypes[] eventTypes = EventTypes.values();
                EventTypes selectedEventType = (EventTypes) JOptionPane.showInputDialog(
                        mainFrame,
                        "Select an event type:",
                        "Add Event",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        eventTypes,
                        eventTypes[0]
                );
                if (selectedEventType != null) {
                    switch (selectedEventType) {
                        case HiddenWeaponryEvent:
                            selectedNode.addEvent(new HiddenWeaponryEvent(nextEventID, ""));
                            break;
                        case NaturalDisasterEvent:
                            selectedNode.addEvent(new NaturalDisasterEvent(nextEventID, ""));
                            break;
                        case RandomEvent:
                            selectedNode.addEvent(RandomEvent.getRandomEvent(nextEventID));
                            break;
                        case ReinforcementsEvent:
                            selectedNode.addEvent(new ReinforcementsEvent(nextEventID, ""));
                            break;
                        case TrainingMontage:
                            selectedNode.addEvent(new TrainingMontage(nextEventID, ""));
                            break;
                    }
                    nextEventID++;
                    panel.repaint();
                    System.out.println("Event added to the selected node.");
                }
        } else if(graph.getSelectedEdge() != null) {
            Edge selectedEdge = graph.getSelectedEdge();
                EventTypes[] eventTypes = EventTypes.values();
                EventTypes selectedEventType = (EventTypes) JOptionPane.showInputDialog(
                        mainFrame,
                        "Select an event type:",
                        "Add Event",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        eventTypes,
                        eventTypes[0]
                );
                if (selectedEventType != null) {
                    switch (selectedEventType) {
                        case HiddenWeaponryEvent:
                            selectedEdge.addEvent(new HiddenWeaponryEvent(nextEventID, ""));
                            break;
                        case NaturalDisasterEvent:
                            selectedEdge.addEvent(new NaturalDisasterEvent(nextEventID, ""));
                            break;
                        case RandomEvent:
                            selectedEdge.addEvent(RandomEvent.getRandomEvent(nextEventID));
                            break;
                        case ReinforcementsEvent:
                            selectedEdge.addEvent(new ReinforcementsEvent(nextEventID, ""));
                            break;
                        case TrainingMontage:
                            selectedEdge.addEvent(new TrainingMontage(nextEventID, ""));
                            break;
                    }
                    nextEventID++;
                    panel.repaint();
                    System.out.println("Event added to the selected edge.");
                }
        }
    }

    public void removeEventFromSelectedElement(Graph graph, Panel graphPanel, MainFrame mainFrame) {
        if (graph.getSelectedNode() != null) {
            Node selectedNode = graph.getSelectedNode();
            if (!selectedNode.getEvents().isEmpty()) {
                List<Event> events = selectedNode.getEvents();
                List<String> eventOptions = new ArrayList<>();
                for (Event event : events) {
                    eventOptions.add(event.toStringWithID());
                }
                String[] eventOptionsArray = eventOptions.toArray(new String[0]);
                String selectedEventString = (String) JOptionPane.showInputDialog(
                        mainFrame,
                        "Select an event to remove:",
                        "Remove Event",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        eventOptionsArray,
                        eventOptionsArray[0]
                );
                if (selectedEventString != null) {
                    Event selectedEvent = null;
                    for (Event event : events) {
                        if (event.toStringWithID().equals(selectedEventString)) {
                            selectedEvent = event;
                            break;
                        }
                    }

                    if (selectedEvent != null) {
                        selectedNode.removeEvent(selectedEvent);
                        graphPanel.repaint();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "No events to remove from the selected node.");
            }
        } else if (graph.getSelectedEdge() != null) {
            Edge selectedEdge = graph.getSelectedEdge();
            if (!selectedEdge.getEvents().isEmpty()) {
                List<Event> events = selectedEdge.getEvents();
                List<String> eventOptions = new ArrayList<>();
                for (Event event : events) {
                    eventOptions.add(event.toStringWithID());
                }
                String[] eventOptionsArray = eventOptions.toArray(new String[0]);
                String selectedEventString = (String) JOptionPane.showInputDialog(
                        mainFrame,
                        "Select an event to remove:",
                        "Remove Event",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        eventOptionsArray,
                        eventOptionsArray[0]
                );
                if (selectedEventString != null) {
                    Event selectedEvent = null;
                    for (Event event : events) {
                        if (event.toStringWithID().equals(selectedEventString)) {
                            selectedEvent = event;
                            break;
                        }
                    }

                    if (selectedEvent != null) {
                        selectedEdge.removeEvent(selectedEvent);
                        graphPanel.repaint();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "No events to remove from the selected edge.");
            }
        }
    }


    /**
     * starts the simulation for one step.
     * @param graph the graph the simulation happens in.
     * @param panel the panel the simulation is going to be portrayed on.
     */
    public void simulation1Step(Graph graph, Panel panel) {
        Simulation sim = new Simulation();
        sim.simulateSingleStep(graph);
        panel.repaint();
    }

}
