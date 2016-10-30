package newdata.data;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by admin on 30.10.2016.
 */
public class SolarPanel {
    private String name;
    private double generation;
    private boolean working = true;


    public SolarPanel(String name) {
        this.name = name;
    }

    public void generation(){
        if(working)
            generation = ThreadLocalRandom.current().nextDouble(0.20, 5.6);
    }

    public String getName() {
        return name;
    }

    public double getGeneration() {
        return generation;
    }

    public void setStop() {
        this.working = false;
        generation= 0;
    }
    public void setWork() {
        this.working = true;
    }
}
