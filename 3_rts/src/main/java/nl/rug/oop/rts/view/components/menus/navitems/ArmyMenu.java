package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

public class ArmyMenu extends NavItem {
    public ArmyMenu(String label, MainFrame frame) {
        super(label,frame);
    }

    @Override
    public void createMenuItems() {
        JMenuItem addArmyItem = new JMenuItem("Add Army");
        JMenuItem removeArmyItem = new JMenuItem("Remove Army");
        add(addArmyItem);
        add(removeArmyItem);

        addArmyItem.addActionListener(e -> actions.addArmyToSelectedNode(panel.getGraph(), frame));
        removeArmyItem.addActionListener(e -> actions.removeArmyFromSelectedNode(panel.getGraph(), frame));
    }
}
