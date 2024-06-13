package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

public class SimMenu extends Menu {
    public SimMenu(String label, MainFrame frame) {
        super(label, frame);
    }

    public void createMenuItems() {
        JMenuItem sim1step = new JMenuItem("Simulate 1 step");
        add(sim1step);

        sim1step.addActionListener(e -> actions.simulation1Step(panel.getGraph()));
    }
}
