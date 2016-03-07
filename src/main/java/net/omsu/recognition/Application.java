package net.omsu.recognition;

import com.xeiam.xchart.*;
import net.omsu.recognition.mnk.Context;
import net.omsu.recognition.mnk.MathMethod;
import net.omsu.recognition.mnk.distribution.UniformDistribution;
import net.omsu.recognition.mnk.functions.SinFunction;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 */
public class Application {

    public static void main(String[] args) {
        Function<Double, Double> function = new SinFunction();

        MathMethod method = new MathMethod(
                function,
                new UniformDistribution(5),
                new UniformDistribution(0.5)
        );

        List<Chart> charts = new ArrayList<>();

        for (int degree = 3; degree < 8; degree++) {
            Context context = new Context(degree);

            Chart defaultChart = chartBuilder();
            Chart distributionChart = chartBuilderScatter();

            searchFunction(defaultChart, method);
            linearEquation(distributionChart, method, context);
            solveLinearEquation(defaultChart, method, context);

            charts.add(defaultChart);
            charts.add(distributionChart);
        }

        new SwingWrapper(charts).displayChartMatrix();
    }

    private static void searchFunction(final Chart chart, final MathMethod method) {

        Pair<List<Double>, List<Double>> values = method.searchFunction();
        chart.addSeries("sin(x)", values.getKey(), values.getValue());
    }

    private static void linearEquation(final Chart chart, final MathMethod method, final Context context) {
        method.buildLinearEquation(context);

        String title = String.format("Degree %d", context.getDegree());
        chart.addSeries(title, context.getOriginalDistribution().getKey(), context.getOriginalDistribution().getValue());
    }

    private static void solveLinearEquation(final Chart chart, final MathMethod method, final Context context) {
        method.solveLinearEquations(context);

        String title = String.format("Degree %d", context.getDegree());
        chart.addSeries(title, context.getResultDistribution().getKey(), context.getResultDistribution().getValue());
    }

    private static Chart chartBuilder() {
        Chart chart = new ChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
        chart.getStyleManager().setMarkerSize(6);

        return chart;
    }

    private static Chart chartBuilderScatter() {
        Chart chart = new ChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
        chart.getStyleManager().setChartType(StyleManager.ChartType.Scatter);

        return chart;
    }
}
