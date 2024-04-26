package nl.rug.oop.rpg.game.entities.npc;

import lombok.Getter;
import nl.rug.oop.rpg.game.Inspectable;
import nl.rug.oop.rpg.game.Interactable;
import nl.rug.oop.rpg.game.entities.Player;

public abstract class NPC implements Inspectable, Interactable {
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
        System.out.println("You interact with " + description + ". They don't do much.");
    }
}
