package nl.rug.oop.rpg.game;

import lombok.Getter;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.entities.npc.NPC;
import nl.rug.oop.rpg.game.util.IOUtils;
import nl.rug.oop.rpg.game.util.SaveType;
import nl.rug.oop.rpg.game.util.Scan;

import java.io.Serializable;
import java.util.List;

import static nl.rug.oop.rpg.game.util.SaveType.QUICKSAVE;
import static nl.rug.oop.rpg.game.util.SaveType.REGULARSAVE;

/**
 * The instance of the game.
 */
public class Game implements Serializable, Decideable {
    @Getter
    private Player player;
    private List npcs;
    private IOUtils utils;
    private static final long serialVersionUID = 42L;

    /**
     * The constructor for the game.
     *
     * @param player The player of the game.
     * @param npcs The NPCs in the game.
     */
    public Game(Player player, List<NPC> npcs) {
        this.player = player;
        this.npcs = npcs;
        this.utils = new IOUtils();
    }

    /**
     * Starts the game loop. Keeps going while player is alive.
     */
    public void start() {
        System.out.println("Welcome to the dungeon of aletta Jacobs hall!" +
                "To exit the game you must defeat the evil lecturer Meijster.\n");

        while (player.getAliveAttributes().getHealth() > 0) {
            showInteractionMenu();
            decide(player);
        }

        Scan.closeScanner();
    }

    /**
     * default interaction menu that gets printed and prompts the player to choose an option.
     */
    public void showInteractionMenu() {
        System.out.println(
                "\nWhat do you want to do?\n" +
                        "  (0) Look around\n" +
                        "  (1) Look for a way out\n" +
                        "  (2) Look for company\n" +
                        "  (3) QuickSave\n" +
                        "  (4) Save\n" +
                        "  (5) QuickLoad\n" +
                        "  (6) Load\n");
    }

    /**
     * Lays out the decisions a player can make.
     *
     * @param p The player.
     * @return Interface bound return.
     */
    public boolean decide(Player p) {
        int choice = Scan.nextInt();
        switch (choice) {
            case 0 -> p.getCurrentRoom().inspect();
            case 1 -> p.getCurrentRoom().showDoors(player);
            case 2 -> p.getCurrentRoom().showNPCs(player);
            case 3 -> saveGame(QUICKSAVE);
            case 4 -> saveGame(REGULARSAVE);
            case 5 -> loadGame(QUICKSAVE);
            case 6 -> loadGame(REGULARSAVE);
            default -> System.out.println(choice + " is not one of the choices");
        }
        return false;
    }

    /**
     * Calls logic that saves a game.
     * @param type The save type (Regular, Quick).
     */
    private void saveGame(SaveType type) {
        utils.save(this, type);
    }

    /**
     * Calls logic that loads a game.
     * @param type The save type (Regular, Quick).
     */
    private void loadGame(SaveType type) {
        Game loadedGame = utils.load(type);
        if (loadedGame != null) {
            this.player = loadedGame.player;
            this.npcs = loadedGame.npcs;
        }
    }
}
