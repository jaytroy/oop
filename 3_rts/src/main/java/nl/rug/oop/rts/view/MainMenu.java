package nl.rug.oop.rts.view;

import javax.swing.*;

public class MainMenu {
    private final MainFrame mainFrame;

    public MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu nodeMenu = new JMenu("Node");
        JMenu edgeMenu = new JMenu("Edge");
        JMenu armyMenu = new JMenu("Army");
        JMenu eventMenu = new JMenu("Events");
        JMenu simulationMenu = new JMenu("Simulation");
        JMenu saveMenu = new JMenu("Save");

        MenuItems menuItems = new MenuItems(mainFrame);
        menuItems.createMenuItems(nodeMenu, edgeMenu, armyMenu, eventMenu, simulationMenu, saveMenu);

        menuBar.add(nodeMenu);
        menuBar.add(edgeMenu);
        menuBar.add(armyMenu);
        menuBar.add(eventMenu);
        menuBar.add(simulationMenu);
        menuBar.add(saveMenu);

        return menuBar;
    }
}
