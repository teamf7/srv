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
    private int  count=0;
    private double consuption = 0;

    public SolarData(DataController data){
        this.data= data;
        battery = new Battery();
    }

    public void update(int power, double current) throws InterruptedException {
        synchronized (this){
            this.power += power;
            this.current += current;
            count++;
            if(count > 2){
                data.updateSvg(this.current,battery.getCapacity());
                setLabel();
            }
        }
    }

    public void setConsuption() {
        this.consuption = Consumption.consumption();
    }

    private void setLabel() {
            battery.generationCurrent(current);
            System.out.println("После генерация " + battery.getCapacity());
        setConsuption();
            battery.consumptionCurrent(consuption);
            System.out.println("После потребления "+battery.getCapacity());
            DecimalFormat format1 = new DecimalFormat("##.#");
            DecimalFormat format2 = new DecimalFormat("##");
            data.setLabel(Integer.toString(power), format1.format(current), format2.format(battery.getCapacity()), format2.format(consuption));
            count = 0;
            this.power = 0;
            this.current = 0;
    }



}
