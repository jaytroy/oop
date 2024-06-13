package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

public class EventMenu extends NavItem {
    public EventMenu(String label, MainFrame frame) {
        super(label,frame);
    }

    @Override
    public void createMenuItems() {
        JMenuItem addEventItem = new JMenuItem("Add Event");
        JMenuItem removeEventItem = new JMenuItem("Remove Event");
        add(addEventItem);
        add(removeEventItem);

        addEventItem.addActionListener(e -> actions.addEventToSelectedElement(panel.getGraph(), frame));
        removeEventItem.addActionListener(e -> actions.removeEventFromSelectedElement(panel.getGraph(), frame));
    }
}
