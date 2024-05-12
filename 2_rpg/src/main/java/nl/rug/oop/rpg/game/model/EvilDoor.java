package nl.rug.oop.rpg.game.model;

import lombok.Getter;
import nl.rug.oop.rpg.game.AliveAttributes;
import nl.rug.oop.rpg.game.entities.Combatable;
import nl.rug.oop.rpg.game.entities.Player;

public class EvilDoor extends Door implements Combatable<Player> {
    @Getter
    private AliveAttributes aliveAttributes;

    public EvilDoor(String description, Room from, Room to, int health, int damage) {
        super(description, from, to);
        this.aliveAttributes = new AliveAttributes(health, damage);
    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public boolean attack(Player target) {
        return false;
    }
}
