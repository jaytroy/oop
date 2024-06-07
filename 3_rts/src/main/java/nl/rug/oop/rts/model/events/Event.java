package nl.rug.oop.rts.model.events;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.entity.Army;

@Setter
@Getter
public abstract class Event {
    private String description;
    private int id;

    public Event(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Army startEvent(Army army) {
        return army;
    }

    public String toStringWithID() {
        return String.format("%s - Event ID: %d", getDescription(), getId());
    }
}
