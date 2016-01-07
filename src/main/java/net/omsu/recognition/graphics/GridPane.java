package net.omsu.recognition.graphics;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class GridPane extends JPanel {

    public GridPane() {

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g2d.dispose();
    }
}
