package nl.rug.oop.rpg;

import nl.rug.oop.rpg.entities.Enemy;
import nl.rug.oop.rpg.entities.NPC;
import nl.rug.oop.rpg.entities.Player;
import nl.rug.oop.rpg.model.Door;
import nl.rug.oop.rpg.model.Game;
import nl.rug.oop.rpg.model.Room;

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
        Game game = new Game("Jay");
        Player player = game.getPlayer();

        Room room1 = new Room("A large lecture hall");
        Room room2 = new Room("A long hallway");
        Room room3 = new Room("A broom closet");

        Door door1 = new Door("A mysterious door", room1, room2);
        Door door2 = new Door("A door with a small window", room1, room3);

        room1.addDoor(door1);
        room1.addDoor(door2);

        NPC npc1 = new Enemy("Arnold Meijster", 6, 1);
        NPC npc2 = new NPC("Janitor");
        NPC npc3 = new Enemy("Public enemy #1", 100, 5);

        room1.addNPC(npc1);
        room2.addNPC(npc3);
        room3.addNPC(npc2);


        player.setCurrentRoom(room1);

        game.start();
    }
}