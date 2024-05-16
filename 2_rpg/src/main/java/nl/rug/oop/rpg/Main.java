package nl.rug.oop.rpg;

import nl.rug.oop.rpg.game.GameInitializer;
import nl.rug.oop.rpg.game.Game;

/**
 * The main class.
 */
public class Main {
    /**
     * The main method.
     * @param args Args.
     */
    public static void main(String[] args) {
        GameInitializer initializer = new GameInitializer();
        Game game = initializer.initializeGame();
        game.start();
    }

}