package at.fhhagenberg.sqe;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sqelevator.ECC;
import sqelevator.ECCDataModel;
import sqelevator.Elevator;
import sqelevator.Floor;

/**
 * JavaFX App
 */
public class App extends Application {

    // Hier: ECCManager instanzieren
    // Layout-Klasse anlegen, die die GUI aufbaut und Manager mitgeben
    // Manager erzeugt ElevatorModel, Updater, ... und hier geben wir es an die GUI
    private ECC ecc;
    private Timer timer;
	private TimerTask timerTask;

    private static final int timerPeriodMS = 10;

    @Override
    public void start(Stage stage) {

        // Im ECC wird der RMI-Call ausgeführt und Model + Updater instanziert.
        ecc = getECC();
        ecc.init();

        // Try to reconnect if RMI can't be reached
        if (!ecc.isConnected()) {
            if (!tryReconnect(3)) {
                System.out.println("Failed to connect to RMI. Shutting down.");
                // System.exit(-1);
                return;
            }
        }

        // ScheduledService oder Timer?
        // https://stackoverflow.com/questions/9966136/javafx-periodic-background-task

        timer = new Timer();
        timerTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        ecc.update();
                    });
            }
        };
        timer.schedule(timerTask, 0, timerPeriodMS);
        System.out.println("ECC connected to RMI and timer created.");

        

        var label = new Label("Hello, JavaFX.");
        var layout = new BorderPane(label);
        var button = new Button("Click me!");
        button.setOnAction(evt -> button.setText("Clicked!"));
        layout.setBottom(button);

        var scene = createScene(ecc.getModel());
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.setTitle("Elevator Control Center");
        stage.show();


    }

    

    // Take all elevators from the ECCDataModel and create a tab for each one.
    private Scene createScene(ECCDataModel model) {
        TabPane tabs = new TabPane();

        List<Elevator> elevators = model.getElevatorList();
        List<Floor> floors = model.getFloorList();

        for (int i = 0; i < elevators.size(); i++) {
            ElevatorTab t = new ElevatorTab(elevators.get(i), i, floors, ecc.getUpdater());
            tabs.getTabs().add(t.createTab());
        }

        return new Scene(tabs, 800, 350);
    }
    
    
    
    private boolean tryReconnect(int nrTries) {

        System.out.println("Failed to connect to RMI. Retrying...");
        ecc.init();
        if (ecc.isConnected()) {
            return true;
        }
        
        return false;
    }

    // Overwrite this in your test invocation to inject a mocked ECC object
    protected ECC getECC() {
        return new ECC();
    }


    // This is needed so that the timer stops when the application is closed, 
    // otherwise timer task will continue running.
    @Override
    public void stop() throws Exception {
    	if (timer != null) {
        	timer.cancel();
    	}    	
    	super.stop();
    }


    /**
     * Let her rip
     * 
     * @param args args
     */
    public static void main(String[] args) {
        Application.launch();
    }

}