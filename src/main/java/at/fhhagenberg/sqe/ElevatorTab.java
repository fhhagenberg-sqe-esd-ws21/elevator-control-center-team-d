package at.fhhagenberg.sqe;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sqelevator.*;

public class ElevatorTab {

    private Elevator mElevator;
    private ArrayList<Floor> mFloors;
    private ECCUpdater mUpdater;
    private int mNumber;

    ElevatorTab(Elevator elevator, int elevatorNumber, List<Floor> floors, ECCUpdater updater) {
        if (elevatorNumber < 0 || floors.isEmpty()) {
            throw new IllegalArgumentException();
        }

        mElevator = elevator;
        mFloors = new ArrayList<Floor>(floors);
        mNumber = elevatorNumber;
        mUpdater = updater;
    }

    private void updateElevator(int target) {
        // mElevator.setTarget(target);
        mUpdater.sendElevatorTarget(mNumber, target);
    }

    public Tab createTab() {
        String tabName = "Elevator " + (mNumber+1);

        // stats
        GridPane stats = new GridPane();
        stats.getStyleClass().add("group");
        Label statsHeader = new Label("Stats");
        statsHeader.getStyleClass().add("header");

        Label payloadVal = new Label();
        Label speedVal = new Label();
        Label targetVal = new Label();
        Label doorVal = new Label();

        targetVal.setId("TargetVal" + mNumber);

        Label payload = new Label("Payload (kg): ");
        payloadVal.textProperty().bind(mElevator.mWeightProperty().asString());
        Label speed = new Label("Speed (m/s): ");
        speedVal.textProperty().bind(mElevator.mSpeedProperty().asString());
        // Here .add(1) is used to add 1 to the elevator number, so that elevator 0
        // is represented in the string as "elevator 1"
        Label target = new Label("Target: ");
        targetVal.textProperty().bind((mElevator.mTargetProperty().add(1)).asString());
        Label doorStat = new Label("Door: ");
        doorVal.textProperty().bind(mElevator.mDoorStatusProperty().asString());

        // Logs
        Label logHeader = new Label("LastError");
        Label lastError = new Label("no Errors"); // TODO
        
        stats.add(statsHeader, 0, 0);
        stats.add(payload, 0, 1);
        stats.add(payloadVal, 1, 1);
        stats.add(speed, 0, 2);
        stats.add(speedVal, 1, 2);
        stats.add(doorStat, 0, 3);
        stats.add(doorVal, 1, 3);
        stats.add(target, 0, 4);
        stats.add(targetVal, 1, 4);
        stats.add(logHeader, 0, 5);
        stats.add(lastError, 1, 5);




        // Manual mode
        GridPane manual = new GridPane();
        manual.getStyleClass().add("group");
        Label manualModeHeader = new Label("Manual Mode");
        manualModeHeader.getStyleClass().add("header");
        Label nextFloorHeader = new Label("Next Floor");

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setId("floorComboBox" + mNumber);

        for (int i = 0; i < mFloors.size(); i++) {
            String floor = "Floor " + (i+1);
            comboBox.getItems().add(floor);
        }

        Button setTarget = new Button("Go!");
        setTarget.setId("goButton" + mNumber);

        setTarget.setDisable(true);
        setTarget.setOnAction(evt -> updateElevator(comboBox.getSelectionModel().getSelectedIndex()));

        comboBox.setOnAction(evt -> {
            if(comboBox.getSelectionModel() != null)
                setTarget.setDisable(false);
        });

        ToggleButton enableAutoMode = new ToggleButton("Automatic Mode");
        enableAutoMode.setDisable(true);

        manual.add(manualModeHeader, 0, 0);
        manual.add(nextFloorHeader, 0, 1);
        manual.add(comboBox, 1, 1);
        manual.add(setTarget, 2, 1);
        manual.add(enableAutoMode, 0, 2);



        // commited direction
        GridPane direction = new GridPane();
        direction.getStyleClass().add("group");
        Label directionHeader = new Label("DIR");
        directionHeader.getStyleClass().add("header");
        Label directionText = new Label("");
        directionText.textProperty().bind(mElevator.mCommittedDirectionProperty().asString());
        direction.add(directionHeader, 0, 0);
        direction.add(directionText, 0, 1);

        
        // Floor Status
        GridPane floor = new GridPane();
        floor.getStyleClass().add("group");
        floor.getStyleClass().add("floorButtons");

        Label floorLabel = new Label("Floor");
        Label upLabel = new Label("Up");
        Label downLabel = new Label("Down");

        floor.add(floorLabel, 0, 0);
        floor.add(upLabel, 1, 0);
        floor.add(downLabel, 2, 0);

        for (int i = 0; i < mFloors.size(); i++) {
            Label floorNum = new Label(String.valueOf(i+1));
            Label upRequested = new Label();
            Label downRequested = new Label();

            upRequested.textProperty().bind(
                Bindings.when(mFloors.get(i).upButtonPressedProperty())
                        .then("▲")
                        .otherwise(""));
            downRequested.textProperty().bind(
                Bindings.when(mFloors.get(i).downButtonPressedProperty())
                        .then("▼")
                        .otherwise(""));

            GridPane.setHalignment(floorNum, HPos.CENTER);
            GridPane.setHalignment(upRequested, HPos.CENTER);
            GridPane.setHalignment(downRequested, HPos.CENTER);

            floor.add(floorNum, 0, 1 + i);
            floor.add(upRequested, 1, 1 + i);
            floor.add(downRequested, 2, 1 + i);
        }


        HBox box = new HBox();
        box.getChildren().addAll(stats,manual,direction,floor);

        Tab tab = new Tab(tabName, box);

        tab.setClosable(false);

        return tab;
    }
}
