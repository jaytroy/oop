package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

public class SaveMenu extends NavItem {
    public SaveMenu(String label, MainFrame frame) {
        super(label, frame);
    }

    public void createMenuItems() {
        JMenuItem saveGame = new JMenuItem("Save Game");
        add(saveGame);

        saveGame.addActionListener(e -> actions.saveGame(panel.getGraph()));
    }
}
