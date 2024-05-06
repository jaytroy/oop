package nl.rug.oop.rpg.game.model;

import nl.rug.oop.rpg.game.entities.Player;

public class LockedDoor extends Door {
    private static final String PASSWORD = "42";

    public LockedDoor(String description, Room from, Room to) {
        super(description, from, to);
    }

    @Override
    public void interact(Player player) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("The door is locked, to enter please type\n" +
                " the answer to life the universe and everything:");
        String input = scanner.nextLine();
        if (input.equals(PASSWORD)) {
            super.interact(player);
        } else {
            System.out.println("Wrong! The door remains locked.");
        }
    }
}
