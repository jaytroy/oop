package nl.rug.oop.rpg;

import nl.rug.oop.rpg.game.entities.npc.Enemy;
import nl.rug.oop.rpg.game.entities.npc.NPC;
import nl.rug.oop.rpg.game.entities.npc.Person;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.entities.npc.Teacher;
import nl.rug.oop.rpg.game.model.DamageDoor;
import nl.rug.oop.rpg.game.model.Door;
import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.game.model.NormalDoor;
import nl.rug.oop.rpg.game.model.Room;

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
        Room room1 = new Room("A large lecture hall");
        Room room2 = new Room("A long hallway");
        Room room3 = new Room("A broom closet");

        Door door1 = new DamageDoor("A mysterious door", room1, room2, 1);
        Door door2 = new NormalDoor("A door with a small window", room1, room3);

        room1.addDoor(door1);
        room1.addDoor(door2);

        NPC npc1 = new Enemy("Arnold Meijster", 6, 1);
        NPC npc2 = new Person("Janitor");
        NPC npc3 = new Enemy("Public enemy #1", 100, 5);
        NPC npc4 = new Teacher("The best lecturer", 5, 1);

        room1.addNPC(npc1);
        room2.addNPC(npc3);
        room3.addNPC(npc2);
        room1.addNPC(npc4);

        List<NPC> npcs = new ArrayList<>();
        npcs.add(npc1);
        npcs.add(npc2);
        npcs.add(npc3);
        npcs.add(npc4);

        Game game = new Game(new Player("Jay", 10, 5), npcs);
        Player player = game.getPlayer();

        player.setCurrentRoom(room1);

        game.start();
    }
}