package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the nodes we need.
 */
public class Node {
    @Getter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    private List<Edge> edges;
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private int y;
    @Getter
    @Setter
    private int width;
    @Getter
    @Setter
    private int height;
    @Setter
    private boolean selected;
    @Getter
    @Setter
    private List<Army> armies;
    @Getter
    @Setter
    private List<Event> events;
    @Getter
    @Setter
    private String textureName;


    /**
     * This is the constructor of the nodes.
     * @param id the id of the node
     * @param name the name of the node
     * @param x x-axis coordinates of the node
     * @param y y-axis coordinates of the node
     */
    public Node(int id, String name, int x, int y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<>();
        this.armies = new ArrayList<>();
        this.events = new ArrayList<>();
        this.selected = false;
        this.width = 40;
        this.height = 40;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    public void addArmy(Army army) {
        armies.add(army);
    }

    public void removeArmy(Army givenArmy) {
        armies.removeIf(army-> army.getId() == givenArmy.getId());
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event givenEvent) {
        events.removeIf(event-> event.getId() == givenEvent.getId());
    }


    public boolean contains(int x, int y) {
        return x >= this.x && x <= this.x + this.width &&
                y >= this.y && y <= this.y + this.height;
    }
    public boolean isHasArmy(){
        if(armies.isEmpty()) {
            return false;
        } else{
            return true;
        }

    }


    public boolean isSelected() {
        return selected;
    }

    public boolean isConnected(Node node) {
        for (Edge edge : edges) {
            if (edge.getNode1() == node || edge.getNode2() == node) {
                return true;
            }
        }
        return false;
    }
}
