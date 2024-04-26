package nl.rug.oop.rpg.model;

import nl.rug.oop.rpg.Inspectable;
import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.entities.Player;

import java.util.Scanner;

public class Door implements Inspectable, Interactable {
    private final String description;
    private final Room room1;
    private final Room room2;

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
    }

}
