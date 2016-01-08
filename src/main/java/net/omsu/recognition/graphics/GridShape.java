package net.omsu.recognition.graphics;

/**
 *
 */
public interface GridShape {

    void moveTo(double x, double y);
    void lineTo(double x, double y);
    void ellipse(double x, double y, double radius);
}
