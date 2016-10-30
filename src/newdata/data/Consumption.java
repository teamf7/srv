package newdata.data;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by admin on 30.10.2016.
 */
public class Consumption {

    public Consumption() {
    }

    public double generation(){
        return ThreadLocalRandom.current().nextDouble(0.20, 5.6);
    }

}
