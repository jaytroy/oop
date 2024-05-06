package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.game.entities.Player;

interface Command {
    void execute(Player player);
}
