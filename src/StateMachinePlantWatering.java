import org.firmata4j.Pin;
import java.util.TimerTask;

/**
 * This class handles the logic for watering the plant based on moisture readings.
 * It acts as a state machine that runs on a schedule.
 */
public class StateMachinePlantWatering extends TimerTask {
    private final Pin pump;

    /**
     * Constructs a StateMachinePlantWatering with the specified pump pin.
     *
     * @param pump the digital output pin connected to the pump relay
     */
    public StateMachinePlantWatering(Pin pump) {
        this.pump = pump;
    }

    /**
     * This method is triggered by a timer. It checks the soil moisture level
     * and activates or deactivates the pump based on preset thresholds.
     */
    @Override
    public void run() {
        double moisture = SensorPeriodicSample.getMoistureValue();

        try {
            if (moisture >= Board.REALLY_DRY_VALUE) {
                System.out.println("its really dry here man, he NEEDS water. Activating pump for 2 seconds.");
                activatePump(Board.MUCH_WATER_DURATION);
            }
            else if (moisture >= Board.SLIGHTLY_WET_VALUE) {
                System.out.println("Its a lil dry he needs a bit of water. Activating pump for 1 second.");
                activatePump(Board.LIL_WATER_DURATION);
            }
            else if (moisture >= Board.VERY_WET_VALUE) {
                System.out.println("The dirt looks good no water please. Pump is off.");
                pump.setValue(0);
            }
            else {
                System.out.println("Soil is very wet. Definitely do not give him water.");
                pump.setValue(0);
            }
        } catch (Exception e) {
            System.err.println("Error in state machine: " + e.getMessage());
        }
    }

    /**
     * Activates the pump for a specified duration.
     *
     * @param duration how long to run the pump, in milliseconds
     * @throws Exception if setting pin value or sleep fails
     */
    private void activatePump(long duration) throws Exception {
        pump.setValue(1);
        Thread.sleep(duration);
        pump.setValue(0);
    }
}
