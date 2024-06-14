package nl.rug.oop.rts.view.components.menus.navitems;

import nl.rug.oop.rts.controller.actions.ButtonActions;
import nl.rug.oop.rts.view.MainFrame;
import nl.rug.oop.rts.view.components.MainPanel;

import javax.swing.*;

/**
 * bstract class of generic navigation item.
 */
public abstract class NavItem extends JMenu {
    protected MainFrame frame;
    protected MainPanel panel;
    protected ButtonActions actions;

    /**
     * constructor of generic nav item.
     * @param label label fo the item.
     * @param frame frame that the item would be added.
     */
    public NavItem(String label, MainFrame frame) {
        super(label);
        this.frame = frame;
        this.panel = frame.getMainPanel();
        this.actions = panel.getButtonActions();
    }

    /**
     * Abstract method to create a menu tiem.
     */

    public abstract void createMenuItems();
}
