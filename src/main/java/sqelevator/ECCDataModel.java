package sqelevator;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lucas Drack S2010567003
 */
public class ECCDataModel {

    private final int mNrOfElevators;
    private final int mNrOfFloors;

    private List<Elevator> elevators;
    private List<Floor> floors;

    private long clockTick;
    private int floorHeight;

    private StringProperty mErrorMsg = new SimpleStringProperty("");

    /**
     * Constructor of class ElevatorModel.
     * @param numberOfElevators Represents the number of elevators in the building.
     * @param numberOfFloors Represents the number of floors in the building.
     * @param floorHeight Represents the height of a single floor in m.
     */
    public ECCDataModel(int numberOfElevators, int numberOfFloors, int floorHeight) {
		mNrOfElevators = numberOfElevators;
		mNrOfFloors = numberOfFloors;

        elevators = new ArrayList<>();
        for(int i = 0; i < numberOfElevators; ++i) {
            elevators.add(new Elevator(numberOfFloors));
        }
        floors = new ArrayList<>(numberOfFloors);
        for(int i = 0; i < numberOfFloors; ++i) {
            floors.add(new Floor());
        }

        this.floorHeight = floorHeight;
        clockTick = 0;
    }

    /**
     * Returns total number of elevators in the building.
     * @return number of elevators.
     */
    public int getNumOfElevators() {
        return mNrOfElevators;
    }

    /**
     * Returns total number of floors in the building.
     * @return number of floors.
     */
    public int getNumOfFloors() {
        return mNrOfFloors;
    }

    /**
     * Gives access to an elevator with specified number.
     * @param elevatorNumber number of the elevator.
     * @return Elevator object.
     */
    public Elevator getElevator(int elevatorNumber) {
        if(elevatorNumber < 0 || elevatorNumber >= mNrOfElevators)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber);
    }

    /**
     * Gives access to a floor with specified number.
     * @param floor Floornumber.
     * @return Floor object.
     */
    public Floor getFloor(int floor) {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return floors.get(floor);
    }



    /**
     * Gets the clock tick of internal clock.
     * @return clock tick.
     */
    public long getClockTick() {
        return this.clockTick;
    }
    
    /**
     * Sets the clock tick of internal clock.
     * @param clockTick clock tick.
     */
    public void setClockTick(long clockTick) {
        this.clockTick = clockTick;
    }

    /**
     * Gets the floor height in m.
     * @return floor height.
     */
    public int getFloorHeight() {
        return this.floorHeight;
    }




    public String getErrorMsg() {
		return mErrorMsg.get();
	}

	public void setErrMsg(String errMsg) {		
		this.mErrorMsg.set(errMsg);
	}

	public StringProperty mErrorMsgProperty() {
		return mErrorMsg;
	}


    // Returns the elevator list for easy access in GUI
    public List<Elevator> getElevatorList() {
        return elevators;
    }

    public List<Floor> getFloorList() {
        return floors;
    }







}