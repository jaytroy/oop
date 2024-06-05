package nl.rug.oop.rts.model.base;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the nodes we need.
 */
@Getter
@Setter
public class Node extends GameElement {
    private List<Edge> edges;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean selected;


    /**
     * This is the constructor of the nodes.
     * @param id the id of the node
     * @param name the name of the node
     * @param x x-axis coordinates of the node
     * @param y y-axis coordinates of the node
     */
    public Node(int id, String name, int x, int y) {
        super(id, name);
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<>();
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



    public boolean contains(int x, int y) {
        int halfWidth = 15 + this.width / 2;
        int halfHeight = 15 + this.height / 2;
        return x >= this.x - halfWidth && x <= this.x + halfWidth &&
                y >= this.y - halfHeight && y <= this.y + halfHeight;
    }

    public boolean isHasArmy(){
        return !super.getArmies().isEmpty();
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
