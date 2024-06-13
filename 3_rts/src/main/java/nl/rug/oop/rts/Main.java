package nl.rug.oop.rts;

import com.formdev.flatlaf.FlatDarculaLaf;
import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;

/**
 * Main class of the application.
 */
public class Main {

    /**
     * Main function. Starts the game.
     *
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {
        System.out.println("Starting");
        FlatDarculaLaf.setup();
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}