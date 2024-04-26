package nl.rug.oop.rpg.model;

public class Room implements Inspectable {
    private final String description;

    public Room(String description) {
        this.description = description;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }
}
