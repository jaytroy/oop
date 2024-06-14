package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.game.entities.Player;

/**
 *  Interface that makes objects interactable.
 */
public interface Interactable {
    void interact(Player p);
}
