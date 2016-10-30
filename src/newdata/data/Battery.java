package newdata.data;

import java.text.DecimalFormat;

/**
 * Created by admin on 30.10.2016.
 */
public class Battery {
    private double capacity = 0;
    private double defaultCapacity= 400;
    public  Battery(){}


    public double getCapacity() {
        return capacity;
    }

    public void addCurrent(double current) {
        if(capacity < defaultCapacity) {
            this.capacity += current;
        }else {
            this.capacity += current / 100;
        }
        System.out.println("Генерация " + current);
        print();
    }
    public void consumptionCurrent(double current) {
        if(capacity < defaultCapacity) {
            this.capacity -= current;
        }
        System.out.println("Потребление " + current);
        print();
    }

    public void print(){
        DecimalFormat format = new DecimalFormat("##.#");
        System.out.println("Battery capacity "+format.format(capacity));
    }
}
