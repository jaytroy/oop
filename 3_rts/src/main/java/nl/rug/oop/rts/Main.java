package nl.rug.oop.rts;

import com.formdev.flatlaf.FlatDarculaLaf;
import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

/**
 * Main class of the application. Add more details here.
 */
public class Main {

    /**
     * Main function. Add more details here.
     *
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}