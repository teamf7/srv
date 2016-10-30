import newdata.data.SolarData;
import newdata.data.SolarPanel;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Created by admin on 25.10.2016.
 */


public class MainTest {
    private SolarData solarData;
    private boolean starter = true;
    private double current = 0;

    public MainTest() {
        solarData = new SolarData();

    }

    @Test
    public void testApp() throws InterruptedException {

       solarData.generationSolarPanel();
       solarData.setStop();
    }



}