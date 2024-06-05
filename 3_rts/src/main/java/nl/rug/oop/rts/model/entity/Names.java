package nl.rug.oop.rts.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles the names for every type of faction.
 */
public class Names {

    /**
     * This generates the name for a specific unit in a faction.
     * @param faction given faction
     * @return the name
     */
    public static String getUnitNames(Faction faction) {
        int numOfNames = 3;
        Random random = new Random();
        String unitName = null;
        switch (faction) {
            case MEN:
                unitName = caseMen(random, numOfNames);
                break;
            case ELVES:
                unitName = caseElves(random, numOfNames);
                break;
            case DWARVES:
                unitName = caseDwarves(random, numOfNames);
                break;
            case MORDOR:
                unitName = caseMordor(random, numOfNames);
                break;
            case ISENGARD:
                unitName = caseIsengard(random, numOfNames);
                break;
        }
        return unitName;
    }

    /**
     * The case for faction MEN.
     * @param random a random next name
     * @param numOfNames number of names
     * @return the name we need
     */
    public static String caseMen(Random random, int numOfNames) {
        List<String> unitNames = new ArrayList<>();
        unitNames.add("Gondor Soldier");
        unitNames.add("Tower Guard");
        unitNames.add("Ithilien Ranger");
        int index = random.nextInt(numOfNames);
        return unitNames.get(index);
    }

    /**
     * The case for faction ELVES.
     * @param random a random next name
     * @param numOfNames number of names
     * @return the name we need
     */
    public static String caseElves(Random random, int numOfNames) {
        List<String> unitNames = new ArrayList<>();
        unitNames.add("Lorien Warrior");
        unitNames.add("Mirkwood Archer");
        unitNames.add("Rivendell Lancer");
        int index = random.nextInt(numOfNames);
        return unitNames.get(index);
    }

    /**
     * The case for faction DWARVES.
     * @param random a random next name
     * @param numOfNames number of names
     * @return the name we need
     */
    public static String caseDwarves(Random random, int numOfNames) {
        List<String> unitNames = new ArrayList<>();
        unitNames.add("Guardian");
        unitNames.add("Phalanx");
        unitNames.add("Axe Thrower");
        int index = random.nextInt(numOfNames);
        return unitNames.get(index);
    }

    /**
     * The case for faction MORDOR.
     * @param random a random next name
     * @param numOfNames number of names
     * @return the name we need
     */
    public static String caseMordor(Random random, int numOfNames) {
        List<String> unitNames = new ArrayList<>();
        unitNames.add("Orc Warrior");
        unitNames.add("Orc Pikeman");
        unitNames.add("Haradrim Archer");
        int index = random.nextInt(numOfNames);
        return unitNames.get(index);
    }

    /**
     * The case for faction ISENGARD.
     * @param random a random next name
     * @param numOfNames number of names
     * @return the name we need
     */
    public static String caseIsengard(Random random, int numOfNames) {
        List<String> unitNames = new ArrayList<>();
        unitNames.add("Uruk-hai");
        unitNames.add("Uruk Crossbowman");
        unitNames.add("Warg Rider");
        int index = random.nextInt(numOfNames);
        return unitNames.get(index);
    }
}