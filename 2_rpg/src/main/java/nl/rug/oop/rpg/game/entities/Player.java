package nl.rug.oop.rpg.game.entities;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rpg.game.AliveAttributes;
import nl.rug.oop.rpg.game.model.Room;

import java.io.Serializable;

/**
 * The player class.
 */
@Getter
public class Player implements Combatable, Serializable {
    private final String name;
    @Getter
    private final String description;
    private AliveAttributes aliveAttributes;
    @Setter
    private Room currentRoom;

    /**
     * The constructor for the player class.
     * @param name The name of the player.
     * @param description The description of the player.
     * @param health The health of the player.
     * @param damage The damage the player can inflict.
     */
    public Player(String name, String description, int health, int damage) {
        this.name = name;
        this.description = description;
        this.aliveAttributes = new AliveAttributes(health, damage);
    }

    @Override
    public void takeDamage(int damage) {
        aliveAttributes.takeDamage(damage);
    }

    @Override
    public boolean attack(Combatable e) {
        System.out.println(e.getDescription() + " attacks you! They deal " +
                e.getAliveAttributes().getDamage() + " damage.");
        this.takeDamage(e.getAliveAttributes().getDamage());
        if (aliveAttributes.getHealth() <= 0) {
            System.out.println("You've been killed. Terribly sad.");
            return true;
        }
        System.out.println(this.getName() + " is at " + aliveAttributes.getHealth() + " health\n");
        return false;
    }
}
