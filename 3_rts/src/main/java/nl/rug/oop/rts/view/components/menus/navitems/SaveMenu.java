package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;
/**
 * menu for the save game options.
 */

public class SaveMenu extends NavItem {
    public SaveMenu(String label, MainFrame frame) {
        super(label, frame);
    }

    /**
     * Creates the save game menu item.
     */

    public void createMenuItems() {
        JMenuItem saveGame = new JMenuItem("Save Game");
        add(saveGame);

        saveGame.addActionListener(e -> actions.saveGame(panel.getGraph()));
    }
}
