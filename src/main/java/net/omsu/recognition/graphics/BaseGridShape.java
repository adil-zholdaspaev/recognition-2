package net.omsu.recognition.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

/**
 *
 */
public class BaseGridShape implements GridShape {

    private final Graphics2D graphics2D;
    private final int height;
    private final int width;

    private double x;
    private double y;

    public BaseGridShape(final Graphics2D graphics2D, final JComponent jComponent) {
        this.graphics2D = graphics2D;
        this.height = jComponent.getHeight();
        this.width = jComponent.getWidth();

        x = 0;
        y = 0;
    }

    public void moveTo(double x, double y) {
        GeneralPath path = new GeneralPath();
        path.moveTo(convertX(x), convertY(y));

        this.x = convertX(x);
        this.y = convertY(y);

        graphics2D.draw(path);
    }

    public void lineTo(double x, double y) {
        graphics2D.setColor(Color.BLACK);

        GeneralPath path = new GeneralPath();
        path.moveTo(this.x, this.y);
        path.lineTo(convertX(x), convertY(y));

        this.x = convertX(x);
        this.y = convertY(y);

        graphics2D.draw(path);
    }

    public void ellipse(double x, double y, double radius) {
        graphics2D.setColor(Color.BLUE);

        Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(convertX(x), convertY(y), radius, radius);

        graphics2D.draw(ellipse);
    }

    private double convertX(final double x) {
        return ((width / 2) + x * 15);
    }

    private double convertY(final double y) {
        return ((height / 2) - y * 15);
    }
}
