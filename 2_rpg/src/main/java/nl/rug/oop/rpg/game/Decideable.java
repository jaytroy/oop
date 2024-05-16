package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.game.entities.Player;

/**
 * Marks a class as containing decisions for the player, and provides the method signatures.
 */
public interface Decideable {
    void showInteractionMenu();

    boolean decide(Player p);
}
