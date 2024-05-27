package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This class handles one unit in the army.
 */
public class Unit {
    @Getter
    private String name;
    @Getter
    @Setter
    private int damage;
    @Getter
    @Setter
    private int health;
    @Getter
    @Setter
    private int accuracy;

    /**
     * Constructor of a unit in the army.
     * @param name name of the unit
     * @param damage damage  the unit
     * @param health health of the unit
     */
    public Unit(String name, int damage, int health) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.accuracy = 50;
    }

}

