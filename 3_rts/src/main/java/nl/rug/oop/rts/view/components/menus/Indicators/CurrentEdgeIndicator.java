package nl.rug.oop.rts.view.components.menus.Indicators;

import javax.swing.*;
import java.awt.*;

public class CurrentEdgeIndicator extends JPanel {
    private final JLabel selectedEdgeLabel;

    public CurrentEdgeIndicator() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBounds(10, 50, 300, 30);

        selectedEdgeLabel = new JLabel("Selected Edge: None");
        selectedEdgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedEdgeLabel.setForeground(Color.BLACK);

        add(selectedEdgeLabel, BorderLayout.CENTER);
    }

    public void updateSelectedEdgeLabel(String text) {
        selectedEdgeLabel.setText(text);
    }
}
