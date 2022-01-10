package at.fhhagenberg.sqe;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sqelevator.ECC;
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

    private final int timerPeriodMS = 1000;

    @Override
    public void start(Stage stage) {

        // Im ECC wird der RMI-Call ausgeführt und Model + Updater instaziert.
        // TODO: Zugriff auf Model über den ECC
        ecc = new ECC();
        ecc.init();

        // Handle connection problems
        if (!ecc.isConnected()) {
            for(int i = 0; i < 3; i++) {
                System.out.println("Failed to connect to RMI. Retrying...");
                try {
                    wait(1000000);
                    ecc.init();
                    if (ecc.isConnected()) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Failed to connect to RMI. Shutting down.");
            System.exit(-1);
        }

        // ScheduledService oder Timer?
        // https://stackoverflow.com/questions/9966136/javafx-periodic-background-task

        timer = new Timer();
        timerTask = new TimerTask() {
                @Override
                public void run() {
                    ecc.update();
            }
        };
        timer.schedule(timerTask, 0, timerPeriodMS);
        System.out.println("ECC connected to RMI and timer created.");



        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var layout = new BorderPane(label);
        var button = new Button("Click me!");
        button.setOnAction(evt -> button.setText("Clicked!"));
        layout.setBottom(button);

        var scene = createScene();

        stage.setScene(scene);
        stage.setTitle("Elevator Control Center");
        stage.show();

        Task<Void> pollingTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) { // NOSONAR
                    Thread.sleep(100);
                    // fetch new data
                    Platform.runLater(() -> {
                        // update model
                    });
                }
            }
        };
        new Thread(pollingTask).start();

    }

    /**
     * 
     * @return The finished gui Scene
     */
    private Scene createScene() {
        TabPane tabs = new TabPane();

        Elevator e1 = new Elevator(3);
        Elevator e2 = new Elevator(3);
        Floor f1 = new Floor();
        Floor f2 = new Floor();
        Floor f3 = new Floor();
        f1.setDownButtonPressed(true);
        f2.setUpButtonPressed(true);
        f3.setDownButtonPressed(true);
        ArrayList<Floor> floors = new ArrayList<>();
        floors.add(f1);
        floors.add(f2);
        floors.add(f3);

        ElevatorTab tab1 = new ElevatorTab(e1, 1, floors);
        ElevatorTab tab2 = new ElevatorTab(e2, 2, floors);

        tabs.getTabs().add(tab1.createTab());
        tabs.getTabs().add(tab2.createTab());

        return new Scene(tabs, 640, 480);
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