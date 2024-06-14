package nl.rug.oop.rpg.game.entities.npc;

import nl.rug.oop.rpg.game.entities.Player;

/**
 * teacher class, apparently do not count as "persons".
 */
public class Teacher extends NPC {
    private int lectures;
    private int value;

    /**
     * constructr of the lecturer.
     * @param description description for when the lecturer, functionally a name.
     * @param lectures ways the player can interact with the teacher to increase their damage.
     * @param value the increase in damage the lectures offer.
     */
    public Teacher(String description, int lectures, int value) {
        super(description);
        this.lectures = lectures;
        this.value = value;
    }

    /**
     * method in which the teacher exhausts on of their lectures to increase a players damage.
     * @param p the player receiving the lecture.
     */
    public void teach(Player p) {
        if(lectures <= 0) {
            System.out.println(super.getDescription() + " has taught you everything they could!");
            return;
        }
        p.getAliveAttributes().addDamage(value);
        System.out.println(super.getDescription() + " teaches you about combat. You gain " + value + " damage.\n" +
                "Your damage: " + p.getAliveAttributes().getDamage());
        lectures--;
    }

    @Override
    public void interact(Player p) {
        teach(p);
    }
}
