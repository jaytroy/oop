package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Unit;

import java.util.List;

/**
 * This class handles a special event, when an army gets a random number of bonus units.
 */
public class ReinforcementsEvent extends Event {

    public ReinforcementsEvent(int nextid, String description) {
        super(nextid, "Reinforcements");
    }

    @Override
    public Army startEvent(Army army) {
        List<Unit> reinforcements = army.createRandomUnits(army.getFaction());
        army.getUnits().addAll(reinforcements);
        return army;
    }
}

