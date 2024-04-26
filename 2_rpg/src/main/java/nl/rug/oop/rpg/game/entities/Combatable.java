package nl.rug.oop.rpg.game.entities;

public interface Combatable<T> {
    void takeDamage(int damage);
    boolean attack(T target);
}
