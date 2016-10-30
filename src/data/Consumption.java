package data;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by admin on 30.10.2016.
 */
public class Consumption {

    public Consumption() {
    }

    public static double consumption() {
        return ThreadLocalRandom.current().nextDouble(1, 4);
    }
}
