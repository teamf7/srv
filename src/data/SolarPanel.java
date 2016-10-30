package data;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by admin on 25.10.2016.
 */
public class SolarPanel implements Runnable {
    private volatile SolarData solarData;
    private String name;
    private volatile int generationPower;
    private volatile double generationCurrent;
    private boolean working = true;

    public SolarPanel(SolarData solarData, String name){
        this.solarData = solarData;
        this.name = name;
    }

    public SolarPanel(String name) {
        this.name = name;
    }

    public void generation(){
        if(working){
            generationPower = 25;//ThreadLocalRandom.current().nextInt(25, 35);
            generationCurrent = 3;//ThreadLocalRandom.current().nextDouble(0.20, 5.6);
        }
    }

    public String getName() {
        return name;
    }

    public void setStopGenerationPanel() {
        this.working = false;
        generationPower = 0;
        generationCurrent = 0;
    }
    public void setWorkGenerationPanel() {
        this.working = true;
    }

    @Override
    public void run() {
        generation();
        try {
            solarData.update(generationPower, generationCurrent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
