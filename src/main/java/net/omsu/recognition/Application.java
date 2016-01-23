package net.omsu.recognition;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;

/**
 *
 */
public class Application {

    public static void main(String[] args) {
        new Application();
    }

    public Application() {

        double[] xData = new double[] { 0.0, 1.0, 2.0 };
        double[] yData = new double[] { 2.0, 1.0, 0.0 };

        // Create Chart
        Chart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);

        new SwingWrapper(chart).displayChart();

        //MathMethod mathMethod = new MathMethod(new SinFunction(), new UniformDistribution(10), new UniformDistribution(1));
    }
}
