package data;

import exception.SolarBatteryException;
/**
 * Created by designAi on 26.10.2016.
 */
public class Battery {
    private double capacity = 40;
    private double defaultCapacity= 400;
    public  Battery(){}


    public double getCapacity() {
        return capacity;
    }

    public void generationCurrent(double current) {
        if(capacity < defaultCapacity) {
            this.capacity += current;
        }
    }
    public void consumptionCurrent(double current) {
        if(capacity < defaultCapacity) {
            this.capacity -= current;
        }
    }

}
