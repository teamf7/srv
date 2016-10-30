package data;

/**
 * Created by designAi on 26.10.2016.
 */
public class Battery {
    private double capacity = 0;
    private double defaultCapacity= 200;
    public  Battery(){}


    public double getCapacity() {
        return capacity;
    }

    public void addCurrent(double current) {
        if(capacity <= defaultCapacity) {
            this.capacity += current;
        }
    }
}
