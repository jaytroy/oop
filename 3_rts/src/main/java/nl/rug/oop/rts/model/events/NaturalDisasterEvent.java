package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Battle;
import nl.rug.oop.rts.model.entity.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles one of the special events, where units form an army die because of natural disasters.
 */
public class NaturalDisasterEvent extends Event {
    private static int nextId = 0;
    private static final String DESC = "Natural Disaster";

    public NaturalDisasterEvent() {
        super(nextId, DESC);
        nextId++;
    }

    @Override
    public void startEvent(Army army) {
        List<Army> armies = new ArrayList<>();
        List<Unit> armylist = army.getUnits();
        Random random = new Random();
        int unitsToRemove = random.nextInt(11);
        unitsToRemove = Math.min(unitsToRemove, armylist.size());
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
    }
}
