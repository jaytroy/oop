package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.controller.actions.ButtonActions;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.components.MainPanel;

import javax.swing.*;

public abstract class Menu extends JMenu {
    protected MainFrame frame;
    protected MainPanel panel;
    protected ButtonActions actions;

    public Menu(String label, MainFrame frame) {
        super(label);
        this.frame = frame;
        this.panel = frame.getMainPanel();
        this.actions = panel.getButtonActions();
    }

    public abstract void createMenuItems();
}
