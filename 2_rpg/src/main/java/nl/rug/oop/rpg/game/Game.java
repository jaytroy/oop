package nl.rug.oop.rpg.game;

import lombok.Getter;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.util.Scan;

public class Game {
    @Getter
    private Player player;

    public Game(String playerName) {
        this.player = new Player(playerName, 10, 5);
    }

    public void start() {
        while(player.getHealth() > 0) {
            showInteractionMenu();
            int choice = Scan.nextInt();
            decide(choice);
        }

        Scan.closeScanner();
    }

    public void showInteractionMenu() {
        System.out.println(
                "\nWhat do you want to do?\n" +
                        "  (0) Look around\n" +
                        "  (1) Look for a way out\n" +
                        "  (2) Look for company\n");
    }

    public void decide(int choice) {
        switch (choice) {
            case 0: player.getCurrentRoom().inspect(); break;
            case 1: player.getCurrentRoom().showDoors(player); break;
            case 2: player.getCurrentRoom().showNPCs(player); break;
            default: System.out.println(choice + " is not one of the choices"); break;
        }
    }
}
