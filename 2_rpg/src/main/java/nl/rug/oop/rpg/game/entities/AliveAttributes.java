package nl.rug.oop.rpg.game.entities;

import lombok.Getter;

import java.io.Serializable;

/**
 * Stores attributes specific to "alive" classes.
 */
@Getter
public class AliveAttributes implements Serializable {
    private int health;
    private int damage;
    private static final long serialVersionUID = 42L;

    /**
     * The constructor for alive attributes.
     * @param health The holder's health.
     * @param damage The holder's damage.
     */
    public AliveAttributes(int health, int damage) {
        this.health = health;
        this.damage = damage;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public void addDamage(int intel) {
        this.damage += intel;
    }

    public void addHealth(int health) {
        this.health += health;
    }
}