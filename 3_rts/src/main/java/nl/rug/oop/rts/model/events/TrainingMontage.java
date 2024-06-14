package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Unit;

import java.util.List;

/**
 * This class handles a special event, where units in an army get a boost of accuracy.
 */
public class TrainingMontage extends Event {
    private static int nextId = 0;
    private static final String DESC = "Training Montage";

    public TrainingMontage() {
        super(nextId, DESC);
        nextId++;
    }

    @Override
    public void startEvent(Army army) {
        List<Unit> units = army.getUnits();
        for (Unit unit : units) {
            unit.setAccuracy(unit.getAccuracy() + 5);
        }
    }
}
