package sqelevator;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

/**
 * Datamodel for the Elevator
 *
 */
public class Elevator {

	private final int mNrOfFloors;


	// private ElevatorDirection mCommittedDirection;
    // private ElevatorDoorStatus mDoorStatus;
	private ObjectProperty<ElevatorDirection> mCommittedDirection = new SimpleObjectProperty<>(ElevatorDirection.UNCOMMITTED);
	private ObjectProperty<ElevatorDoorStatus> mDoorStatus = new SimpleObjectProperty<>(ElevatorDoorStatus.CLOSED);
    
	private IntegerProperty mAccel = new SimpleIntegerProperty(0);
    private IntegerProperty mFloor = new SimpleIntegerProperty(0);
    private IntegerProperty mPosition = new SimpleIntegerProperty(0);
    private IntegerProperty mSpeed = new SimpleIntegerProperty(0);
    private IntegerProperty mWeight = new SimpleIntegerProperty(0);
    private IntegerProperty mCapacity = new SimpleIntegerProperty(1);
    private IntegerProperty mTarget = new SimpleIntegerProperty(0);

    // private ArrayList<Boolean> mServicedFloors;
    private ListProperty<Boolean> mStopButtons;
	private ListProperty<Boolean> mServicedFloors;
    
    /**
     * Constructor of Elevator
     * @param numberOfFloors: how many floors does the building have?
     */
    public Elevator(int numberOfFloors)
    {
		mNrOfFloors = numberOfFloors;
    	// setCommittedDirection(ElevatorDirection.UNCOMMITTED);
    	// setDoorStatus(ElevatorDoorStatus.CLOSED);

		// Initialize Lists by first creating an observableArrayList.
		// This is needed so the ListProperty can be observed.
		ObservableList<Boolean> serviceList = FXCollections.observableArrayList(new ArrayList<Boolean>());
		mServicedFloors = new SimpleListProperty<Boolean>(serviceList);

		ObservableList<Boolean> stopList = FXCollections.observableArrayList(new ArrayList<Boolean>());
		mStopButtons = new SimpleListProperty<Boolean>(stopList);

    	for(int i = 0; i < numberOfFloors; i++)
    	{
    		mServicedFloors.add(true);
    		mStopButtons.add(false);
    	}
    }

	/**
     * Provides information if the elevator is going up, down or uncommited.
     * @return ElevatorDirection.
     */
	public ElevatorDirection getCommittedDirection() {
		return mCommittedDirection.get();
	}

	/**
	 * @param committedDirection the committedDirection to set; can be either ELEVATOR_DIRECTION_UP, ELEVATOR_DIRECTION_DOWN or ELEVATOR_DIRECTION_UNCOMMITED.
	 */
	public void setCommittedDirection(ElevatorDirection committedDirection) {		
		this.mCommittedDirection.set(committedDirection);
	}

	public ObjectProperty<ElevatorDirection> mCommittedDirectionProperty() {
		return mCommittedDirection;
	}




	/**
     * Returns the door status of the elevator.
     * @return ElevatorDoorStatus.
     */
	public ElevatorDoorStatus getDoorStatus() {
		return mDoorStatus.get();
	}

	/**
	 * @param doorStatus the doorStatus to set
	 */
	public void setDoorStatus(ElevatorDoorStatus doorStatus) {		
		this.mDoorStatus.set(doorStatus);
	}

	public ObjectProperty<ElevatorDoorStatus> mDoorStatusProperty() {
		return mDoorStatus;
	}




