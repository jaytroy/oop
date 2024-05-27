package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Unit;

import java.util.List;

/**
 * This class handles a special event, where units in an army get a boost of accuracy.
 */
public class TrainingMontage extends Event {
    public TrainingMontage(String description, int nextid) {
        super(description, nextid);
    }

    @Override
    public void startEvent(Army army) {
        List<Unit> units = army.getUnits();
        for(Unit unit : units){
            unit.setAccuracy(unit.getAccuracy() + 5);
        }
    }

}
