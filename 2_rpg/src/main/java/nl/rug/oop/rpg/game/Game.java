package nl.rug.oop.rpg.game;

import lombok.Getter;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.entities.npc.NPC;

import nl.rug.oop.rpg.game.util.IOUtils;
import nl.rug.oop.rpg.game.util.SaveType;
import nl.rug.oop.rpg.game.util.Scan;

import java.io.Serializable;
import java.util.List;

import static nl.rug.oop.rpg.game.util.IOUtils.load;
import static nl.rug.oop.rpg.game.util.IOUtils.save;
import static nl.rug.oop.rpg.game.util.SaveType.QUICKSAVE;
import static nl.rug.oop.rpg.game.util.SaveType.REGULARSAVE;

/**
 * Game class containing all of the logic oft he game.
 */
public class Game implements Serializable {
    @Getter
    private Player player;
    private List npcs;

    public Game(Player player, List<NPC> npcs) {
        this.player = player;
        this.npcs = npcs;
    }

    /**
     * called in the main method to start a new game.
     */
    public void start() {
        System.out.println("Welcome to the dungeon of aletta Jacobs hall!" +
                "To exit the game you must defeat the evil lecturer Meijster.\n");
        while (player.getHealth() > 0) {
            showInteractionMenu();
            decide();
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
     * method performing the underlying logic for each option.
     */
    public void decide() {
        int choice = Scan.nextInt();
        switch (choice) {
            case 0 -> player.getCurrentRoom().inspect();
            case 1 -> player.getCurrentRoom().showDoors(player);
            case 2 -> player.getCurrentRoom().showNPCs(player);
            case 3 -> saveGame(QUICKSAVE);
            case 4 -> saveGame(REGULARSAVE);
            case 5 -> loadGame(QUICKSAVE);
            case 6 -> loadGame(REGULARSAVE);
            default -> System.out.println(choice + " is not one of the choices");
        }
    }

    private void saveGame(SaveType saveType) {
        IOUtils.save(this, saveType);
    }

    private void loadGame(SaveType type) {
        Game loadedGame = IOUtils.load(type);
        if (loadedGame != null) {
            this.player = loadedGame.player;
            this.npcs = loadedGame.npcs;
        }
    }
}
