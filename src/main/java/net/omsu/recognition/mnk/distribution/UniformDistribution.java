package net.omsu.recognition.mnk.distribution;

import org.apache.commons.math3.distribution.UniformRealDistribution;

import java.util.function.Supplier;

/**
 *
 */
public class UniformDistribution implements Supplier<Double> {

    private final UniformRealDistribution distribution;

    public UniformDistribution(final int range) {
        this.distribution = new UniformRealDistribution(-range, range);
    }

    @Override
    public Double get() {
        return distribution.sample();
    }
}
