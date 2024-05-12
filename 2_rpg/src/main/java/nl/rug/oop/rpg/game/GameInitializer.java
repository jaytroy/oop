package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.entities.npc.*;
import nl.rug.oop.rpg.game.model.*;

import java.util.ArrayList;
import java.util.List;

public class GameInitializer{
    public Game initializeGame() {
        Room room1 = new Room("A large lecture hall");
        Room room2 = new Room("A long hallway");
        Room room3 = new Room("A broom closet");

        Door door1 = new DamageDoor("A mysterious door with a huge crossbow attached to it", room1, room2, 1);
        Door door2 = new NormalDoor("A door with a small window", room1, room3);
        Door door3 = new LockedDoor("A door with intricate lock mechanism around it", room2, room3);
        Door door4 = new EvilDoor("A door covered with flesh.", room1,room2, 8, 2);

        room1.addDoor(door1);
        room1.addDoor(door2);
        room2.addDoor(door1);
        room2.addDoor(door3);
        room3.addDoor(door2);
        room3.addDoor(door3);

        NPC npc1 = new Enemy("Meijster's TA minion", 6, 1);
        NPC npc2 = new Person("Janitor");
        NPC npc3 = new Enemy("Arnold Meijster", 100, 5);
        NPC npc4 = new Teacher("StudeCEE lecturer", 5, 1);
        NPC npc5 = new Healer("StudentArts", 5);

        room1.addNPC(npc1);
        room2.addNPC(npc3);
        room3.addNPC(npc2);
        room3.addNPC(npc5);
        room1.addNPC(npc4);

        List<NPC> npcs = new ArrayList<>();
        npcs.add(npc1);
        npcs.add(npc2);
        npcs.add(npc3);
        npcs.add(npc4);
        npcs.add(npc5);

        Game game = new Game(new Player("JaPan", 10, 5), npcs);
        Player player = game.getPlayer();

        player.setCurrentRoom(room1);

        return game;
    }
}
