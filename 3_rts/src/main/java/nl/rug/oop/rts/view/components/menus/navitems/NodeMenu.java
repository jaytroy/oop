package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

/**
 * Menu for the possible ndoe actions.
 */
public class NodeMenu extends NavItem {
    public NodeMenu(String label, MainFrame frame) {
        super(label,frame);
    }

    @Override
    public void createMenuItems() {
        JMenuItem addNodeItem = new JMenuItem("Add Node");
        JMenuItem removeNodeItem = new JMenuItem("Remove Node");
        JMenuItem renameNodeItem = new JMenuItem("Rename Node");
        add(addNodeItem);
        add(removeNodeItem);
        add(renameNodeItem);

        addNodeItem.addActionListener(e -> actions.addNode(panel.getGraph(), frame));
        removeNodeItem.addActionListener(e -> actions.removeNode(panel.getGraph()));
        renameNodeItem.addActionListener(e -> actions.renameNode(panel.getGraph(), frame));
    }
}
