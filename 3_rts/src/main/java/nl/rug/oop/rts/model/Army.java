package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles the details of armies.
 */
public class Army {
    @Getter
    private List<Unit> units;
    @Getter
    private Faction faction;
    @Getter
    private Boolean team;
    @Getter
    private int id;
    @Getter
    @Setter
    private Node comingFrom;

    /**
     * This is the constructor of the army.
     *
     * @param id      the id of the army
     * @param faction its faction
     */
    public Army(int id, Faction faction) {
        this.units = createRandomUnits(faction);
        this.faction = faction;
        this.team = Faction.getTeam(faction);
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
        List<String> unitNames = new ArrayList<>();

        Random random = new Random();
        int numUnits = random.nextInt(41) + 10;
        for (int i = 0; i < numUnits; i++) {
            String randomUnitName = Names.getUnitNames(faction);
            int randDamage = random.nextInt(10) + 1;
            int randHealth = random.nextInt(101) + 50;
            randomUnits.add(new Unit(randomUnitName, randDamage, randHealth));
        }
        return randomUnits;
    }
}

