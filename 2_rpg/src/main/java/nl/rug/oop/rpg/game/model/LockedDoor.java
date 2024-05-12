package nl.rug.oop.rpg.game.model;

import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.util.Scan;

public class LockedDoor extends Door {
    private static final String PASSWORD = "42";

    public LockedDoor(String description, Room from, Room to) {
        super(description, from, to);
    }

    @Override
    public void interact(Player player) {
        System.out.println("The door is locked, to enter please type" +
                " the answer to life the universe and everything:");
        String input = Scan.nextLine();
        if (input.equals(PASSWORD)) {
            super.interact(player);
        } else {
            System.out.println("Wrong! The door remains locked.");
        }
    }
}
