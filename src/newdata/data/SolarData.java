package newdata.data;

import java.text.DecimalFormat;

/**
 * Created by admin on 30.10.2016.
 */
public class SolarData {
    private volatile SolarPanel[] panels;
    private boolean starter = true;
    private double current = 0;
    private Battery battery;
    private  Consumption consumption;
    public SolarData() {
        panels = new SolarPanel[3];
        battery = new Battery();
        consumption = new Consumption();
        initialization();
    }

    private void initialization() {
        panels[0] = new SolarPanel("Панель 1");
        panels[1] = new SolarPanel("Панель 2");
        panels[2] = new SolarPanel("Панель 3");
    }

    public void setWork(){
        starter = true;
    }

    public void setStop(){
        starter = false;
    }

    public void update(){
        current = 0;
        for(SolarPanel panel: panels){
            current += panel.getGeneration();
        }
        battery.addCurrent(current);
        battery.consumptionCurrent(consumption.generation());

    }
    public void print(){
        DecimalFormat format = new DecimalFormat("##.#");
        System.out.println(format.format(current) + "");
    }

    public void generationSolarPanel() throws InterruptedException {
        while(starter){
            panels[0].generation();
            panels[1].generation();
            panels[2].generation();
            Thread.sleep(500);
            update();
        }
    }
}
