package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Unit;

import java.util.List;

/**
 * This class handles a special event, where units in an army get a boost of accuracy.
 */
public class TrainingMontage extends Event {
    private final static String DESC = "Training Montage";

    public TrainingMontage(int nextid) {
        super(nextid, DESC);
    }

    @Override
    public Army startEvent(Army army) {
        List<Unit> units = army.getUnits();
        for (Unit unit : units) {
            unit.setAccuracy(unit.getAccuracy() + 5);
        }
        return army;
    }
}
