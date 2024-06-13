package nl.rug.oop.rts.view.components.Indicators;

import javax.swing.*;
import java.awt.*;

/**
 * Indicates which element is currently selected.
 */
public class CurrentElementIndicator extends JPanel {
    private final JLabel selectedElementLabel;

    /**
     * Constructor for the current element indicator.
     */
    public CurrentElementIndicator() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBounds(10, 10, 300, 30);

        selectedElementLabel = new JLabel("Selected Element: None");
        selectedElementLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedElementLabel.setForeground(Color.BLACK);

        add(selectedElementLabel, BorderLayout.CENTER);
    }

    /**
     * Updates the label of the selected element.
     * @param text The text to update the label with.
     */
    public void updateSelectedElementLabel(String text) {
        selectedElementLabel.setText(text);
    }
}
