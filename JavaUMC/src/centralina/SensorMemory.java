package centralina;

import java.util.Arrays;

public class SensorMemory {
    Sensor sensor;
    float[] measurements;
    private int counter;
    
    public SensorMemory(Sensor sensor, int capacity) {
        this.sensor = sensor;
        measurements = new float[capacity];
        counter = 0;
    }
    
    public boolean memorize(float data) {
        if(counter == measurements.length) 
            return false;
        else {
            measurements[counter] = data;
            counter++;
            return true;
        }
    }
    
    public void resetMemory() {
        Arrays.fill(measurements, 0);
        counter = 0;
    }
    
    public float[] readMemory() {
        return measurements;
    }
    
    public Sensor getSensor() {
        return sensor;
    }
}
