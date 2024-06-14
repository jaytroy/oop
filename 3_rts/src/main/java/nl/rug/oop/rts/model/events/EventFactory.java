package nl.rug.oop.rts.model.events;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Factory class for constructing the specific desired event.
 */

public class EventFactory {
    private final Map<EventTypes, Supplier<Event>> eventConstructors = new HashMap<>();

    /**
     * Factory class getting the constructors ready.
     */
    public EventFactory() {
        eventConstructors.put(EventTypes.HIDDEN_WEAPONRY_EVENT, HiddenWeaponryEvent::new);
        eventConstructors.put(EventTypes.NATURAL_DISASTER_EVENT, NaturalDisasterEvent::new);
        eventConstructors.put(EventTypes.REINFORCEMENTS_EVENT, ReinforcementsEvent::new);
        eventConstructors.put(EventTypes.TRAINING_MONTAGE, TrainingMontage::new);
        eventConstructors.put(EventTypes.RANDOM_EVENT, RandomEvent::getRandomEvent);
    }

    /**
     * Method to create a specific event
     * @param type type of event to be created.
     * @return event.
     */
    public Event createEvent(EventTypes type) {
        if(eventConstructors.containsKey(type)) {
            return eventConstructors.get(type).get();
        } else {
            throw new IllegalArgumentException("Invalid event type: " + type);
        }
    }
}
