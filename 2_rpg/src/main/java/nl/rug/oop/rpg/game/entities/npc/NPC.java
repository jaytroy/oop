package nl.rug.oop.rpg.game.entities.npc;

import lombok.Getter;
import nl.rug.oop.rpg.game.Inspectable;
import nl.rug.oop.rpg.game.Interactable;
import nl.rug.oop.rpg.game.entities.Player;

import java.io.Serializable;

/**
 * NPC abstract class outlining their basic expected functionality.
 */
public abstract class NPC implements Inspectable, Interactable, Serializable {
    @Getter
    private String description;
    private static final long serialVersionUID = 42L;

    public NPC(String description) {
        this.description = description;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        System.out.println("You interact with " + description + ". They don't do much.");
    }
}
