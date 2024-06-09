package nl.rug.oop.rts.model;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Simulation {
    private final double CHANCE = 0.70;

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
        eventOnElement(edges);

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
        log.info("EVENTS START");

        // Phase 7: Apply events to armies on nodes
        eventOnElement(nodes);
        log.info("EVENTS END");

        log.info("Simulation complete");
    }


    /**
     * Takes a list of GameElement or a list of one of its subclasses and applies events to them.
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
                            log.info("Event started for army: {}", army);
                        }
                    }
                }
            }
        }
    }
}