	/**
     * Returns the target floor of the elevator.
     * @return Target floor number.
     */
	public int getTarget() {
		return mTarget.get();
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(int target) {
		if(target < 0 || target >= mNrOfFloors)
            throw new IllegalArgumentException();
		
		this.mTarget.set(target);
	}

	public IntegerProperty mTargetProperty() {
		return mTarget;
	}




	/**
     * Provides information about how fast the elevator is going.
     * @return acceleration in m/s.
     */
	public int getAccel() {
		return mAccel.get();
	}

	/**
	 * @param accel the accel to set
	 */
	public void setAccel(int accel) {
		if(accel < 0)
            throw new IllegalArgumentException();
		
		this.mAccel.set(accel);
	}

	public IntegerProperty mAccelProperty() {
		return mAccel;
	}




	/**
     * Returns the floor the elevator is on.
     * @return floor number that the elevator is on.
     */
	public int getFloor() {
		return mFloor.get();
	}

	/**
	 * @param floor the floor that the elevator is on.
	 */
	public void setFloor(int floor) {
		if(floor < 0 || floor >= mNrOfFloors)
            throw new IllegalArgumentException();
		
		this.mFloor.set(floor);
	}

	public IntegerProperty mFloorProperty() {
		return mFloor;
	}




	/**
     * Returns the position of the elevator in m above ground.
     * @return elevator position in m above ground.
     */
	public int getPosition() {
		return mPosition.get();
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		if(position < 0)
            throw new IllegalArgumentException();
		
		this.mPosition.set(position);
	}

	public IntegerProperty mPositionProperty() {
		return mPosition;
	}




	/**
     * Returns the speed of the elevator in m/s.
     * @return elevator speed.
     */
	public int getSpeed() {
		return mSpeed.get();
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.mSpeed.set(speed);
	}

	public IntegerProperty mSpeedProperty() {
		return mSpeed;
	}




	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return mCapacity.get();
	}

	/**
     * Returns the capacity of the elevator.
     * @return elevator capacity.
     */
	public void setCapacity(int capacity) {
		if(capacity < 0)
            throw new IllegalArgumentException();
		
		this.mCapacity.set(capacity);
	}

	public IntegerProperty mCapacityProperty() {
		return mCapacity;
	}




	/**
     * Returns the weight of the elevator in kg.
     * @return elevator weight in kg.
     */
	public int getWeight() {
		return mWeight.get();
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		if(weight < 0)
            throw new IllegalArgumentException();

		this.mWeight.set(weight);
	}

	public IntegerProperty mWeightProperty() {
		return mWeight;
	}



	
	/**
     * Tells you if the elevator services a given floor.
     * @param floor Floornumber.
     * @return True if elevator servies the floor, false otherwise.
     */
	public Boolean isFloorServiced(int floor)
	{
		if(floor < 0 || floor >= mNrOfFloors)
            throw new IllegalArgumentException();
		
		return mServicedFloors.get(floor);
	}
	
	/**
     * Sets an elevator to service a given floor.
	 * @param floor: floor number to be checked
	 * @param isServiced: boolean to tell wether the floor is serviced or not
	 */
	public void setFloorServiced(int floor, Boolean isServiced)
	{
		if(floor < 0 || floor >= mNrOfFloors)
            throw new IllegalArgumentException();
		
		mServicedFloors.set(floor, isServiced);
	}

	public ObservableList<Boolean> mServicedFloorsProperty() {
		return mServicedFloors.get();
	}



	
	/**
	 * 
	 * @param floor: floor number to be checked
	 * @return is button pressed or not
	 */
	public Boolean isStopButtonPressed(int floor)
	{
		if(floor < 0 || floor >= mNrOfFloors)
            throw new IllegalArgumentException();
		
		return mStopButtons.get(floor);
	}
	
	/**
	 * 
	 * @param floor: floor number to be checked
	 * @param stop: bool to tell if floor stop button should be pressed
	 */
	public void setStopButton(int floor, Boolean stop)
	{
		if(floor < 0 || floor >= mNrOfFloors)
            throw new IllegalArgumentException();
			
		mStopButtons.set(floor, stop);
	}

	public ObservableList<Boolean> mStopButtonsProperty() {
		return mStopButtons.get();
	}
	
}
