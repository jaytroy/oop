package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Unit;

import java.util.List;

/**
 * This represents one of the special events, where units get a boost of damage.
 */
public class HiddenWeaponryEvent extends Event {
    public HiddenWeaponryEvent(String description, int nextid) {
        super(description, nextid);
    }

    @Override
    public void startEvent(Army army) {
        List<Unit> units = army.getUnits();
        for (Unit unit : units) {
            unit.setDamage(unit.getDamage() + 5);
        }
    }

}
