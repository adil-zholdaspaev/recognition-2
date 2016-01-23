package net.omsu.recognition.mnk;

import net.omsu.recognition.graphics.GridShape;
import net.omsu.recognition.mnk.distribution.Distribution;
import net.omsu.recognition.mnk.functions.Function;
import net.omsu.recognition.mnk.gauss.Algorithm;
import net.omsu.recognition.mnk.gauss.LinearSystem;
import net.omsu.recognition.mnk.gauss.MyEquation;

import java.util.Random;

/**
 *
 */
public class MathMethod {

    private static final int RANGE = 10;
    private static final int DEGREE = 10;
    private static final int PARTS = 50;
    private static final int PERCENT = 30;

    private final Function function;
    private final Distribution xDistribution;
    private final Distribution distribution;

    public MathMethod(final Function function, final Distribution xDistribution, final Distribution distribution) {
        this.function = function;
        this.xDistribution = xDistribution;
        this.distribution = distribution;
    }

    public void method(final GridShape gridShape) {

        gridShape.moveTo(-RANGE, function.calc(-RANGE));
        for (int x = -RANGE; x <= RANGE; x++) {
            double y = function.calc(x);

            gridShape.lineTo(x, y);
        }
    }

    public void solveLinearEquations(final GridShape gridShape) {
        double a[][] = new double[DEGREE][DEGREE];
        double b[] = new double[DEGREE];

        Random random = new Random();

        for (int i = 0; i < PARTS; i++) {

            double xi = xDistribution.calc();
            double mistake = 0;
            if (random.nextInt(100) < PERCENT) {
                mistake = distribution.calc();
            }
            double yi = function.calc(xi) + mistake;

            gridShape.ellipse(xi, yi, 2);

            for (int j = 0; j < DEGREE; j++) {
                for (int k = 0; k < DEGREE; k++) {
                    a[j][k] += Math.pow(xi, (j + k));
                }
                b[j] += yi * Math.pow(xi, j);
            }
        }

        LinearSystem<Double, MyEquation> linearSystem = new LinearSystem<>();
        for (int j = 0; j < DEGREE; j++) {
            MyEquation equation = new MyEquation();
            for (int k = 0; k < DEGREE; k++) {
                equation.generate(a[j][k]);
            }
            equation.generate(b[j]);
            linearSystem.push(equation);
        }

        Algorithm<Double, MyEquation> algorithm = new Algorithm<>(linearSystem);
        algorithm.calculate();

        Double[] x = new Double[DEGREE];
        for(int i = linearSystem.size() - 1; i >= 0; i--) {
            Double sum = 0.0d;
            for(int j = linearSystem.size() - 1; j > i; j--) {
                sum += linearSystem.itemAt(i, j) * x[j];
            }
            x[i] = (linearSystem.itemAt(i, linearSystem.size()) - sum) / linearSystem.itemAt(i, i);
        }


        double y = 0;
        for (int k = 0; k < DEGREE; k++) {
            y += x[k] * Math.pow(-RANGE, (double) k);
        }

        gridShape.moveTo(-RANGE, y);

        for (int i = -RANGE; i < RANGE; i++) {
            y = 0;
            for (int k = 0; k < DEGREE; k++) {
                y += x[k] * Math.pow(i, (double) k);
            }
            gridShape.lineTo(i, y);
        }
    }
}
