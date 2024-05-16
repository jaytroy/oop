package nl.rug.oop.rpg.game;

import lombok.Getter;

/**
 * Stores attributes specific to "alive" classes.
 */
@Getter
public class AliveAttributes {
    private int health;
    private int damage;

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