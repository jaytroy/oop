package nl.rug.oop.rpg.game.model;

/**
 * A regular door.
 */
public class NormalDoor extends Door {
    /**
     * The constructor for a regular door.
     * @param description The description of the door.
     * @param from Where the door leads from.
     * @param to Where the door leads to.
     */
    public NormalDoor(String description, Room from, Room to) {
        super(description, from, to);
    }
}
