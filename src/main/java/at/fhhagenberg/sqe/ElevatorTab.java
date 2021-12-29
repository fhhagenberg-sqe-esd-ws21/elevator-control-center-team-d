package at.fhhagenberg.sqe;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sqelevator.*;

public class ElevatorTab {

    private Elevator mElevator;
    private ArrayList<Floor> mFloors;
    private int mNumber;

    ElevatorTab(Elevator elevator, int elevatorNumber, ArrayList<Floor> floors) {
        if (elevatorNumber < 0 || floors.size() == 0) {
            throw new IllegalArgumentException();
        }

        mElevator = elevator;
        mFloors = floors;
        mNumber = elevatorNumber;
    }

    public Tab createTab() {
        String tabName = "Elevator " + String.valueOf(mNumber);

        // stats
        Text StatsHeader = new Text("Stats");
        StatsHeader.setId("Stats");
        StatsHeader.setFill(Color.BLUE);
        Text Payload = new Text("Payload: ");
        Text PayloadVal = new Text(String.valueOf(mElevator.getWeight()) + "kg");
        Text Speed = new Text("Speed: ");
        Text SpeedVal = new Text(String.valueOf(mElevator.getSpeed()) + "m/s");
        Text Target = new Text("Target: ");
        Text TargetVal = new Text(String.valueOf(mElevator.getFloor()));
        Text DoorStat = new Text("Door: ");
        Text DoorVal = new Text(mElevator.getDoorStatus().name());

        GridPane grid = new GridPane();
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
        Text LastError = new Text("no Errors"); // TODO
        grid.add(LogHeader, 3, 4);
        grid.add(LastError, 3, 5);

        // Floor Status
        Text FloorLabel = new Text("Floor");
        Text UpBotton = new Text("Up Button");
        Text DownBotton = new Text("Down Button");

        grid.add(FloorLabel, 5, 3);
        grid.add(UpBotton, 6, 3);
        grid.add(DownBotton, 7, 3);

        for (int i = 0; i < mFloors.size(); i++) {
            Text floor = new Text(String.valueOf(i));
            Text upRequested = new Text(String.valueOf(mFloors.get(i).isUpButtonPressed()));
            Text downRequested = new Text(String.valueOf(mFloors.get(i).isDownButtonPressed()));
            ;

            grid.add(floor, 5, 4 + i);
            grid.add(upRequested, 6, 4 + i);
            grid.add(downRequested, 7, 4 + i);
        }

        Tab tab = new Tab(tabName, grid);

        return tab;
    }
}
