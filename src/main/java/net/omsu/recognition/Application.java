package net.omsu.recognition;

import com.xeiam.xchart.*;
import net.omsu.recognition.mnk.MathMethod;
import net.omsu.recognition.mnk.distribution.UniformDistribution;
import net.omsu.recognition.mnk.functions.SinFunction;
import org.apache.commons.math3.util.Pair;

import java.util.List;

/**
 *
 */
public class Application {

    public static void main(String[] args) {
        Chart chart = new ChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
        chart.getStyleManager().setMarkerSize(6);

        MathMethod method = new MathMethod(
                new SinFunction(),
                new UniformDistribution(10),
                new UniformDistribution(1)
        );

        searchFunction(chart, method);
        linearEquation(chart, method);
        solveLinearEquation(chart, method);

        SwingWrapper wrapper = new SwingWrapper(chart);
        wrapper.displayChart();
    }

    private static void searchFunction(final Chart chart, final MathMethod method) {

        Pair<List<Double>, List<Double>> values = method.searchFunction();
        chart.addSeries("sin(x)", values.getKey(), values.getValue());
    }

    private static void linearEquation(final Chart chart, final MathMethod method) {

        Pair<List<Double>, List<Double>> values = method.buildLinearEquation();
        //chart.addSeries("sd", values.getKey(), values.getValue());
    }

    private static void solveLinearEquation(final Chart chart, final MathMethod method) {

        Pair<List<Double>, List<Double>> values = method.solveLinearEquations();
        chart.addSeries("sdt", values.getKey(), values.getValue());
    }
}
