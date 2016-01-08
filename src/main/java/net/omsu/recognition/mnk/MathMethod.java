package net.omsu.recognition.mnk;

import net.omsu.recognition.graphics.GridShape;
import net.omsu.recognition.mnk.distribution.Distribution;
import net.omsu.recognition.mnk.functions.Function;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 */
public class MathMethod {

    private static final int RANGE = 10;
    private static final int DEGREE = 6;
    private static final int PARTS = 50;

    private final Function function;
    private final Distribution distribution;

    public MathMethod(final Function function, final Distribution distribution) {
        this.function = function;
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

        for (int i = 0; i < PARTS; i++) {

            double xi = distribution.calc();
            double mistake = distribution.calc();
            double yi = function.calc(xi) + mistake;

            gridShape.ellipse(xi, yi, 2);
        }
    }
}
