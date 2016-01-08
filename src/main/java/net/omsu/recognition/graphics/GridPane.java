package net.omsu.recognition.graphics;

import net.omsu.recognition.mnk.MathMethod;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class GridPane extends JPanel {

    private final MathMethod mathMethod;

    public GridPane(final MathMethod mathMethod) {
        this.mathMethod = mathMethod;
        this.setPreferredSize(new Dimension(600, 600));
    }

    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics.create();
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g2d.dispose();

        g2d = (Graphics2D) graphics.create();
        GridShape gridShape = new BaseGridShape(g2d, this);

        mathMethod.method(gridShape);
        mathMethod.solveLinearEquations(gridShape);

        g2d.dispose();
    }
}
