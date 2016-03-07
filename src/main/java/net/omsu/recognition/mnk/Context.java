package net.omsu.recognition.mnk;

import net.omsu.recognition.mnk.gauss.LinearSystem;
import net.omsu.recognition.mnk.gauss.MyEquation;
import org.apache.commons.math3.util.Pair;

import java.util.List;

public class Context {

    private final int degree;

    private LinearSystem<Double, MyEquation> linearSystem;
    private Pair<List<Double>, List<Double>> originalDistribution;
    private Pair<List<Double>, List<Double>> resultDistribution;

    public Context(final int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public LinearSystem<Double, MyEquation> getLinearSystem() {
        return linearSystem;
    }

    public Pair<List<Double>, List<Double>> getOriginalDistribution() {
        return originalDistribution;
    }

    public Pair<List<Double>, List<Double>> getResultDistribution() {
        return resultDistribution;
    }

    public void setLinearSystem(LinearSystem<Double, MyEquation> linearSystem) {
        this.linearSystem = linearSystem;
    }

    public void setOriginalDistribution(Pair<List<Double>, List<Double>> originalDistribution) {
        this.originalDistribution = originalDistribution;
    }

    public void setResultDistribution(Pair<List<Double>, List<Double>> resultDistribution) {
        this.resultDistribution = resultDistribution;
    }
}
