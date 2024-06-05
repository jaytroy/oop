package nl.rug.oop.rts.model.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * This class handles one unit in the army.
 */
@Getter
public class Unit {
    private final int MID_ACCURACY = 50;

    private final String name;
    @Setter
    private int damage;
    @Setter
    private int health;
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
        this.accuracy = MID_ACCURACY;
    }

}

