package nl.rug.oop.rpg.game.model;

import nl.rug.oop.rpg.game.entities.Player;

public class DamageDoor extends Door {
    private int damage;

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
