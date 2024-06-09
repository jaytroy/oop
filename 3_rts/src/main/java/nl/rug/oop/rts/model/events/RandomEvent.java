package nl.rug.oop.rts.model.events;

import java.util.Random;

/**
 * This class handles random events.
 */
public class RandomEvent {
    private static final EventFactory eventFactory = new EventFactory();
    private static final EventTypes[] eventTypes = EventTypes.values();
    private static final Random random = new Random();


    /**
     * Get a random event.
     *
     * @return the selected event.
     */
    public static Event getRandomEvent() {
        int eventTypeIndex = random.nextInt(eventTypes.length);
        EventTypes selectedType = eventTypes[eventTypeIndex];
        return eventFactory.createEvent(selectedType);
    }
}
