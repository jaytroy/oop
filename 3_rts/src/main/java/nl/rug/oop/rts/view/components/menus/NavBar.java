package nl.rug.oop.rts.view.components.menus;

import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.components.menus.navitems.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Navigation bar class.
 */

public class NavBar {
    private final MainFrame frame;

    /**
     * navigation abr constructor.
     * @param mainFrame Mianframe that the abr will be added to.
     */
    public NavBar(MainFrame mainFrame) {
        this.frame = mainFrame;
    }

    /**
     * Creates the standard menu bar.
     * @return menubar.
     */
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        List<NavItem> menus = new ArrayList<>();

        menus.add(new NodeMenu("Node", frame));
        menus.add(new EdgeMenu("Edge", frame));
        menus.add(new ArmyMenu("Army", frame));
        menus.add(new EventMenu("Events", frame));
        menus.add(new SimMenu("Simulation", frame));
        menus.add(new SaveMenu("Save", frame));

        for(NavItem menu : menus) {
            menu.createMenuItems();
            menuBar.add(menu);
        }

        return menuBar;
    }
}
