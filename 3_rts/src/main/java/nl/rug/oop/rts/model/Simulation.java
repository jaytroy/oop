package nl.rug.oop.rts.model;

import nl.rug.oop.rts.model.base.Edge;
import nl.rug.oop.rts.model.base.GameElement;
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
    private final double CHANCE = 0.70;

    /**
     * Simulation of a single step in the simulation.
     *
     * @param graph graph where it happens
     */
    public void simulateSingleStep(Graph graph) {
        List<Node> nodes = new ArrayList<>(graph.getNodes());
        List<Edge> edges = new ArrayList<>(graph.getEdges());

        System.out.println("The simulation has started.");

        resolveBattlesOnNodes(nodes);
        moveArmiesToEdges(nodes, graph);
        resolveBattlesOnEdges(edges);
        applyEventsOnEdges(edges);
        moveArmiesBackToNodes(edges, graph);
        resolveBattlesOnNodes(nodes);
        applyEventsOnNodes(nodes);

        System.out.println("Simulation complete");
    }

    private void resolveBattlesOnNodes(List<Node> nodes) {
        for (Node node : nodes) {
            node.setArmies(Battle.battleStart(node.getArmies()));
        }
    }

    private void moveArmiesToEdges(List<Node> nodes, Graph graph) {
        Random random = new Random();
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
    }

    private void resolveBattlesOnEdges(List<Edge> edges) {
        for (Edge edge : edges) {
            edge.setArmies(Battle.battleStart(edge.getArmies()));
        }
    }

    private void applyEventsOnEdges(List<Edge> edges) {
        eventOnElement(edges);
    }

    private void moveArmiesBackToNodes(List<Edge> edges, Graph graph) {
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
    }

    private void applyEventsOnNodes(List<Node> nodes) {
        eventOnElement(nodes);
    }

    /**
     * Takes a list of GameElement or a list of one of its subclasses and applies events to them.
     *
     * @param elements The GameElements.
     */
    private void eventOnElement(List<? extends GameElement> elements) {
        Random random = new Random();
        for (GameElement element : elements) {
            List<Event> events = element.getEvents();
            if (events != null && !events.isEmpty() && random.nextDouble() < CHANCE) {
                Event randomEvent = events.get(random.nextInt(events.size()));
                if (randomEvent != null) {
                    for (Army army : element.getArmies()) {
                        if (army != null) {
                            randomEvent.startEvent(army);
                            System.out.println(randomEvent.getDescription() +
                                    " started for army: " + army.getFaction() +
                                    " " + army.getNumUnits());
                        }
                    }
                }
            }
        }
    }
}
