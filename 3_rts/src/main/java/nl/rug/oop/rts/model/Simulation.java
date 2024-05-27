package nl.rug.oop.rts.model;

//import nl.rug.oop.rts.util.model.events.RandomEvent;

import nl.rug.oop.rts.model.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that handles the simulations.
 */
public class Simulation {
    private static final double CHANCE = 0.25;
    private static final Random random = new Random();
    /**
     * Initialising the fight.
     * @param graph graph where it happens
     */
    public void firstFight(Graph graph) {
        List<Node> nodes = graph.getNodes();
        for (Node node : nodes) {
            node.setArmies(Battle.battleStart(node.getArmies()));
        }
        System.out.println("first fight complete");
    }

    /**
      * Simulation of  a single step in the simulation.
     * @param graph graph where it happens
     */
    public void simulateSingleStep(Graph graph) {
        List<Node> nodes = new ArrayList<>(graph.getNodes());
        List<Edge> edges = new ArrayList<>(graph.getEdges());

        for (Node node : nodes) {
            List<Edge> nodeEdges = new ArrayList<>(node.getEdges());
            List<Army> armies = new ArrayList<>(node.getArmies());
            for (Army army : armies) {
                if (army != null && nodeEdges.size() > 0) {
                    Edge targetEdge = graph.getRandomEdge(nodeEdges);
                    army.setComingFrom(node);
                    node.removeArmy(army);
                    targetEdge.addArmy(army);
                }
            }
        }
        System.out.println("Armies moved to a random edge");

        eventsOnEdges(edges);
        System.out.println("Events were added to the Nodes and applied to the armies");

        for (Edge edge : edges) {
            List<Army> armies = new ArrayList<>(edge.getArmies());
            for (Army army : armies) {
                if (army != null) {
                    Node targetNode = edge.getOtherNode(army.getComingFrom());
                    edge.removeArmy(army);
                    targetNode.addArmy(army);
                }
            }
        }
        System.out.println("Armies moved back to Nodes");

        eventsOnNodes(nodes);
        System.out.println("Events were added to the Nodes and applied to the armies");

        System.out.println("Simulation complete");
    }


    private static void eventsOnEdges(List<Edge> edges) {
        for (Edge edge : edges) {
            if (edge.getEvents() != null && random.nextDouble() < CHANCE) {
                for(Event event : edge.getEvents()) {
                    for (Army army : new ArrayList<>(edge.getArmies())) {
                        event.startEvent(army);
                    }
                }
            }
            edge.setArmies(Battle.battleStart(edge.getArmies()));
        }
    }

    private static void eventsOnNodes(List<Node> nodes) {
        for (Node node : nodes) {
            if (node.getEvent() != null && random.nextDouble() < CHANCE) {
                for(Event event : node.getEvents()) {
                    for (Army army : new ArrayList<>(node.getArmies())) {
                        event.startEvent(army);
                    }
                }
            }
            node.setArmies(Battle.battleStart(node.getArmies()));
        }
    }
}
