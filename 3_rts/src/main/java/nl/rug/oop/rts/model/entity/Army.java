package nl.rug.oop.rts.model.entity;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.Saveable;
import nl.rug.oop.rts.model.base.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles the details of armies.
 */
@Getter
public class Army implements Saveable {
    private List<Unit> units;
    private Faction faction;
    private Boolean team;
    private int id;
    @Setter
    private Node comingFrom;
    private int numUnits;

    /**
     * This is the constructor of the army.
     *
     * @param id      the id of the army
     * @param faction its faction
     */
    public Army(int id, Faction faction) {
        this.units = createRandomUnits(faction);
        this.faction = faction;
        this.team = faction.getTeam();
        this.id = id;
        this.comingFrom = null;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    /**
     * This method creates random units for an army.
     * @param faction the faction type of the units
     * @return the list of random units
     */
    public List<Unit> createRandomUnits(Faction faction) {
        List<Unit> randomUnits = new ArrayList<>();

        Random random = new Random();
        Names names = new Names();
        numUnits = random.nextInt(41) + 10;
        for (int i = 0; i < numUnits; i++) {
            String randomUnitName = names.getUnitNames(faction);
            int randDamage = random.nextInt(10) + 1;
            int randHealth = random.nextInt(101) + 50;
            randomUnits.add(new Unit(randomUnitName, randDamage, randHealth));
        }
        return randomUnits;
    }

    @Override
    public String saveJson() {
        StringBuilder json = new StringBuilder();
        json.append("\n\t\t\t\t{\n");
        json.append("\t\t\t\t\t\"id\": ").append(id).append(",\n");
        json.append("\t\t\t\t\t\"faction\": \"").append(faction).append("\",\n");
        json.append("\t\t\t\t\t\"team\": ").append(team).append(",\n");
        json.append("\t\t\t\t\t\"units\": [");
        for (Unit unit : units) {
            json.append(unit.saveJson()).append(",");
        }

        if(units.isEmpty()) {
            json.append("],\n");
        } else {
            json.deleteCharAt(json.length() - 1);
            json.append("\n\t\t\t\t\t]\n");
        }
        json.append("\t\t\t\t}");

        return json.toString();
    }
}

