package nl.rug.oop.rpg.game;

import lombok.Getter;

@Getter
public class AliveAttributes {
    private int health;
    private int damage;

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