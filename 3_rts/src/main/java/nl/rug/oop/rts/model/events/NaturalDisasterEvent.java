package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Battle;
import nl.rug.oop.rts.model.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles one of the special events, where units form an army die because of natural disasters.
 */
public class NaturalDisasterEvent extends Event {

    public NaturalDisasterEvent(int nextid, String description) {
        super(nextid, "Natural disaster: Units ide");
    }

    @Override
    public Army startEvent(Army army) {
        List<Army> armies = new ArrayList<>();
        List<Unit> armylist = army.getUnits();
        Random random = new Random();
        int unitsToRemove = random.nextInt(11);
        for (int i = 0; i <= unitsToRemove; i++) {
            if (armylist.get(i) != null) {
                armylist.remove(0);
            } else {
                break;
            }
        }
        armies.add(army);
        Battle.removeCasualties(armies);
        System.out.println("EVENT HAPPENS");
        return army;
    }
}
