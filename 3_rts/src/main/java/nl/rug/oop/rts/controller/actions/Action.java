package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.components.GraphPanel;

public interface Action {
    void addAction(Graph graph, GraphPanel panel, MainFrame mainFrame);
    void removeAction(Graph graph, GraphPanel panel);
}
