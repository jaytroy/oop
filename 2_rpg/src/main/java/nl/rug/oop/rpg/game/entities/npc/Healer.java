package nl.rug.oop.rpg.game.entities.npc;

import nl.rug.oop.rpg.game.Decideable;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.util.Scan;

/**
 * An NPC that heals the player.
 */
public class Healer extends NPC implements Decideable {
    private int experience;
    private final int MAX_XP = 10;

    /**
     * The constructor for the healer.
     *
     * @param description The description of the NPC.
     * @param experience  The amount of experience that the healer has.
     */
    public Healer(String description, int experience) {
        super(description);
        this.experience = Math.max(experience, MAX_XP);
    }

    @Override
    public void interact(Player p) {
        System.out.println("You interact with " + super.getDescription() + ". they agree to put you on a 4 " +
                "week waiting list. What do you want to do?");

        boolean exit = false;
        while (!exit) {
            showInteractionMenu();
            exit = decide(p);
        }
    }

    @Override
    public void showInteractionMenu() {
        System.out.println(
                "\nWhat do you want to do?\n" +
                        "  (0) Beg them for a check up\n" +
                        "  (1) Give up on healthcare and leave\n");
    }

    @Override
    public boolean decide(Player p) {
        int choice = Scan.nextInt();

        switch (choice) {
            case 0 -> {
                return heal(p);
            }
            case 1 -> {
                return true;
            }
            default -> System.out.println(choice + " is not one of the choices");
        }
        return false;
    }

    /**
     * Method in which the healer restores the player's health of they have less than 6 HP,
     * increasing its experience every time it does so.
     *
     * @param p The player getting healed.
     * @return False if player gets healed, true otherwise.
     */
    public boolean heal(Player p) {
        if (p.getAliveAttributes().getHealth() >= 5) {
            System.out.println(super.getDescription() + " sees that you are not under any medical emergency," +
                    " you should just take some Tylenol®");
            return false;
        } else {
            p.getAliveAttributes().addHealth(experience);
            increaseExperience();
            System.out.println("Being in critical condition and on the brink of death " +
                    super.getDescription() + " agree to prescribe you some medicine (not Tylenol®), you now have "
                    + p.getAliveAttributes().getHealth() + "health.\n");
            return true;
        }
    }

    /**
     * Increases the healers experience.
     */
    private void increaseExperience() {
        if (experience < MAX_XP) {
            experience += 1;
        }
    }
}


