package nl.rug.oop.rts.model.entity;

/**
 * This represents the types of factions we have in the simulator.
 */
public enum Faction {
    MEN(true),
    ELVES(true),
    DWARVES(true),
    MORDOR(true),
    ISENGARD(true);

    private final boolean team;

    Faction(boolean team) {
        this.team = team;
    }

    public boolean getTeam() {
        return team;
    }
}


