// Main Java class for Plant Watering System
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

import java.util.Timer;

/**
 * The main class that initializes and launches the plant watering system.
 */
public class Main {

    /**
     * The entry point for running the system.
     *  Preconditions:
     *   Arduino device must be connected and recognized on the defined port.
     *   Firmata must be loaded on the Arduino.
     *
     * @param args command-line arguments (not used)
     * @throws Exception if device initialization or IO fails
     */
    public static void main(String[] args) throws Exception {

        IODevice arduino = new FirmataDevice(Board.PORT);
        arduino.start();
        arduino.ensureInitializationIsDone();

        Pin soilSensor = arduino.getPin(Board.SOIL_SENSOR_PIN); // Analog pin A0
        Pin pump = arduino.getPin(Board.PUMP_PIN);              // Digital pin D2

        soilSensor.setMode(Pin.Mode.ANALOG);
        pump.setMode(Pin.Mode.OUTPUT);

        // Start sensor sampling
        Timer sensorTimer = new Timer();
        SensorPeriodicSample sensorSampler = new SensorPeriodicSample(soilSensor);
        sensorTimer.scheduleAtFixedRate(sensorSampler, 0, Board.SENSOR_SAMPLE_INTERVAL);

        // Start plant watering logic
        Timer stateMachineTimer = new Timer();
        stateMachineTimer.scheduleAtFixedRate(new StateMachinePlantWatering(pump), 0, Board.STATE_MACHINE_INTERVAL);

        // Start the graph
        GraphPeriodicUpdate.createAndShowChart();
        Timer graphTimer = new Timer();
        graphTimer.scheduleAtFixedRate(new GraphPeriodicUpdate(), 0, Board.GRAPH_UPDATE_INTERVAL);

        System.out.println("System running. Press Enter to stop...");
        System.in.read();

        sensorTimer.cancel();
//        stateMachineTimer.cancel();
//        graphTimer.cancel();
        arduino.stop();

        System.out.println("System stopped.");
    }
}
