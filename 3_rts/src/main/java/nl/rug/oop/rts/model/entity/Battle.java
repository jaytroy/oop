package nl.rug.oop.rts.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles the battles.
 */
public class Battle {

    /**
     * This handles the battle between the friendly armies and the non-friendly ones.
     *
     * @param armies = the list of armies that participate
     * @return the winning armies (friendly or non-friendly ones)
     */
    public static List<Army> battleStart(List<Army> armies) {
        Random random = new Random();
        List<Army> goodArmies = new ArrayList<>();
        List<Army> evilArmies = new ArrayList<>();

        for (Army army : armies) {
            if (army.getTeam()) {
                goodArmies.add(army);
            } else {
                evilArmies.add(army);
            }
        }
        while (!goodArmies.isEmpty() && !evilArmies.isEmpty()) {
            Army goodArmy = goodArmies.get(random.nextInt(goodArmies.size()));
            Army evilArmy = evilArmies.get(random.nextInt(evilArmies.size()));
            simulateBattle(goodArmy, evilArmy);
            if (goodArmy.getUnits().isEmpty()) {
                goodArmies.remove(goodArmy);
            }
            if (evilArmy.getUnits().isEmpty()) {
                evilArmies.remove(evilArmy);
            }
        }

        if (evilArmies.isEmpty()) {
            return goodArmies;
        } else {
            return evilArmies;
        }
    }

    private static void simulateBattle(Army goodArmy, Army evilArmy) {
        Random random = new Random();
        Unit goodUnit;
        Unit evilUnit;
        while (!goodArmy.getUnits().isEmpty() && !evilArmy.getUnits().isEmpty()) {
            goodUnit = goodArmy.getUnits().get(random.nextInt(goodArmy.getUnits().size()));
            evilUnit = evilArmy.getUnits().get(random.nextInt(evilArmy.getUnits().size()));
            if (goodUnit.getAccuracy() >= random.nextInt(101)) {
                goodUnit.setHealth(evilUnit.getHealth() - evilUnit.getDamage());
            }
            if (evilUnit.getAccuracy() >= random.nextInt(101)) {
                evilUnit.setHealth(evilUnit.getHealth() - goodUnit.getDamage());
            }

            if (evilUnit.getHealth() <= 0) {
                evilArmy.removeUnit(evilUnit);
            }
            if (evilUnit.getHealth() <= 0) {
                goodArmy.removeUnit(goodUnit);
            }
        }
    }

    /**
     * This function removes units that died in combat.
     *
     * @param armies the list of armies
     */
    public static void removeCasualties(List<Army> armies) {
        for (Army army : armies) {
            for (Unit unit : army.getUnits()) {
                if (unit.getHealth() <= 0) {
                    army.removeUnit(unit);
                }
            }
            if (army.getUnits().isEmpty()) {
                armies.remove(army);
            }
        }
    }
}
