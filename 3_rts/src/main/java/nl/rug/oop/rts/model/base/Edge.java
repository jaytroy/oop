package nl.rug.oop.rts.model.base;

import lombok.Getter;
import lombok.Setter;

/**
 * This class handles the Edges that we have.
 */
@Getter
public class Edge extends GameElement {
    private Node Node1, Node2;
    @Getter
    @Setter
    private boolean selected;

    /**
     * This is the constructor of the edge.
     *
     * @param id    the id of the edge
     * @param name  its name
     * @param Node1 the first node it is connected to
     * @param Node2 the node it leads to
     */
    public Edge(int id, String name, Node Node1, Node Node2) {
        super(id, name);
        this.Node1 = Node1;
        this.Node2 = Node2;
    }

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

    @Override
    protected String saveExtraFields() {
        StringBuilder json = new StringBuilder();

        json.append("\t\t\t\"Node1\": ").append(Node1.getId()).append(",\n");
        json.append("\t\t\t\"Node2\": ").append(Node2.getId()).append(",\n");

        return json.toString();
    }
}
