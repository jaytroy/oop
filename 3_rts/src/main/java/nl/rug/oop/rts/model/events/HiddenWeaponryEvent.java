package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Unit;

import java.util.List;

/**
 * This represents one of the special events, where units get a boost of damage.
 */
public class HiddenWeaponryEvent extends Event {
    private static int nextId = 0;
    private static final String DESC = "Hidden Weaponry";

    public HiddenWeaponryEvent() {
        super(nextId, DESC);
        nextId++;
    }

    @Override
    public void startEvent(Army army) {
        List<Unit> units = army.getUnits();
        for (Unit unit : units) {
            unit.setDamage(unit.getDamage() + 5);
        }
    }
}
