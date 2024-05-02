package nl.rug.oop.rpg.game.entities;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rpg.game.entities.npc.Enemy;
import nl.rug.oop.rpg.game.model.Room;

import java.io.Serializable;

/**
 * player class which we play the game through.
 */
@Getter
public class Player implements Combatable<Enemy>, Serializable {
    private final String name;
    @Setter
    @Getter
    private int health;
    @Setter
    private int damage;
    @Setter
    private Room currentRoom;

    /**
     * constructor for the player class.
     * @param name name of the player.
     * @param health current health of the player.
     * @param damage damage the player cna inflict.
     */
    public Player(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    /**
     * handling combat between two combatable enemies.
     * @param e the attacking enemy.
     * @return boolean signifying if the player is dead or not after the attack.
     */
    public boolean attack(Enemy e) {
        System.out.println(e.getDescription() + " attacks you! They deal " + e.getDamage() + " damage.");
        this.takeDamage(e.getDamage());
        if (this.getHealth() <= 0) {
            System.out.println("You've been killed. Terribly sad.");
            return true;
        }
        System.out.println(this.getName() + " is at " + this.getHealth() + " health\n");
        return false;
    }
}
