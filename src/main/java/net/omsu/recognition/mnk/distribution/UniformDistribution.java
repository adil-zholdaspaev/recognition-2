package net.omsu.recognition.mnk.distribution;

import org.apache.commons.math3.distribution.UniformRealDistribution;

/**
 *
 */
public class UniformDistribution implements Distribution {

    private final UniformRealDistribution distribution;

    public UniformDistribution(final int range) {
        this.distribution = new UniformRealDistribution(-range, range);
    }

    public double calc() {
        return distribution.sample();
    }
}
