package nl.rug.oop.rpg.game.entities.npc;

import lombok.Getter;
import nl.rug.oop.rpg.game.AliveAttributes;
import nl.rug.oop.rpg.game.Decideable;
import nl.rug.oop.rpg.game.entities.Combatable;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.util.Scan;

/**
 * An NPC that damages the player.
 */
public class Enemy extends NPC implements Combatable, Decideable {
    @Getter
    private AliveAttributes aliveAttributes;

    /**
     * The constructor for the enemy.
     *
     * @param description The description of the enemy.
     * @param health      The health of the enemy.
     * @param damage      The damage the enemy can deal.
     */
    public Enemy(String description, int health, int damage) {
        super(description);
        this.aliveAttributes = new AliveAttributes(health, damage);
    }

    @Override
    public void interact(Player p) {
        System.out.println("You interact with " + super.getDescription() + ". They seems a bit hostile\n" +
                "What do you want to do?");

        boolean exit = false;
        while (!exit) {
            showInteractionMenu();
            exit = decide(p);
        }
    }

    /**
     * prints the possible interaction options to the player.
     */
    @Override
    public void showInteractionMenu() {
        System.out.println(
                "\nWhat do you want to do?\n" +
                        "  (0) Talk\n" +
                        "  (1) Attack\n" +
                        "  (2) Leave them alone\n");
    }

    @Override
    public boolean decide(Player p) {
        int choice = Scan.nextInt();

        switch (choice) {
            case 0 -> talk();
            case 1 -> {
                return attack(p);
            }
            case 2 -> {
                return true;
            }
            default -> System.out.println(choice + " is not one of the choices");
        }
        return false;
    }

    /**
     * Method for both the enemy and the attacking player to deal damage to each other.
     *
     * @param p player attacking the enemy.
     * @return boolean indicating if the enemy is still alive or not after the attack.
     */
    public boolean attack(Combatable p) {
        if (this.aliveAttributes.getHealth() <= 0) {
            System.out.println("You attack the corpse of " + super.getDescription() + " They stay dead.");
            return false;
        }
        System.out.println("You attack " + super.getDescription() + "! You deal " +
                p.getAliveAttributes().getDamage() + " damage.");

        this.takeDamage(p.getAliveAttributes().getDamage());
        if (this.aliveAttributes.getHealth() <= 0) {
            System.out.println("You killed " + super.getDescription() + ". Shame on you.");
            return true;
        }
        System.out.println(super.getDescription() + " is at " + this.getAliveAttributes().getHealth() + " health\n");

        return p.attack(this);
    }

    @Override
    public void takeDamage(int damage) {
        aliveAttributes.takeDamage(damage);
    }

    /**
     * method that will output a given dialogue provided the enemy is alive.
     */
    public void talk() {
        if (aliveAttributes.getHealth() <= 0) {
            System.out.println("You try to talk to " + super.getDescription() + ". Dead things don't tend to say much");
            return;
        }
        System.out.println("You talk to " + super.getDescription() + ". They're not very coherent." +
                " You notice that they have " + aliveAttributes.getHealth() + " health.");
    }
}