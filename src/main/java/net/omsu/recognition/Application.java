package net.omsu.recognition;

import net.omsu.recognition.graphics.GridPane;
import net.omsu.recognition.mnk.MathMethod;
import net.omsu.recognition.mnk.distribution.UniformDistribution;
import net.omsu.recognition.mnk.functions.SinFunction;

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

                MathMethod mathMethod = new MathMethod(new SinFunction(), new UniformDistribution(10), new UniformDistribution(1));
                GridPane gridPane = new GridPane(mathMethod);
                gridPane.setPreferredSize(new Dimension(500, 500));

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(gridPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
