import data.SolarData;
import data.SolarPanel;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 25.10.2016.
 */
public class Main {


    public static void launch(String[] args){
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(3, r -> {
            Thread t = new Thread(r);
           // t.setDaemon(true);
            return t;
        });
        //SolarData solarData = new SolarData(this);
        //SolarPanel test1 = new SolarPanel(solarData);
        //SolarPanel test2 = new SolarPanel(solarData);
        //SolarPanel test3 = new SolarPanel(solarData);

        //ScheduledFuture future1 = exec.scheduleAtFixedRate(test1, 0, 1, TimeUnit.SECONDS);
        //ScheduledFuture future2 = exec.scheduleAtFixedRate(test2, 0, 1, TimeUnit.SECONDS);
        //ScheduledFuture future3 = exec.scheduleAtFixedRate(test3, 0, 1, TimeUnit.SECONDS);
    }



    public static void main(String[] args) {       launch(args);

    }
}
