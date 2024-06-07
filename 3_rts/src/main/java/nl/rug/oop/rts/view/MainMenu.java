package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.controller.actions.ButtonActions;

import javax.swing.*;

@Getter
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

        MenuItems menuItems = new MenuItems(mainFrame);
        menuItems.createMenuItems(nodeMenu, edgeMenu, armyMenu, eventMenu, simulationMenu);

        menuBar.add(nodeMenu);
        menuBar.add(edgeMenu);
        menuBar.add(armyMenu);
        menuBar.add(eventMenu);
        menuBar.add(simulationMenu);

        return menuBar;
    }
}
