package data;

import views.DataController;

import java.text.DecimalFormat;

/**
 * Created by admin on 25.10.2016.
 */
public class SolarData {

    private int power = 0;
    private double current = 0;
    private DataController data;
    private Battery battery;
    int count=0;
    public SolarData(DataController data){
        this.data= data;
        battery = new Battery();
    }

    public void update(int power, double current){
        this.power +=power;
        this.current +=current;
        setLabel();
        count++;
    }

    private void setLabel() {
        if(count == 3){
            battery.addCurrent(current);
            DecimalFormat format = new DecimalFormat("##.#");
            data.setLabel(Integer.toString(power),format.format(current),format.format(battery.getCapacity()));
            count =0;
            this.power = 0;
            this.current = 0;
        }
    }



}
