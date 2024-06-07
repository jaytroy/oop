package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.GameElement;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.events.*;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.components.GraphPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EventActions {
    private int nextEventID = 0;
    private final EventFactory eventFactory = new EventFactory();

    public void addEventToSelectedElement(Graph graph, GraphPanel panel, MainFrame mainFrame) {
        boolean node = graph.getSelectedNode() != null;
        GameElement selectedElement = node ? graph.getSelectedNode() : graph.getSelectedEdge();

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
            Event newEvent = eventFactory.createEvent(selectedEventType, nextEventID);
            selectedElement.addEvent(newEvent);
            nextEventID++;
            panel.repaint();
            System.out.println("Event added to the selected node.");
        }
    }

    public void removeEventFromSelectedElement(Graph graph, GraphPanel graphPanel, MainFrame mainFrame) {
        boolean node = graph.getSelectedNode() != null;
        GameElement selectedElement = node ? graph.getSelectedNode() : graph.getSelectedEdge();

        if (!selectedElement.getEvents().isEmpty()) {
            List<Event> events = selectedElement.getEvents();
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
                    selectedElement.removeEvent(selectedEvent);
                    graphPanel.repaint();
                }
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No events to remove from the selected node.");
        }
    }
}
