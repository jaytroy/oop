package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.game.entities.Player;

/**
 *  interface assigning objects to interact method.
 */
public interface Interactable {
    void interact(Player player);
}
