package nl.rug.oop.rts.model;
import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Battle;
import nl.rug.oop.rts.model.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that handles the simulations.
 */
public class Simulation {
    private static final double CHANCE = 0.70;
    private static final Random random = new Random();

    /**
      * Simulation of  a single step in the simulation.
     * @param graph graph where it happens
     */
    public void simulateSingleStep(Graph graph) {
        List<Node> nodes = new ArrayList<>(graph.getNodes());
        List<Edge> edges = new ArrayList<>(graph.getEdges());

        // Phase 1: Resolve battles on nodes before moving armies to edges
        for (Node node : nodes) {
            node.setArmies(Battle.battleStart(node.getArmies()));
        }

        // Phase 2: Move armies to a random edge
        for (Node node : nodes) {
            List<Edge> nodeEdges = new ArrayList<>(node.getEdges());
            List<Army> armies = new ArrayList<>(node.getArmies());
            for (Army army : armies) {
                if (army != null && !nodeEdges.isEmpty()) {
                    Edge targetEdge = graph.getRandomEdge(nodeEdges);
                    army.setComingFrom(node);
                    node.removeArmy(army);
                    targetEdge.addArmy(army);
                }
            }
        }

        // Phase 3: Resolve battles on edges after moving armies
        for (Edge edge : edges) {
            edge.setArmies(Battle.battleStart(edge.getArmies()));
        }

        // Phase 4: Apply events to armies on edges
        eventsOnEdges(edges);

        // Phase 5: Move armies back to nodes
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

        // Phase 6: Resolve battles on nodes after moving armies back
        for (Node node : nodes) {
            node.setArmies(Battle.battleStart(node.getArmies()));
        }
        System.out.println("EVENTS START");
        // Phase 7: Apply events to armies on nodes
        eventsOnNodes(nodes);
        System.out.println("EVENTS END");


        System.out.println("Simulation complete");
    }



    private static void eventsOnEdges(List<Edge> edges) {
        Random random = new Random();
        for (Edge edge : edges) {
            if (edge.getEvents() != null && random.nextDouble() < CHANCE) {
                List<Event> events = edge.getEvents();
                System.out.println(events.size());
                Event randomEvent = events.get(random.nextInt(events.size()));
                if (randomEvent != null) {
                    for (Army army : edge.getArmies()) {
                        if (army != null) {
                            randomEvent.startEvent(army);
                            System.out.println("Event started for army: " + army);
                        }
                    }
                }
            }
        }
    }



    private static void eventsOnNodes(List<Node> nodes) {
        Random random = new Random();
        for (Node node : nodes) {
            if (node.getEvents() != null && random.nextDouble() < CHANCE) {
                List<Event> events = node.getEvents();
                Event randomEvent = events.get(random.nextInt(events.size()));
                if (randomEvent != null) {
                    for (Army army : new ArrayList<>(node.getArmies())) {
                        randomEvent.startEvent(army);
                        System.out.println("Event started for army: " + army);
                    }
                }
            }
        }
    }
}
