package nl.rug.oop.rts.model.events;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.entity.Army;

public abstract class Event {
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private int id;

    public Event(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Army startEvent(Army army) {
        return army;
    }

    public int getId() {
        return id;
    }

    public String toStringWithID() {
        return String.format("%s - Event ID: %d", getDescription(), getId());
    }
}
