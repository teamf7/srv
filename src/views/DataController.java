package views;

import data.SolarData;
import data.SolarPanel;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by designAi on 26.10.2016.
 */
public class DataController {

    @FXML
        private Label power;
    @FXML
        private Label current;
    @FXML
        private Label capacity;
    @FXML
        private SVGPath generation1;
    @FXML
        private SVGPath generation2;

    private MainApp mainApp;
    private boolean startGenerator = true;
    private ScheduledFuture future1;
    private ScheduledFuture future2;
    private ScheduledFuture future3;
    private SolarData data;
    private FadeTransition transition1;
    private FadeTransition transition2;
    public DataController(){}

    @FXML
    private void initialize() {
        data = new SolarData(this);
        power.setText("0");
        generation1.setVisible(false);
        generation2.setVisible(false);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    public void startSolarGeneration() {
        System.out.println("start project");
        if (startGenerator) {
            generation1.setVisible(true);
            generation2.setVisible(true);
            ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(3, r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });
            SolarPanel panel1 = new SolarPanel(data);
            SolarPanel panel2 = new SolarPanel(data);
            SolarPanel panel3 = new SolarPanel(data);

            transition1 = new FadeTransition(Duration.millis(500), generation1);
            transition2 = new FadeTransition(Duration.millis(500), generation2);
            animationSvgPath(transition1);
            animationSvgPath(transition2);
            future1 = exec.scheduleAtFixedRate(panel1, 0, 1, TimeUnit.SECONDS);
            future2 = exec.scheduleAtFixedRate(panel2, 0, 1, TimeUnit.SECONDS);
            future3 = exec.scheduleAtFixedRate(panel3, 0, 1, TimeUnit.SECONDS);
            startGenerator = false;
        }
    }

    public void animationSvgPath(FadeTransition transition){
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }
    public void closeSolarGeneration(){
        startGenerator = true;
        transition1.stop();
        transition2.stop();
        generation1.setVisible(false);
        generation2.setVisible(false);
        future1.cancel(false);
        future2.cancel(false);
        future3.cancel(false);
    }
    public void setLabel(String p,String c,String cap){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                power.setText(p);
                current.setText(c);
                capacity.setText(cap);
            }
        });
    }
}
