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
        eventConstructors.put(EventTypes.HIDDEN_WEAPONRY_EVENT, HiddenWeaponryEvent::new);
        eventConstructors.put(EventTypes.NATURAL_DISASTER_EVENT, NaturalDisasterEvent::new);
        eventConstructors.put(EventTypes.REINFORCEMENTS_EVENT, ReinforcementsEvent::new);
        eventConstructors.put(EventTypes.TRAINING_MONTAGE, TrainingMontage::new);
        eventConstructors.put(EventTypes.RANDOM_EVENT, RandomEvent::getRandomEvent);
    }

    public Event createEvent(EventTypes type) {
        if(eventConstructors.containsKey(type)) {
            return eventConstructors.get(type).get();
        } else {
            throw new IllegalArgumentException("Invalid event type: " + type);
        }
    }
}
