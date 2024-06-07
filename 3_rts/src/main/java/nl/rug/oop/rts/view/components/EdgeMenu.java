package nl.rug.oop.rts.view.components;

import javax.swing.*;
import java.awt.*;

/**
 * CURRENTLY UNUSED
 */
public class EdgeMenu extends JPanel {
    private final JButton removeEdgeButton;
    private final JButton addEdgeButton;
    private JLabel selectedEdgeLabel;

    public EdgeMenu() {
        setBounds(10, 50, 300, 30);
        setBackground(Color.WHITE);

        selectedEdgeLabel = new JLabel("Selected Edge: None");
        selectedEdgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedEdgeLabel.setForeground(Color.BLACK);

        addEdgeButton = new JButton("Add Edge");
        removeEdgeButton = new JButton("Remove Edge");
        add(addEdgeButton);
        add(removeEdgeButton);
    }
}
