package net.omsu.recognition.mnk.functions;

import java.util.function.Function;

/**
 *
 */
public class SinFunction implements Function<Double, Double> {

    public SinFunction() {}

    @Override
    public Double apply(final Double argument) {
        return Math.sin(argument);
    }
}
