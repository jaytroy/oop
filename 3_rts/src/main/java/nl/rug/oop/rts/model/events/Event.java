package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;

/**
 * This function handles the events.
 */
public abstract class Event {

    private String description;
    private int id;

    public Event(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void startEvent(Army army) {
    }

    public int getId() {
        return id;
    }
}

