package nl.rug.oop.rts.view.components.menus.whatisthis;

import javax.swing.*;
import java.awt.*;

public class NodeMenu2 extends JPanel {
    private final JLabel selectedNodeLabel;

    public NodeMenu2() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBounds(10, 10, 300, 30);

        selectedNodeLabel = new JLabel("Selected Node: None");
        selectedNodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedNodeLabel.setForeground(Color.BLACK);

        add(selectedNodeLabel, BorderLayout.CENTER);
    }

    public void updateSelectedNodeLabel(String text) {
        selectedNodeLabel.setText(text);
    }
}
