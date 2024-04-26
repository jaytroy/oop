package nl.rug.oop.rpg.game.entities;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rpg.game.entities.npc.Enemy;
import nl.rug.oop.rpg.game.model.Room;

@Getter
public class Player implements Combatable<Enemy> {
    private final String name;
    private int health;
    @Setter
    private int damage;
    @Setter
    private Room currentRoom;

    public Player(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

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
