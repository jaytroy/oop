package nl.rug.oop.rts.model.base;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.Saveable;
import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the common attributes of base game elements.
 */
@Getter
@Setter
public abstract class GameElement implements Saveable {
    private int id;
    private String name;
    private List<Army> armies;
    private List<Event> events;

    /**
     * abstract constructor of generic game element.
     * @param id game element id.
     * @param name game element name.
     */

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

    protected String saveExtraFields() {
        return "";
    }

    @Override
    public String saveJson() {
        StringBuilder json = new StringBuilder();

        json.append("\t\t{\n");

        json.append("\t\t\t\"Id\": ").append(id).append(",\n");
        json.append("\t\t\t\"Name\": \"").append(name).append("\",\n");

        json.append(saveExtraFields());

        json.append("\t\t\t\"Armies\": [");
        for (Army army : armies) {
            json.append(army.saveJson()).append(",\n");
        }
        if(armies.isEmpty()) {
            json.append("],\n");
        } else {
            json.deleteCharAt(json.length() - 2);
            json.append("\t\t\t],\n");
        }

        json.append("\t\t\t\"Events\": [");
        for (Event event : events) {
            json.append(event.saveJson()).append(",");
        }
        if(events.isEmpty()) {
            json.append("]\n");
        } else {
            json.deleteCharAt(json.length() - 1);
            json.append("\n\t\t\t]\n");
        }
        json.append("\t\t}");

        return json.toString();
    }
}
