package views;

import data.SolarData;
import data.SolarPanel;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
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
        private Label consumption;
    @FXML
        private SVGPath generation1;
    @FXML
        private SVGPath generation2;
    @FXML
        private SVGPath  generation0;
    @FXML
        private RadioButton rb1;
    @FXML
        private RadioButton rb2;
    @FXML
        private RadioButton rb3;


    private MainApp mainApp;
    private boolean startGenerator = true;
    private ScheduledFuture future1;
    private ScheduledFuture future2;
    private ScheduledFuture future3;
    private SolarData data;
    private FadeTransition transition1;
    private FadeTransition transition2;
    private FadeTransition transition3;
    private SolarPanel[] solarPanels;

    public DataController(){
        solarPanels = new SolarPanel[3];
    }

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
            ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(3, r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });
            solarPanels[0] = new SolarPanel(data, "Панель 1");
            solarPanels[1] = new SolarPanel(data, "Панель 2");
            solarPanels[2] = new SolarPanel(data, "Панель 3");
            transition1 = currentAnimation(transition1,generation1);
            animationSvgPath(transition1);
            future1 = exec.scheduleAtFixedRate(solarPanels[0], 0, 1, TimeUnit.SECONDS);
            future2 = exec.scheduleAtFixedRate(solarPanels[1], 0, 1, TimeUnit.SECONDS);
            future3 = exec.scheduleAtFixedRate(solarPanels[2], 0, 1, TimeUnit.SECONDS);
            startGenerator = false;
        }
    }
    public void updateSvg(double current, double capacity){
        if(current == 0 ){
            stopSolarAnimationSVG(transition2, generation2);
            generation0.setVisible(true);
            transition3 = currentAnimation(transition3,generation0);
            animationSvgPath(transition3);
        }else {
            transition2 = currentAnimation(transition2,generation2);
            animationSvgPath(transition2);
        }


    }

    public FadeTransition currentAnimation(FadeTransition transition,SVGPath nameSVG){
        nameSVG.setVisible(true);
        return transition = new FadeTransition(Duration.millis(500), nameSVG);
    }

    public void stopSolarAnimationSVG(FadeTransition transition,SVGPath nameSVG){
        transition.stop();
        nameSVG.setVisible(false);
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
        stopSolarAnimationSVG(transition1, generation1);
        stopSolarAnimationSVG(transition2, generation2);
        future1.cancel(false);
        future2.cancel(false);
        future3.cancel(false);
    }
    public void closeFirstSolarGeneration(){
        if(rb1.isSelected())
            solarPanels[0].setStopGenerationPanel();
        if(rb2.isSelected())
            solarPanels[1].setStopGenerationPanel();
        if(rb3.isSelected())
            solarPanels[2].setStopGenerationPanel();
    }
    public void startFirstSolarGeneration(){
        if(rb1.isSelected())
            solarPanels[0].setWorkGenerationPanel();
        if(rb2.isSelected())
            solarPanels[1].setWorkGenerationPanel();
        if(rb3.isSelected())
            solarPanels[2].setWorkGenerationPanel();
    }


    public void setLabel(String p,String c,String cap,String cons){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                power.setText(p);
                current.setText(c);
                capacity.setText(cap);
                consumption.setText(cons);
            }
        });
    }
}
