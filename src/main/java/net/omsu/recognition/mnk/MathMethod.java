package net.omsu.recognition.mnk;

import net.omsu.recognition.mnk.gauss.Algorithm;
import net.omsu.recognition.mnk.gauss.LinearSystem;
import net.omsu.recognition.mnk.gauss.MyEquation;
import org.apache.commons.math3.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 */
public class MathMethod {

    private static final int RANGE = 5;

    private static final double DELTA = 0.25;
    private static final double OFFSET = 0.001;

    private static final int PARTS = 200;
    private static final int PERCENT = 30;

    private final Function<Double, Double> function;
    private final Supplier<Double> distribution;

    private List<Double> learningArguments;
    private List<Double> checkingArguments;

    public MathMethod(final Function<Double, Double> function,
                      final Supplier<Double> xDistribution,
                      final Supplier<Double> distribution) {

        this.function = function;
        this.distribution = distribution;

        learningArguments = new ArrayList<>();
        checkingArguments = new ArrayList<>();

        for (int i = 0; i < PARTS; i++) {
            double xi = xDistribution.get();

            if (i % 5 == 0) {
                checkingArguments.add(xi);
                continue;
            }

            learningArguments.add(xi);
        }
        Collections.sort(checkingArguments, Double::compareTo);
    }

    public Pair<List<Double>, List<Double>> searchFunction() {
        final List<Double> arguments = new ArrayList<>();
        final List<Double> results = new ArrayList<>();

        for (double x = -RANGE; x <= RANGE; x += DELTA) {
            arguments.add(x);
            results.add(function.apply(x));
        }

        return new Pair<>(arguments, results);
    }

    public void buildLinearEquation(final Context context) {
        final int degree = context.getDegree();
        double a[][] = new double[degree][degree];
        double b[] = new double[degree];

        Random random = new Random();

        List<Double> arguments = new ArrayList<>();
        List<Double> results = new ArrayList<>();

        for (Double xi : learningArguments) {

            double mistake = 0;
            if (random.nextInt(100) < PERCENT) {
                mistake = distribution.get();
            }
            double yi = function.apply(xi) + mistake;

            for (int j = 0; j < degree; j++) {
                for (int k = 0; k < degree; k++) {
                    a[j][k] += Math.pow(xi, (j + k));

                    if (j == k) {
                        a[j][k] += OFFSET;
                    }
                }
                b[j] += yi * Math.pow(xi, j);
            }

            arguments.add(xi);
            results.add(yi);
        }

        LinearSystem<Double, MyEquation> linearSystem = new LinearSystem<>();
        for (int j = 0; j < degree; j++) {
            MyEquation equation = new MyEquation();
            for (int k = 0; k < degree; k++) {
                equation.generate(a[j][k]);
            }
            equation.generate(b[j]);
            linearSystem.push(equation);
        }

        context.setOriginalDistribution(new Pair<>(arguments, results));
        context.setLinearSystem(linearSystem);
    }

    public void solveLinearEquations(final Context context) {
        LinearSystem<Double, MyEquation> linearSystem = context.getLinearSystem();
        Algorithm<Double, MyEquation> algorithm = new Algorithm<>(linearSystem);
        algorithm.calculate();

        Double[] x = new Double[context.getDegree()];
        for(int i = linearSystem.size() - 1; i >= 0; i--) {
            Double sum = 0.0d;
            for(int j = linearSystem.size() - 1; j > i; j--) {
                sum += linearSystem.itemAt(i, j) * x[j];
            }
            x[i] = (linearSystem.itemAt(i, linearSystem.size()) - sum) / linearSystem.itemAt(i, i);
        }

        List<Double> arguments = new ArrayList<>();
        List<Double> results = new ArrayList<>();

        for (Double checkArgument : checkingArguments) {
            double y = 0;
            for (int k = 0; k < context.getDegree(); k++) {
                y += x[k] * Math.pow(checkArgument, (double) k);
            }
            arguments.add(checkArgument);
            results.add(y);
        }

        context.setResultDistribution(new Pair<>(arguments, results));
    }
}
