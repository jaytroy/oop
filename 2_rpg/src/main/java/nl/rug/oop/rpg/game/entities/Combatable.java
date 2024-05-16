package nl.rug.oop.rpg.game.entities;

/**
 * Interface assigned to entities that can receive and deal damage.
 */
public interface Combatable {

    void takeDamage(int damage);

    boolean attack(Combatable target);

    AliveAttributes getAliveAttributes();

    String getDescription();
}
