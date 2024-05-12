package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.game.entities.Player;

public interface Decideable {
    void showInteractionMenu();
    boolean decide(Player p);
}
