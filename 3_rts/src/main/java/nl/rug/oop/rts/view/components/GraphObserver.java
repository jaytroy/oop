package nl.rug.oop.rts.view.components;

import nl.rug.oop.rts.model.base.Graph;

/**
 * This interface handles the update of the observers.
 */
public interface GraphObserver {
    void update(Graph graph);
}
