package nl.rug.oop.rpg.game.model;

import nl.rug.oop.rpg.game.Inspectable;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.entities.npc.NPC;
import nl.rug.oop.rpg.game.util.Scan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A model for a room.
 */
public class Room implements Inspectable, Serializable {
    private final String description;
    private List<Door> doors;
    private List<NPC> npcs;

    /**
     * The contructor for a room.
     * @param description The description.
     */
    public Room(String description) {
        this.description = description;
        this.doors = new ArrayList<>();
        this.npcs = new ArrayList<>();
    }

    @Override
    public void inspect() {
        System.out.println("You see: " + description +
                ". The room has " + doors.size() + " doors.");
    }

    /**
     * Adds a door to the room.
     * @param door The door.
     */
    public void addDoor(Door door) {
        doors.add(door);
    }

    /**
     * Adds an NPC to the room.
     * @param npc The NPC.
     */
    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    /**
     * Prints all the doors in the room to the console and allows a player to enter.
     * @param p The player.
     */
    public void showDoors(Player p) {
        if (doors.isEmpty()) {
            System.out.println("This room has no doors. You are stuck!");
            return;
        }

        System.out.println("You look around for doors. You see:");
        int i = 0;
        for (Door door : doors) {
            System.out.print("  (" + i + ") ");
            door.inspect();
            i++;
        }

        System.out.println("Which door do you take? (-1 : stay here)");
        int choice = Scan.nextInt();
        if (choice == -1) {
            System.out.println("You stay here.");
            return;
        } else if (choice < doors.size()) {
            doors.get(choice).interact(p);
        } else {
            System.out.println(choice + " is not one of the choices");
        }
    }

    /**
     * Prints all the NPCs to the console and allows the player to fight.
     * @param p The player.
     */
    public void showNPCs(Player p) {
        if (npcs.isEmpty()) {
            System.out.println("This room has no npcs. You are lonely!");
        }

        System.out.println("You look around for npcs. You see:");
        int i = 0;
        for (NPC npc : npcs) {
            System.out.print("  (" + i + ") ");
            npc.inspect();
            i++;
        }

        System.out.println("Interact with an NPC? (-1 : do nothing)");
        int choice = Scan.nextInt();
        if (choice == -1) {
            System.out.println("You do not interact with any npcs.");
            return;
        } else if (choice < npcs.size()) {
            npcs.get(choice).interact(p);
        } else {
            System.out.println(choice + " is not one of the choices");
        }
    }
}
