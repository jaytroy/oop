package nl.rug.oop.rts.model.events;

import java.util.Random;

/**
 * This class handles random events.
 */
public class RandomEvent {
    /**
     * Get a random event.
     *
     * @return the selected event.
     */
    public static Event startRandomEvent(int nextid) {
        Random random = new Random();
        int eventType = random.nextInt(4);
        switch (eventType) {
            case 0:
                return new ReinforcementsEvent("Reinforcements event", nextid);
            case 1:
                return new NaturalDisasterEvent("Natural disaster event", nextid);
            case 2:
                return new HiddenWeaponryEvent("hidden weaponry event", nextid);
            case 3:
                return new TrainingMontage("training montage event", nextid);
            default:
                return null;
        }
    }
}

