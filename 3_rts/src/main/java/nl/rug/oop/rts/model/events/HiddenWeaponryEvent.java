package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Unit;

import java.util.List;

/**
 * This represents one of the special events, where units get a boost of damage.
 */
public class HiddenWeaponryEvent extends Event {
    public HiddenWeaponryEvent(int nextid, String description) {
        super(nextid, "Hidden Weaponry Event: Units receive a boost of damage");
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
