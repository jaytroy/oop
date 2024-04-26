package nl.rug.oop.rpg.entities;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rpg.model.Room;

public class Player implements Combatable {
    @Getter
    private final String name;
    @Getter
    private int health;
    @Getter
    private int damage;

    @Getter
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
}
