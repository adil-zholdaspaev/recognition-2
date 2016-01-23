package net.omsu.recognition.mnk.distribution;

import java.util.function.Supplier;

/**
 *
 */
public class NormalDistribution implements Supplier<Double> {

    private final org.apache.commons.math3.distribution.NormalDistribution distribution;

    public NormalDistribution() {
        distribution = new org.apache.commons.math3.distribution.NormalDistribution();
    }

    @Override
    public Double get() {
        return distribution.sample();
    }
}
