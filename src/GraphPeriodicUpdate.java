import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.util.TimerTask;

/**
 * This class handles periodic updates to a live graph showing soil moisture levels over time.
 */
public class GraphPeriodicUpdate extends TimerTask {

    private static XYSeries moistureSeries = new XYSeries("Soil Moisture");
    private static double elapsedTime = 0.0;

    private static final XYSeries series = new XYSeries("Moisture Readings");
    private static double time = 0;

    /**
     * Initializes and displays a Swing window with a live updating chart.
     */
    public static void createAndShowChart() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Soil Moisture Over Time",
                "Time (seconds)",
                "Moisture Level (Voltage)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame("Real-time Moisture Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setSize(800,600);
        frame.setVisible(true);
    }

    /**
     * Adds the current moisture value to the graph at each scheduled interval.
     */
    @Override
    public void run() {
        double moisture = SensorPeriodicSample.getMoistureValue();
        series.add(time, moisture);
        time += Board.GRAPH_UPDATE_INTERVAL / 1000.0;
    }

    /**
     * Exposes the XYSeries data to enable verification in tests.
     *
     * @return the XYSeries object containing moisture data points
     */
    public static XYSeries getSeries() {
        return series;
    }
}
