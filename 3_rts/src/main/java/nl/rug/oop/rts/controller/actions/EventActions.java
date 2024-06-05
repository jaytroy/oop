package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.model.events.*;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.Panel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EventActions {
    private int nextEventID= 0;

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
}
