package nl.rug.oop.rts.model.base;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the common attributes of base game elements.
 */
@Getter
@Setter
public abstract class GameElement {
    private int id;
    private String name;
    private List<Army> armies;
    private List<Event> events;

    public GameElement(int id, String name) {
        this.id = id;
        this.name = name;
        this.armies = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public void addArmy(Army army) {
        armies.add(army);
    }

    public void removeArmy(Army givenArmy) {
        armies.removeIf(army-> army.getId() == givenArmy.getId());
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event givenEvent) {
        events.removeIf(event -> event.getId() == givenEvent.getId());
    }
}
