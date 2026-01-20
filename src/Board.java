/**
 * Holds all constant configuration values for the Plant Watering System.
 * This includes pin mappings, sensor thresholds, durations, and timing intervals
 * used throughout the program.
 */
public class Board {

    /**
     * The serial port for connecting to the Arduino.
     */
    public static final String PORT = "/dev/tty.usbserial-0001";

    /**
     * Analog pin connected to the soil moisture sensor.
     */
    public static final int SOIL_SENSOR_PIN = 14; // A0

    /**
     * Digital pin connected to the pump.
     */
    public static final int PUMP_PIN = 2;         // D2

    /**
     * Voltage threshold for very wet soil.
     */
    public static final double VERY_WET_VALUE = 2.70;

    /**
     * Voltage threshold for slightly wet soil.
     */
    public static final double SLIGHTLY_WET_VALUE = 3.00;

    /**
     * Voltage threshold for very dry soil.
     */
    public static final double REALLY_DRY_VALUE = 3.40;

    /**
     * Duration to run the pump when soil is very dry (in milliseconds).
     */
    public static final long MUCH_WATER_DURATION = 2000;  // 2 seconds

    /**
     * Duration to run the pump when soil is slightly dry (in milliseconds).
     */
    public static final long LIL_WATER_DURATION = 1000;  // 1 second

    /**
     * Time between sensor samples (in milliseconds).
     */
    public static final long SENSOR_SAMPLE_INTERVAL = 2000;

    /**
     * Time between state machine logic checks (in milliseconds).
     */
    public static final long STATE_MACHINE_INTERVAL = 5000;

    /**
     * Time between graph updates (in milliseconds).
     */
    public static final long GRAPH_UPDATE_INTERVAL = 2000;
}
