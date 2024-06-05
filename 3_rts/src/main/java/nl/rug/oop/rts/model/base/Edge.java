package nl.rug.oop.rts.model.base;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.events.Event;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the Edges that we have.
 */
@Getter
public class Edge {
    private int id;
    @Setter
    private String name;
    private Node Node1, Node2;
    @Setter
    private boolean selected;
    @Setter
    private List<Army> armies;
    @Setter
    private List<Event> events;

    /**
     * This is the constructor of the edge.
     *
     * @param id    the id of the edge
     * @param name  its name
     * @param Node1 the first node it is connected to
     * @param Node2 the node it leads to
     */
    public Edge(int id, String name, Node Node1, Node Node2) {
        this.id = id;
        this.name = name;
        this.Node1 = Node1;
        this.Node2 = Node2;
        this.armies = new ArrayList<>();
        this.events = new ArrayList<>();    }

    /**
     * This function handles getting the node our given node is connected to.
     *
     * @param givenNode the node
     * @return what node it is connected to, if it is
     */
    public Node getOtherNode(Node givenNode) {
        if (givenNode.getId() == Node2.getId()) {
            return Node1;
        } else if (givenNode.getId() == Node1.getId()) {
            return Node2;
        } else {
            System.out.println("Node" + givenNode.getName() + "(Node id = " + givenNode.getId() + ") is not connected" +
                    " with any other Nodes");
            return null;
        }

    }

    public boolean isSelected() {
        return selected;
    }

    public boolean contains(int x, int y, int range) {
        Line2D line = new Line2D.Float(getNode1().getX(), getNode1().getY(), getNode2().getX(), getNode2().getY());
        return line.ptLineDist(x, y) <= range;
    }

    public void addArmy(Army army) {
        armies.add(army);
    }

    public void removeArmy(Army givenArmy) {
        armies.removeIf(army -> army.getId() == givenArmy.getId());
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event givenEvent) {
        events.removeIf(event -> event.getId() == givenEvent.getId());
    }
}
