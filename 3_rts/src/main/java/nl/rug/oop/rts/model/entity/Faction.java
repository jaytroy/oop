package nl.rug.oop.rts.model.entity;

/**
 * This represets the types of factions we have in the simulator.
 */
public enum Faction {
    MEN,
    ELVES,
    DWARVES,
    MORDOR,
    ISENGARD;

    public static Boolean getTeam(Faction faction) {
        if (faction == Faction.MEN || faction == Faction.DWARVES || faction == Faction.ELVES) {
           return true;
        } else {
            return false;
        }
    }
}


