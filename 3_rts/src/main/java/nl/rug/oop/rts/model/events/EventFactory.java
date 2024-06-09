package nl.rug.oop.rts.model.events;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EventFactory {
    //Map to store the constructors for each event type.
    //Supplier takes no arguments and returns a result, the result being a new instance of a class.
    private final Map<EventTypes, Supplier<Event>> eventConstructors = new HashMap<>();

    public EventFactory() {
        //Add the event constructors to the map
        eventConstructors.put(EventTypes.HiddenWeaponryEvent, HiddenWeaponryEvent::new);
        eventConstructors.put(EventTypes.NaturalDisasterEvent, NaturalDisasterEvent::new);
        eventConstructors.put(EventTypes.ReinforcementsEvent, ReinforcementsEvent::new);
        eventConstructors.put(EventTypes.TrainingMontage, TrainingMontage::new);
        eventConstructors.put(EventTypes.RandomEvent, RandomEvent::getRandomEvent);
    }

    public Event createEvent(EventTypes type) {
        if(eventConstructors.containsKey(type)) {
            return eventConstructors.get(type).get();
        } else {
            throw new IllegalArgumentException("Invalid event type: " + type);
        }
    }
}
