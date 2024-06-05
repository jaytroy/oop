package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.Panel;

public interface Action {
    void addAction(Graph graph, Panel panel, MainFrame mainFrame);
    void removeAction(Graph graph, Panel panel);
}
