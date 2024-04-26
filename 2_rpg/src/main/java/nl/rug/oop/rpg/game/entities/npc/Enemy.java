package nl.rug.oop.rpg.game.entities.npc;

import lombok.Getter;
import nl.rug.oop.rpg.game.entities.Combatable;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.util.Scan;

public class Enemy extends NPC implements Combatable<Player> {
    @Getter
    private int damage;
    private int health;

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

    public void showInteractionMenu() {
        System.out.println(
                "\nWhat do you want to do?\n" +
                        "  (0) Talk\n" +
                        "  (1) Attack\n" +
                        "  (2) Leave them alone\n");
    }

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

    public void talk() {
        if(this.health <= 0) {
            System.out.println("You try to talk to " + super.getDescription() + ". Dead things don't tend to say much");
            return;
        }
        System.out.println("You talk to " + super.getDescription()+ ". They're not very coherent. You notice that they have " + this.health + " health.");
    }
}