package nl.rug.oop.rts.view.components.menus;

import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.components.menus.navitems.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NavBar {
    private final MainFrame frame;

    public NavBar(MainFrame mainFrame) {
        this.frame = mainFrame;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        List<Menu> menus = new ArrayList<>();

        menus.add(new NodeMenu("Node", frame));
        menus.add(new EdgeMenu("Edge", frame));
        menus.add(new ArmyMenu("Army", frame));
        menus.add(new EventMenu("Events", frame));
        menus.add(new SimMenu("Simulation", frame));
        menus.add(new SaveMenu("Save", frame));

        for(Menu menu : menus) {
            menu.createMenuItems();
            menuBar.add(menu);
        }

        return menuBar;
    }
}
