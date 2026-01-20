import org.jfree.data.xy.XYSeries;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for core components of the Plant Watering System.
 * These tests verify sensor scaling, constant values, and graph updates.
 */
public class PlantsystemTest {

    /**
     * Helper class to expose protected methods from SensorPeriodicSample for testing.
     */
    public static class TestSensor extends SensorPeriodicSample {
        public TestSensor() {
            super(null); // No hardware needed
        }

        /**
         * Exposes the scaleToVoltage method for unit testing.
         *
         * @param analogValue the simulated analog sensor value (0-1023)
         * @return the calculated voltage from 0.0 to 5.0
         */
        public double testScaleToVoltage(long analogValue) {
            return super.scaleToVoltage(analogValue);
        }
    }

    /**
     * Tests the voltage scaling method using known values.
     */
    @Test
    public void testVoltageScaling() {
        TestSensor sensor = new TestSensor();
        assertEquals(0.0, sensor.testScaleToVoltage(0), 0.01);
        assertEquals(2.5, sensor.testScaleToVoltage(512), 0.01);
        assertEquals(5.0, sensor.testScaleToVoltage(1023), 0.01);
    }

    /**
     * Tests whether the moisture value setter and getter are working.
     */
    @Test
    public void testSetAndGetMoistureValue() {
        SensorPeriodicSample.setMoistureValue(3.14);
        assertEquals(3.14, SensorPeriodicSample.getMoistureValue(), 0.001);
    }

    /**
     * Confirms that Board constants haven't been changed unexpectedly.
     */
    @Test
    public void testBoardConstants() {
        assertEquals(14, Board.SOIL_SENSOR_PIN);
        assertEquals(2.70, Board.VERY_WET_VALUE, 0.01);
        assertEquals(2000, Board.SENSOR_SAMPLE_INTERVAL);
    }

    /**
     * Ensures the graph gets updated with a new data point after each run.
     */
    @Test
    public void testGraphDataUpdates() {
        SensorPeriodicSample.setMoistureValue(3.25);
        GraphPeriodicUpdate graphTask = new GraphPeriodicUpdate();
        graphTask.run();

        XYSeries dataSeries = GraphPeriodicUpdate.getSeries();
        assertTrue(dataSeries.getItemCount() >= 1);

        double lastY = dataSeries.getY(dataSeries.getItemCount() - 1).doubleValue();
        assertEquals(3.25, lastY, 0.01);
    }
}
