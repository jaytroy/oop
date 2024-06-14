package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.controller.actions.ButtonActions;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.components.MainPanel;

import javax.swing.*;

/**
 * Abstract class of generic navigation item.
 */
public abstract class NavItem extends JMenu {
    /**
     * The main frame associated with this navigation item.
     */
    protected MainFrame frame;

    /**
     * The main panel associated with the main frame.
     */
    protected MainPanel panel;

    /**
     * The button actions associated with the main panel.
     */
    protected ButtonActions actions;

    /**
     * Constructor for a generic navigation item.
     *
     * @param label the label for the item.
     * @param frame the main frame that the item will be added to.
     */
    public NavItem(String label, MainFrame frame) {
        super(label);
        this.frame = frame;
        this.panel = frame.getMainPanel();
        this.actions = panel.getButtonActions();
    }

    /**
     * Abstract method to create menu items.
     */
    public abstract void createMenuItems();
}
