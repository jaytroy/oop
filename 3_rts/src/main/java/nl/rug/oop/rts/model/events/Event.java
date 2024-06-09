package nl.rug.oop.rts.model.events;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.Saveable;
import nl.rug.oop.rts.model.entity.Army;

@Setter
@Getter
public abstract class Event implements Saveable {
    private String description;
    private int id;

    public Event(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public abstract void startEvent(Army army);

    public String toStringWithID() {
        return String.format("%s - Event ID: %d", getDescription(), getId());
    }

    @Override
    public String saveJson() {
        StringBuilder json = new StringBuilder();
        json.append("\n\t\t\t\t{\n");
        json.append("\t\t\t\t\t\"description\": \"").append(description).append("\",\n");
        json.append("\t\t\t\t\t\"id\": ").append(id).append("\n");
        json.append("\t\t\t\t}");
        return json.toString();
    }
}
