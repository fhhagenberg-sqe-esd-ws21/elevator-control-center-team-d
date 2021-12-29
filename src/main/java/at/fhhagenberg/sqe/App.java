package at.fhhagenberg.sqe;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private int payload = 1000;
    private int speed = 20;
    private int target = 5;
    private boolean door = false;
    private int numOfFloors = 4;
    private int numOfElevators = 2;

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

        // var scene = new Scene(layout, 640, 480);
        var scene = createScene();

        stage.setScene(scene);
        stage.setTitle("Elevator Control Center");
        stage.show();
    }

    /**
     * 
     * @param elevatorNumber Which elevator is it
     * @return grid view for requested Elevator
     */
    private GridPane createGrid(int elevatorNumber) {
        GridPane grid = new GridPane();

        // stats
        Text StatsHeader = new Text("Stats");
        StatsHeader.setId("Stats");
        StatsHeader.setFill(Color.BLUE);
        Text Payload = new Text("Payload: ");
        Text PayloadVal = new Text("105 kg");
        Text Speed = new Text("Speed: ");
        Text SpeedVal = new Text("3 m/s");
        Text Target = new Text("Target: ");
        Text TargetVal = new Text("5");
        Text DoorStat = new Text("Door: ");
        Text DoorVal = new Text("Closed");

        grid.add(StatsHeader, 0, 0);
        grid.add(Payload, 0, 1);
        grid.add(PayloadVal, 1, 1);
        grid.add(Speed, 0, 2);
        grid.add(SpeedVal, 1, 2);
        grid.add(DoorStat, 0, 3);
        grid.add(DoorVal, 1, 3);
        grid.add(Target, 0, 4);
        grid.add(TargetVal, 1, 4);

        // Manuel mode
        Text ManuelModeHeader = new Text("Manuel Mode");
        ManuelModeHeader.setFill(Color.BLUE);
        // ManuelModeHeader.setFont(Font.font("verdana", FontWeight.BOLD,
        // FontPosture.REGULAR, 20));
        Text NextFloorHeader = new Text("Next Floor");

        ComboBox comboBox = new ComboBox();
        for (int i = 1; i <= numOfFloors; i++) {
            String Floor = "Floor " + i;
            comboBox.getItems().add(Floor);
        }
        Button setTarget = new Button("Go!");

        ToggleButton enableAutoMode = new ToggleButton("Automatic Mode");
        enableAutoMode.setDisable(true);

        grid.add(ManuelModeHeader, 3, 0);
        grid.add(NextFloorHeader, 3, 1);
        grid.add(comboBox, 3, 2);
        grid.add(setTarget, 4, 2);
        grid.add(enableAutoMode, 3, 3);

        // commited direction
        Text directionHeader = new Text("Commited Direction");
        directionHeader.setFill(Color.BLUE);
        Text directionText = new Text("UP");

        grid.add(directionHeader, 5, 0);
        grid.add(directionText, 5, 1);

        // Logs
        Text LogHeader = new Text("LastError");
        Text LastError = new Text("no Errors");
        grid.add(LogHeader, 3, 4);
        grid.add(LastError, 3, 5);

        // Floor Status
        Text FloorLabel = new Text("Floor");
        Text UpBotton = new Text("Up Button");
        Text DownBotton = new Text("Down Button");

        Text floor1 = new Text(String.valueOf(1));
        Text upRequested = new Text("x");
        Text downRequested = new Text("");

        Text floor2 = new Text(String.valueOf(2));
        Text upRequested2 = new Text("");
        Text downRequested2 = new Text("x");

        grid.add(FloorLabel, 5, 3);
        grid.add(UpBotton, 6, 3);
        grid.add(DownBotton, 7, 3);

        grid.add(floor1, 5, 4);
        grid.add(upRequested, 6, 4);
        grid.add(downRequested, 7, 4);

        grid.add(floor2, 5, 5);
        grid.add(upRequested2, 6, 5);
        grid.add(downRequested2, 7, 5);

        return grid;
    }

    /**
     * 
     * @return The finished gui Scene
     */
    private Scene createScene() {

        GridPane grid1 = createGrid(1);
        GridPane grid2 = createGrid(2);

        TabPane tabs = new TabPane();
        Tab tab1 = new Tab("Elevator 1", grid1);
        Tab tab2 = new Tab("Elevator 2", grid2);
        tabs.getTabs().add(tab1);
        tabs.getTabs().add(tab2);

        var scene = new Scene(tabs);

        return scene;
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