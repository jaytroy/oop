package nl.rug.oop.rts.model.events;

import java.util.Random;

/**
 * This class handles random events.
 */
public class RandomEvent {
    private static final EventFactory EVENT_FACTORY = new EventFactory();
    private static final EventTypes[] EVENT_TYPES = EventTypes.values();
    private static final Random RANDOM_GENERATOR = new Random();

    /**
     * Get a random event.
     *
     * @return the selected event.
     */
    public static Event getRandomEvent() {
        int eventTypeIndex = RANDOM_GENERATOR.nextInt(EVENT_TYPES.length);
        EventTypes selectedType = EVENT_TYPES[eventTypeIndex];
        return EVENT_FACTORY.createEvent(selectedType);
    }
}
