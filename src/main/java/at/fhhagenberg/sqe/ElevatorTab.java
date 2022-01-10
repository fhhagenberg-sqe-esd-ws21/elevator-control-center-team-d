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
        if (elevatorNumber < 0 || floors.isEmpty()) {
            throw new IllegalArgumentException();
        }

        mElevator = elevator;
        mFloors = floors;
        mNumber = elevatorNumber;
    }

    private void upDateElevator(int target) {
        mElevator.setTarget(target);
    }

    public Tab createTab() {
        String tabName = "Elevator " + mNumber;

        // stats
        Text statsHeader = new Text("Stats");
        statsHeader.setFill(Color.BLUE);

        Text payloadVal = new Text();
        Text speedVal = new Text();
        Text targetVal = new Text();
        Text doorVal = new Text();

        targetVal.setId("TargetVal" + mNumber);

        Text payload = new Text("Payload (kg): ");
        payloadVal.textProperty().bind(mElevator.mWeightProperty().asString());
        Text speed = new Text("Speed (m/s): ");
        speedVal.textProperty().bind(mElevator.mSpeedProperty().asString());
        Text target = new Text("Target: ");
        targetVal.textProperty().bind(mElevator.mTargetProperty().asString());
        Text doorStat = new Text("Door: ");
        doorVal.textProperty().bind(mElevator.mDoorStatusProperty().asString());
        

        GridPane grid = new GridPane();
        grid.add(statsHeader, 0, 0);
        grid.add(payload, 0, 1);
        grid.add(payloadVal, 1, 1);
        grid.add(speed, 0, 2);
        grid.add(speedVal, 1, 2);
        grid.add(doorStat, 0, 3);
        grid.add(doorVal, 1, 3);
        grid.add(target, 0, 4);
        grid.add(targetVal, 1, 4);

        // Manuel mode
        Text manuelModeHeader = new Text("Manuel Mode");
        manuelModeHeader.setFill(Color.BLUE);
        Text nextFloorHeader = new Text("Next Floor");

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setId("floorComboBox" + mNumber);

        for (int i = 0; i < mFloors.size(); i++) {
            String floor = "Floor " + i;
            comboBox.getItems().add(floor);
        }

        Button setTarget = new Button("Go!");
        setTarget.setId("goButton" + mNumber);

        setTarget.setDisable(true);
        setTarget.setOnAction(evt -> upDateElevator(comboBox.getSelectionModel().getSelectedIndex()));

        comboBox.setOnAction(evt -> {
            if(comboBox.getSelectionModel() != null)
                setTarget.setDisable(false);
        });

        ToggleButton enableAutoMode = new ToggleButton("Automatic Mode");
        enableAutoMode.setDisable(true);

        grid.add(manuelModeHeader, 3, 0);
        grid.add(nextFloorHeader, 3, 1);
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
        Text logHeader = new Text("LastError");
        Text lastError = new Text("no Errors"); // TODO
        grid.add(logHeader, 3, 4);
        grid.add(lastError, 3, 5);

        // Floor Status
        Text floorLabel = new Text("Floor");
        Text upBotton = new Text("Up Button");
        Text downBotton = new Text("Down Button");

        grid.add(floorLabel, 5, 3);
        grid.add(upBotton, 6, 3);
        grid.add(downBotton, 7, 3);

        for (int i = 0; i < mFloors.size(); i++) {
            Text floor = new Text(String.valueOf(i));
            Text upRequested = new Text();
            Text downRequested = new Text();

            upRequested.textProperty().bind(mFloors.get(i).upButtonPressedProperty().asString());
            downRequested.textProperty().bind(mFloors.get(i).downButtonPressedProperty().asString());

            grid.add(floor, 5, 4 + i);
            grid.add(upRequested, 6, 4 + i);
            grid.add(downRequested, 7, 4 + i);
        }

        Tab tab = new Tab(tabName, grid);

        tab.setClosable(false);

        return tab;
    }
}
