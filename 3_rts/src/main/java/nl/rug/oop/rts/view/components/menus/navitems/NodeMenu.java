package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

public class NodeMenu extends Menu {
    public NodeMenu(String label, MainFrame frame) {
        super(label,frame);
    }

    @Override
    public void createMenuItems() {
        JMenuItem addNodeItem = new JMenuItem("Add Node");
        JMenuItem removeNodeItem = new JMenuItem("Remove Node");
        add(addNodeItem);
        add(removeNodeItem);

        addNodeItem.addActionListener(e -> actions.addNode(panel.getGraph(), panel.getGraphPanel(), frame));
        removeNodeItem.addActionListener(e -> actions.removeNode(panel.getGraph(), panel.getGraphPanel()));
    }
}
