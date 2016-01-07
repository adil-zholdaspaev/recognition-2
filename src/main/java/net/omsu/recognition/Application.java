package net.omsu.recognition;

import net.omsu.recognition.graphics.GridPane;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 *
 */
public class Application {

    public static void main(String[] args) {
        new Application();
    }

    public Application() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                GridPane gridPane = new GridPane();
                gridPane.setPreferredSize(new Dimension(200, 200));

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(gridPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public interface GridShape {
        public void draw(Graphics2D g2d, JComponent parent);
    }

    public class WaveShape implements GridShape {

        public void draw(Graphics2D g2d, JComponent parent) {
            g2d.setColor(Color.RED);

            int xDiff = parent.getWidth() / 4;
            int height = parent.getHeight() - 1;

            int xPos = 0;

            GeneralPath path = new GeneralPath();
            path.moveTo(0, 0);

            path.curveTo(xPos + xDiff, 0, xPos, height, xPos + xDiff, height);
            xPos += xDiff;
            path.curveTo(xPos + xDiff, height, xPos, 0, xPos + xDiff, 0);
            xPos += xDiff;
            path.curveTo(xPos + xDiff, 0, xPos, height, xPos + xDiff, height);
            xPos += xDiff;
            path.curveTo(xPos + xDiff, height, xPos, 0, xPos + xDiff, 0);
            g2d.draw(path);
        }

    }

}
