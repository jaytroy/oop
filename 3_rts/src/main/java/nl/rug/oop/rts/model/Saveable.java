package nl.rug.oop.rts.model;

/**
 * Marks an interface as saveable.
 */
public interface Saveable {
    /**
     * Creates a saveable JSON string.
     * @return The JSON string.
     */
    String saveJson();
}
