package nl.rug.oop.rpg.game;

import lombok.Getter;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.entities.npc.NPC;
import nl.rug.oop.rpg.game.util.Scan;

import java.io.Serializable;
import java.util.List;

import static nl.rug.oop.rpg.game.util.IOUtils.load;
import static nl.rug.oop.rpg.game.util.IOUtils.save;

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
        while (player.getHealth() > 0) {
            showInteractionMenu();
            int choice = Scan.nextInt();
            decide(choice);
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
     * @param choice the users recorded input.
     */
    public void decide(int choice) {
        switch (choice) {
            case 0 -> player.getCurrentRoom().inspect();
            case 1 -> player.getCurrentRoom().showDoors(player);
            case 2 -> player.getCurrentRoom().showNPCs(player);
            case 3 -> save(this, 0);
            case 4 -> save(this, 1);
            case 5 -> {
                Game loadedGame = load(0);
                if (loadedGame != null) {
                    this.player = loadedGame.player;
                    this.npcs = loadedGame.npcs;
                }
            }
            case 6 -> {
                Game loadedGame = load(1);
                if (loadedGame != null) {
                    this.player = loadedGame.player;
                    this.npcs = loadedGame.npcs;
                }
            }
            default -> System.out.println(choice + " is not one of the choices");
        }
    }
}
