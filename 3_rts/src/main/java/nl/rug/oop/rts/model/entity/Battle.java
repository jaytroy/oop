package nl.rug.oop.rts.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles the battles.
 */
public class Battle {

    /**
     * Handles the battle between friendly and non-friendly armies.
     *
     * @param armies the list of armies that participate
     * @return the winning armies (friendly or non-friendly)
     */
    public static List<Army> battleStart(List<Army> armies) {
        Random random = new Random();
        List<Army> goodArmies = new ArrayList<>();
        List<Army> evilArmies = new ArrayList<>();

        splitArmies(armies, goodArmies, evilArmies);

        while (!goodArmies.isEmpty() && !evilArmies.isEmpty()) {
            Army goodArmy = goodArmies.get(random.nextInt(goodArmies.size()));
            Army evilArmy = evilArmies.get(random.nextInt(evilArmies.size()));

            int goodCasualties = simulateBattle(goodArmy, evilArmy);
            int evilCasualties = simulateBattle(evilArmy, goodArmy);

            printBattleResult(goodArmy, evilArmy, goodCasualties, evilCasualties);

            removeEmptyArmies(goodArmies);
            removeEmptyArmies(evilArmies);
        }

        return goodArmies.isEmpty() ? evilArmies : goodArmies;
    }

    private static void splitArmies(List<Army> armies, List<Army> goodArmies, List<Army> evilArmies) {
        for (Army army : armies) {
            if (army.getTeam()) {
                goodArmies.add(army);
            } else {
                evilArmies.add(army);
            }
        }
    }

    private static int simulateBattle(Army attackingArmy, Army defendingArmy) {
        Random random = new Random();
        int casualties = 0;

        while (!attackingArmy.getUnits().isEmpty() && !defendingArmy.getUnits().isEmpty()) {
            Unit attackingUnit = attackingArmy.getUnits().get(random.nextInt(attackingArmy.getUnits().size()));
            Unit defendingUnit = defendingArmy.getUnits().get(random.nextInt(defendingArmy.getUnits().size()));

            if (attackingUnit.getAccuracy() >= random.nextInt(101)) {
                defendingUnit.setHealth(defendingUnit.getHealth() - attackingUnit.getDamage());
            }

            if (defendingUnit.getAccuracy() >= random.nextInt(101)) {
                attackingUnit.setHealth(attackingUnit.getHealth() - defendingUnit.getDamage());
            }

            casualties += checkUnitCasualty(defendingArmy, defendingUnit);
            casualties += checkUnitCasualty(attackingArmy, attackingUnit);
        }

        return casualties;
    }

    private static int checkUnitCasualty(Army army, Unit unit) {
        if (unit.getHealth() <= 0) {
            army.removeUnit(unit);
            return 1;
        }
        return 0;
    }

    private static void printBattleResult(Army goodArmy, Army evilArmy, int goodCasualties, int evilCasualties) {
        System.out.println("Battle: " + goodArmy.getFaction() + " " + goodArmy.getNumUnits() +
                " vs " + evilArmy.getFaction() + " " + evilArmy.getNumUnits());
        System.out.println(goodArmy.getFaction() + " casualties: " + goodCasualties);
        System.out.println(evilArmy.getFaction() + " casualties: " + evilCasualties);
    }

    private static void removeEmptyArmies(List<Army> armies) {
        armies.removeIf(army -> army.getUnits().isEmpty());
    }

    /**
     * Removes units that died in combat from each army.
     *
     * @param armies the list of armies
     */
    public static void removeCasualties(List<Army> armies) {
        armies.forEach(army -> army.getUnits().removeIf(unit -> unit.getHealth() <= 0));
        armies.removeIf(army -> army.getUnits().isEmpty());
    }
}
