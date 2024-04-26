package nl.rug.oop.rpg.entities;

import nl.rug.oop.rpg.util.Scan;

public class Enemy extends NPC implements Combatable {
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
                    if(this.health <= 0) {
                        System.out.println("You talk to " + super.getDescription() + ". Dead things don't tend to say much");
                        break;
                    }
                    System.out.println("You talk to " + super.getDescription() + ". They're not very coherent"); break;
                case 1:
                    exit = combat(p);
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

    public boolean combat(Player p) {
        if(this.health <= 0) {
            System.out.println("You attack the corpse of " + super.getDescription() + " They stay dead.");
            return false;
        }
        System.out.println("You attack " + super.getDescription() + "!");
        this.takeDamage(p.getDamage());
        if (this.health <= 0) {
            System.out.println("You killed " + super.getDescription() + ". Shame on you.");
            return true;
        }
        System.out.println(super.getDescription() + " is at " + this.health + " health\n");
        System.out.println(super.getDescription() + " attacks you!");
        p.takeDamage(this.damage);
        if (p.getHealth() <= 0) {
            System.out.println("You've been killed. Terribly sad.");
            return true;
        }
        System.out.println(p.getName() + " is at " + p.getHealth() + " health\n");
        return false;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }
}