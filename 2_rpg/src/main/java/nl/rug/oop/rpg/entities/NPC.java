package nl.rug.oop.rpg.entities;

import lombok.Getter;
import nl.rug.oop.rpg.Inspectable;
import nl.rug.oop.rpg.Interactable;

import java.util.Scanner;

public class NPC implements Inspectable, Interactable {
    @Getter
    private String description;

    public NPC(String description) {
        this.description = description;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        System.out.println("You interact with " + description);
    }
}
