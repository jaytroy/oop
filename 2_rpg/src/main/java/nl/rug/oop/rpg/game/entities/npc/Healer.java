package nl.rug.oop.rpg.game.entities.npc;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rpg.game.entities.Player;
import nl.rug.oop.rpg.game.util.Scan;

public class Healer extends NPC {
    @Getter
    @Setter
    private int experience;

    public Healer(String description, int experience) {
        super(description);
        this.experience = experience;
    }


    @Override
    public void interact(Player p) {
        System.out.println("You interact with " + super.getDescription() + ". they agree to put you on a 4 week waiting list\n" +
                "What do you want to do?");

        boolean exit = false;
        while (!exit) {
            showInteractionMenu();
            int choice = Scan.nextInt();

            switch (choice) {
                case 0:
                    exit = heal(p);
                    break;
                case 1:
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
                        "  (0) Beg them for a check up\n" +
                        "  (1) give up on healthcare and leave\n");
    }

    /**
     * method in which the healer restores the player's health of they have less than 6.
     * increasing their epxerience every time they do so
     * @param p the player receiving the lecture.
     */
    public boolean heal(Player p) {
        if(p.getHealth() >= 5) {
            System.out.println(super.getDescription() + " sees that you are not under any medical emergency," +
                    " you should just take some paracetamol");
            return false;
        } else {
            p.setHealth(p.getHealth()+5);
            increaseExperience();
            System.out.println("Being in critical condition and on the brink of death " +
                    super.getDescription() + " agree to prescribe you some medicine, you now have " + p.getHealth() +
                    " health.\n");
            return true;
        }
    }

    public void increaseExperience(){
        if(this.getExperience()<10){
            this.setExperience(this.getExperience()+1);
        }
    }
}


