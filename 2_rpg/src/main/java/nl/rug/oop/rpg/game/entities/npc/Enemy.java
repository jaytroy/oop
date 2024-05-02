package nl.rug.oop.rpg.game.entities.npc;

import lombok.Getter;
import nl.rug.oop.rpg.game.entities.Combatable;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.util.Scan;

/**
 *  class defining a NPC that is capable of dealing damage to the player.
 */
public class Enemy extends NPC implements Combatable<Player> {
    @Getter
    private int damage;
    private int health;

    /**
     * Enemy class for NPCs one can engage in combat with.
     * @param description description of the enemy.
     * @param health health of the enemy.
     * @param damage damage the enemy can deal.
     */
    public Enemy(String description, int health, int damage) {
        super(description);
        this.health = health;
        this.damage = damage;
    }

    @Override
    public void interact(Player p) {
        System.out.println("You interact with " + super.getDescription() + ". They seems a bit hostile\n" +
                "What do you want to do?");

        boolean exit = false;
        while (!exit) {
            showInteractionMenu();
            int choice = Scan.nextInt();

            switch (choice) {
                case 0:
                    talk();
                    break;
                case 1:
                    exit = attack(p);
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println(choice + " is not one of the choices");
                    break;
            }
        }
    }

    /**
     * prints the possible interaction options to the player.
     */
    public void showInteractionMenu() {

        System.out.println(
                "\nWhat do you want to do?\n" +
                        "  (0) Talk\n" +
                        "  (1) Attack\n" +
                        "  (2) Leave them alone\n");
    }

    /**
     * Method for botht the enemy and the attacking player to deal damage to each other.
     * @param p player attacking the enemy.
     * @return boolean indicating if the enemy is still alive or not after the attack.
     */
    public boolean attack(Player p) {
        if(this.health <= 0) {
            System.out.println("You attack the corpse of " + super.getDescription() + " They stay dead.");
            return false;
        }
        System.out.println("You attack " + super.getDescription() + "! You deal " + p.getDamage() + " damage.");
        this.takeDamage(p.getDamage());
        if (this.health <= 0) {
            System.out.println("You killed " + super.getDescription() + ". Shame on you.");
            return true;
        }
        System.out.println(super.getDescription() + " is at " + this.health + " health\n");

        return p.attack(this);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    /**
     * method that will output a given dialogue provided the enemy is alive.
     */
    public void talk() {
        if(this.health <= 0) {
            System.out.println("You try to talk to " + super.getDescription() + ". Dead things don't tend to say much");
            return;
        }
        System.out.println("You talk to " + super.getDescription()+ ". They're not very coherent." +
                " You notice that they have " + this.health + " health.");
    }
}