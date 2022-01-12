package at.fhhagenberg.sqe;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sqelevator.Elevator;
import sqelevator.Floor;

/**
 * JavaFX App
 */
public class App extends Application {

    // Hier: ECCManager instanzieren
    // Layout-Klasse anlegen, die die GUI aufbaut und Manager mitgeben
    // Manager erzeugt ElevatorModel, Updater, ... und hier geben wir es an die GUI

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var layout = new BorderPane(label);
        var button = new Button("Click me!");
        button.setOnAction(evt -> button.setText("Clicked!"));
        layout.setBottom(button);

        var scene = createScene();
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.setTitle("Elevator Control Center");
        stage.show();

        // Task<Void> pollingTask = new Task<Void>() {
        //     @Override
        //     protected Void call() throws Exception {
        //         while (true) { // NOSONAR
        //             Thread.sleep(100);
        //             // fetch new data
        //             Platform.runLater(() -> {
        //                 // update model
        //             });
        //         }
        //     }
        // };
        // new Thread(pollingTask).start();

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

        return new Scene(tabs, 800, 350);
    }

    /**
     * Let her rip
     * 
     * @param args what do you think it is
     */
    public static void main(String[] args) {
        Application.launch();
    }

}