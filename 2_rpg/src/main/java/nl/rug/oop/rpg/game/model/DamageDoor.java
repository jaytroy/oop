package nl.rug.oop.rpg.game.model;

import nl.rug.oop.rpg.game.entities.Player;

/**
 * A door that damages you as you go through.
 */
public class DamageDoor extends Door {
    private int damage;

    /**
     * The constructor for a Damage Door.
     * @param description The description.
     * @param from Where the door leads from.
     * @param to Where the door leads to.
     * @param damage How much damage a player receives.
     */
    public DamageDoor(String description, Room from, Room to, int damage) {
        super(description, from, to);
        this.damage = damage;
    }

    @Override
    public void interact(Player p) {
        p.takeDamage(damage);
        System.out.println("The crossbow shoots at you! You take " + damage + " damage");
        p.setCurrentRoom(super.room2);
    }
}
