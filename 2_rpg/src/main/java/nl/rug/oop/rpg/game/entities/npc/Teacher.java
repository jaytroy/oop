package nl.rug.oop.rpg.game.entities.npc;

import nl.rug.oop.rpg.game.entities.Player;

public class Teacher extends NPC {
    private int lectures;
    private int value;

    public Teacher(String description, int lectures, int value) {
        super(description);
        this.lectures = lectures;
        this.value = value;
    }

    public void teach(Player p) {
        if(lectures <= 0) {
            System.out.println(super.getDescription() + " has taught you everything they could!");
            return;
        }
        p.setDamage(p.getDamage() + value);
        System.out.println(super.getDescription() + " teaches you about combat. You gain " + value + " damage.\n" +
                "Your damage: " + p.getDamage());
        lectures--;
    }

    @Override
    public void interact(Player p) {
        teach(p);
    }
}
