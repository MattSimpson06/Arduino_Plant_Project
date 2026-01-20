import org.firmata4j.Pin;
import java.util.TimerTask;

/**
 * This class handles periodic sampling from the soil moisture sensor
 * and converts the raw analog value into a voltage.
 */
public class SensorPeriodicSample extends TimerTask {
    private final Pin sensorPin;
    private static double moistureValue;

    /**
     * Constructs a SensorPeriodicSample task using the specified analog pin.
     *
     * @param sensorPin the analog pin connected to the soil moisture sensor
     */
    public SensorPeriodicSample(Pin sensorPin) {
        this.sensorPin = sensorPin;
    }

    /**
     * Reads the sensor value, converts it to voltage, and stores it for other components to access.
     */
    @Override
    public void run() {
        long rawValue = sensorPin.getValue();

        System.out.println("RValue: " + rawValue);
        moistureValue = scaleToVoltage(rawValue);
        System.out.printf("Raw analog reading: %d, Moisture voltage: %.2f V%n", rawValue, moistureValue);
    }

    /**
     * Converts an analog sensor value to voltage (0.0 - 5.0V).
     *
     * @param analogValue the raw analog sensor reading from 0 to 1023
     * @return the scaled voltage from 0.0 to 5.0
     */
    protected double scaleToVoltage(long analogValue){
        return (analogValue / 1023.0) * 5.0;
    }

    /**
     * Gets the most recently calculated voltage from the sensor.
     *
     * @return the current moisture voltage
     */
    public static double getMoistureValue() {
        return moistureValue;
    }

    /**
     * Sets the moisture value manually, used for testing purposes.
     *
     * @param value the simulated moisture voltage
     */
    public static void setMoistureValue(double value) {
        moistureValue = value;
    }
}