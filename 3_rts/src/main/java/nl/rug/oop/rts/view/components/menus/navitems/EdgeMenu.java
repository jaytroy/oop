package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

public class EdgeMenu extends Menu {
    public EdgeMenu(String label, MainFrame frame) {
        super(label,frame);
    }

    @Override
    public void createMenuItems() {
        JMenuItem addEdgeItem = new JMenuItem("Add Edge");
        JMenuItem removeEdgeItem = new JMenuItem("Remove Edge");
        JMenuItem renameEdgeItem = new JMenuItem("Rename Edge");
        add(addEdgeItem);
        add(removeEdgeItem);
        add(renameEdgeItem);

        addEdgeItem.addActionListener(e -> actions.addEdge(panel.getGraph(), panel.getGraphPanel(), frame));
        removeEdgeItem.addActionListener(e -> actions.removeEdge(panel.getGraph(), panel.getGraphPanel()));
        renameEdgeItem.addActionListener(e -> actions.renameEdge(panel.getGraph(), panel.getGraphPanel(), frame));
    }
}
