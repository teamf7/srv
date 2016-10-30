package data;

/**
 * Created by designAi on 26.10.2016.
 */
public class Battery {
    private double capacity = 0;
    private double defaultCapacity= 400;
    public  Battery(){}


    public double getCapacity() {
        return capacity;
    }

    public void generationCurrent(double current) {
        if(capacity < defaultCapacity) {
            this.capacity += current;
        }else {
            this.capacity += current / 100;
        }
    }
    public void consumptionCurrent(double current) {
        if(capacity < defaultCapacity) {
            this.capacity -= current;
        }
    }

}
