package nl.rug.oop.rts.model.events;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EventFactory {
    //Map to store the constructors for each event type.
    //The function here returns a new event with a given Integer id (nextId).
    private final Map<EventTypes, Function<Integer,Event>> eventConstructors = new HashMap<>();

    public EventFactory() {
        //Add the event constructors to the map
        eventConstructors.put(EventTypes.HiddenWeaponryEvent, HiddenWeaponryEvent::new);
        eventConstructors.put(EventTypes.NaturalDisasterEvent, NaturalDisasterEvent::new);
        eventConstructors.put(EventTypes.ReinforcementsEvent, ReinforcementsEvent::new);
        eventConstructors.put(EventTypes.TrainingMontage, TrainingMontage::new);
        eventConstructors.put(EventTypes.RandomEvent, RandomEvent::getRandomEvent);
    }

    public Event createEvent(EventTypes type, int nextEventId) {
        if(eventConstructors.containsKey(type)) {
            return eventConstructors.get(type).apply(nextEventId);
        } else {
            throw new IllegalArgumentException("Invalid event type: " + type);
        }
    }
}
