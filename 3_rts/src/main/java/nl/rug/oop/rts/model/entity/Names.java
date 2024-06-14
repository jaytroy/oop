package nl.rug.oop.rts.model.entity;

import java.util.*;

/**
 * This class handles the names for every type of faction.
 */
public class Names {
    private final Map<Faction, List<String>> factionNames;

    /**
     * Names for the units of each faction.
     */
    public Names() {
        factionNames = new EnumMap<>(Faction.class);
        factionNames.put(Faction.MEN, Arrays.asList("Gondor Soldier", "Tower Guard", "Ithilien Ranger"));
        factionNames.put(Faction.ELVES, Arrays.asList("Lorien Warrior", "Mirkwood Archer", "Rivendell Lancer"));
        factionNames.put(Faction.DWARVES, Arrays.asList("Guardian", "Phalanx", "Axe Thrower"));
        factionNames.put(Faction.MORDOR, Arrays.asList("Orc Warrior", "Orc Pikeman", "Haradrim Archer"));
        factionNames.put(Faction.ISENGARD, Arrays.asList("Uruk-hai", "Uruk Crossbowman", "Warg Rider"));
    }

    /**
     * getter for a factions units names.
     * @param faction function whose names are requested.
     * @return returns a random unit name from the lsit.
     */
    public String getUnitNames(Faction faction) {
        Random random = new Random();
        List<String> unitNames = factionNames.get(faction);
        int index = random.nextInt(unitNames.size());
        return unitNames.get(index);
    }
}