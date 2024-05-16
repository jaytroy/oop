package nl.rug.oop.rpg.game.model;

import lombok.Getter;
import nl.rug.oop.rpg.game.AliveAttributes;
import nl.rug.oop.rpg.game.entities.Combatable;

/**
 * An evil door that will fight you.
 */
public class EvilDoor extends Door implements Combatable {
    @Getter
    private AliveAttributes aliveAttributes;

    /**
     * The constructor for an evil door.
     * @param description The description of the door.
     * @param from Where the door leads from.
     * @param to Where the door leads to.
     * @param health The door's health.
     * @param damage The door's damage.
     */
    public EvilDoor(String description, Room from, Room to, int health, int damage) {
        super(description, from, to);
        this.aliveAttributes = new AliveAttributes(health, damage);
    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public boolean attack(Combatable target) {
        return false;
    }
}
