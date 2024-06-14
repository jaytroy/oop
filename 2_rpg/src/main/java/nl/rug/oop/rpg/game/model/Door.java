package nl.rug.oop.rpg.game.model;

import lombok.Getter;
import nl.rug.oop.rpg.game.Inspectable;
import nl.rug.oop.rpg.game.Interactable;
import nl.rug.oop.rpg.game.entities.Player;

import java.io.Serializable;

/**
 * Defines a door.
 */
public abstract class Door implements Inspectable, Interactable, Serializable {
    @Getter
    private final String description;
    private final Room room1;
    private static final long serialVersionUID = 42L;

    /**
     * Room2.
     */
    protected final Room room2;

    /**
     * The constructor for a door.
     *
     * @param description The description of the door.
     * @param from        Where the door leads from.
     * @param to          Where the door leads to.
     */
    public Door(String description, Room from, Room to) {
        this.description = description;
        this.room1 = from;
        this.room2 = to;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        if (player.getCurrentRoom() == room1) {
            player.setCurrentRoom(room2);
        } else {
            player.setCurrentRoom(room1);
        }
        System.out.println("You go through the door");
    }
}
