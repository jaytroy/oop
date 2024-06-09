package nl.rug.oop.rts.model.entity;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.Saveable;

/**
 * This class handles one unit in the army.
 */
@Getter
public class Unit implements Saveable {
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

    @Override
    public String saveJson() {
        StringBuilder json = new StringBuilder();
        json.append("\n\t\t\t\t\t\t{\n");
        json.append("\t\t\t\t\t\t\t\"name\": \"").append(name).append("\",\n");
        json.append("\t\t\t\t\t\t\t\"damage\": ").append(damage).append(",\n");
        json.append("\t\t\t\t\t\t\t\"health\": ").append(health).append(",\n");
        json.append("\t\t\t\t\t\t\t\"accuracy\": ").append(accuracy).append("\n");
        json.append("\t\t\t\t\t\t}");
        return json.toString();
    }
}

