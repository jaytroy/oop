package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Unit;

import java.util.List;

/**
 * This represents one of the special events, where units get a boost of damage.
 */
public class HiddenWeaponryEvent extends Event {
    private final static String DESC = "Hidden Weaponry";

    public HiddenWeaponryEvent(int nextid) {
        super(nextid, DESC);
    }

    @Override
    public Army startEvent(Army army) {
        List<Unit> units = army.getUnits();
        for (Unit unit : units) {
            unit.setDamage(unit.getDamage() + 5);
        }
        return army;
    }
}
