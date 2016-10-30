package data;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by admin on 25.10.2016.
 */
public class SolarPanel implements Runnable {
    private volatile SolarData solarData;

    public SolarPanel(SolarData solarData){
        this.solarData = solarData;
    }


    @Override
    public void run() {
        int randomPower = ThreadLocalRandom.current().nextInt(25, 35);
        double randomCurrent = ThreadLocalRandom.current().nextDouble(0.20, 5.6);
        solarData.update(randomPower, randomCurrent);
    }

}
