## Overview

This project is a soil moisture–controlled automated plant watering system built using an Arduino microcontroller and a Java application. The system continuously monitors soil moisture through an analog sensor, converts raw readings into voltage values, and evaluates them using a threshold-based state machine to determine when and how long a water pump should be activated. 

The Java application operates as a time-driven, event-based system using scheduled tasks (TimerTask). Moisture data is visualized live using JFreeChart, allowing trends in soil conditions to be observed over time and making system behavior easy to validate.

Overall, this project demonstrates practical experience with embedded control systems, real-time scheduling, sensor integration, state-machine logic, data visualization, and testing, all applied to a physical, real-world system.


## Key Features
- Real-time soil moisture monitoring
- Automated watering using calibrated moisture thresholds
- Java state-machine control logic
- Live data visualization with JFreeChart
- Event-driven scheduling using TimerTask
- Modular object-oriented Java design
- Unit tests validating system logic and scaling


## Hardware Components
- Arduino Uno / Grove Base
- Grove Soil Moisture Sensor (analog)
- MOSFET driver board
- DC water pump
- Plastic tubing and water reservoir
- USB serial connection
- Live potted plant


## Software
- Java (IntelliJ IDEA)
- Arduino (Firmata)
- JFreeChart for real-time plotting
- JUnit for testing
- TimerTask / Scheduled execution



## Core Classes

The system is designed using a modular architecture where each class handles a specific responsibility related to sensor sampling, control logic, and data visualization.

- **SensorPeriodicSample**  
  Periodically reads soil moisture sensor data and converts raw analog values into voltage measurements for processing.

- **StateMachinePlantWatering**  
  Implements the watering state machine, controlling pump activation timing based on soil moisture thresholds.

- **GraphPeriodicUpdate**  
  Updates the real-time soil moisture chart, ensuring continuous visualization of sensor data.

- **Board**  
  Centralizes system configuration constants, including pin assignments, moisture thresholds, and timing parameters.

- **Main**  
  Serves as the system entry point, initializing components and scheduling periodic tasks.

- **PlantsystemTest**  
  Contains unit tests that verify sensor scaling accuracy, moisture threshold logic, and graph update behavior.



## Data Visualization
Soil moisture data is plotted in real time using JFreeChart (XYSeries).
The graph displays moisture voltage over time and clearly reflects wet vs. dry soil conditions as watering events occur. Peaks and drops correspond directly to sensor interaction and pump activation

## Screenshots



## Learning Outcomes Demonstrated
- Embedded sensor debugging and analog scaling
- Real-time scheduling with non-blocking tasks
- Hardware-software integration
- Object-oriented system decomposition
- Data visualization for system validation


## Author
- Matthew Simpson
- Electrical Engineering Student
- York University
