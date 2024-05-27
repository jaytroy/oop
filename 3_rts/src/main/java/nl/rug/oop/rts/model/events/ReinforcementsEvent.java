package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Unit;

import java.util.List;

/**
 * This class handles a special event, when an army gets a random number of bonus units.
 */
public class ReinforcementsEvent extends Event {
    public ReinforcementsEvent(String description, int nextid) {
        super(description, nextid);
    }

    @Override
    public void startEvent(Army army) {
        List<Unit> reinforcements = army.createRandomUnits(army.getFaction());
        army.getUnits().addAll(reinforcements);
    }
}
