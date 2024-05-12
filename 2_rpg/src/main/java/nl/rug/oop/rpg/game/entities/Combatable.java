package nl.rug.oop.rpg.game.entities;

/**
 * interface assigned to entities that can recieve (have health) and deal damage.
 * @param <T> The other combatable target of the attacker.
 */
public interface Combatable<T> {

    void takeDamage(int damage);

    boolean attack(T target);

    void getAliveAttributes();
}
