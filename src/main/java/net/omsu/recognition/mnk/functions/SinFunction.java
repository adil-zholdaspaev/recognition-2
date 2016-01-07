package net.omsu.recognition.mnk.functions;

/**
 *
 */
public class SinFunction implements Function {

    public SinFunction() {}

    public double calc(double x) {
        return x * Math.sin(2 * Math.PI * x);
    }
}
