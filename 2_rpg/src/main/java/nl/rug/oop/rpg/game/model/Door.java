package nl.rug.oop.rpg.game.model;

import nl.rug.oop.rpg.game.Inspectable;
import nl.rug.oop.rpg.game.Interactable;
import nl.rug.oop.rpg.game.entities.Player;

public abstract class Door implements Inspectable, Interactable {
    protected final String description;
    protected final Room room1;
    protected final Room room2;

    public Door(String description, Room from, Room to) {
        this.description = description;
        this.room1 = from;
        this.room2 = to;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    public void interact(Player player) {
        player.setCurrentRoom(room2);
        System.out.println("You go through the door");
    }

}
