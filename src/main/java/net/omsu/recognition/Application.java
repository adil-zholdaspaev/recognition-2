package net.omsu.recognition;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;
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
        new Application();
    }

    public Application() {
        Chart chart = new ChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();

        MathMethod mathMethod = new MathMethod(new SinFunction(), new UniformDistribution(10), new UniformDistribution(1));

        Pair<List<Double>, List<Double>> mainFunction = mathMethod.method();

        chart.addSeries("sin(x)", mainFunction.getKey(), mainFunction.getValue());

        new SwingWrapper(chart).displayChart();
    }
}
