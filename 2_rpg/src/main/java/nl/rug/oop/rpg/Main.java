package nl.rug.oop.rpg;
import nl.rug.oop.rpg.game.GameInitializer;
import nl.rug.oop.rpg.game.entities.npc.*;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.model.*;
import nl.rug.oop.rpg.game.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class.
 */
public class Main {
    /**
     * The main method.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        Game game = GameInitializer.initializeGame();
        game.start();
    }

}