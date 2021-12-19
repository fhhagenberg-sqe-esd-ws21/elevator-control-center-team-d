package sqelevator;

import java.util.ArrayList;
import sqelevator.IElevator;

/**
 * Datamodel for the Elevator
 * @author Florian
 *
 */

public class Elevator {

	private final int mNrOfFloors;
	private int mCommittedDirection;
    private int mAccel;
    private int mDoorStatus;
    private int mFloor;
    private int mPosition;
    private int mSpeed;
    private int mWeight;
    private int mCapacity;
    private int mTarget;
    private ArrayList<Boolean> mServicedFloors;
    private ArrayList<Boolean> mStopButtons;
    
    /**
     * Constructor of Elevator
     * @param numberOfFloors: how many floors does the building have?
     */
    public Elevator(int numberOfFloors)
    {
		mNrOfFloors = numberOfFloors;
    	setCommittedDirection(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
    	setAccel(0);
    	setDoorStatus(IElevator.ELEVATOR_DOORS_CLOSED);
    	setFloor(0);
    	setPosition(0);
    	setSpeed(0);
    	setWeight(0);
    	setCapacity(1);
    	setTarget(0);
    	mServicedFloors = new ArrayList<Boolean>();
    	mStopButtons = new ArrayList<Boolean>();
    	
    	for(int i = 0; i < numberOfFloors; i++)
    	{
    		mServicedFloors.add(true);
    		mStopButtons.add(false);
    	}
    }

	/**
     * Provides information if the elevator is going up, down or uncommited.
     * @return ELEVATOR_DIRECTION_UP, ELEVATOR_DIRECTION_DOWN or ELEVATOR_DIRECTION_UNCOMMITED.
     */
	public int getCommittedDirection() {
		return mCommittedDirection;
	}

	/**
	 * @param committedDirection the committedDirection to set; can be either ELEVATOR_DIRECTION_UP, ELEVATOR_DIRECTION_DOWN or ELEVATOR_DIRECTION_UNCOMMITED.
	 */
	public void setCommittedDirection(int committedDirection) {
		if(committedDirection < IElevator.ELEVATOR_DIRECTION_UP || committedDirection > IElevator.ELEVATOR_DIRECTION_UNCOMMITTED)
            throw new IllegalArgumentException();
		
		this.mCommittedDirection = committedDirection;
	}

	/**
     * Returns the target floor of the elevator.
     * @return Target floor number.
     */
	public int getTarget() {
		return mTarget;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(int target) {
		if(target < 0 || target >= mNrOfFloors)
            throw new IllegalArgumentException();
		
		this.mTarget = target;
	}

	/**
     * Provides information about how fast the elevator is going.
     * @return acceleration in m/s.
     */
	public int getAccel() {
		return mAccel;
	}

	/**
	 * @param accel the accel to set
	 */
	public void setAccel(int accel) {
		if(accel < 0)
            throw new IllegalArgumentException();
		
		this.mAccel = accel;
	}

	/**
     * Returns the door status of the elevator.
     * @return ELEVATOR_DOORS_OPEN, ELEVATOR_DOORS_CLOSED, ELEVATOR_DOORS_OPENING or ELEVATOR_DOORS_CLOSING.
     */
	public int getDoorStatus() {
		return mDoorStatus;
	}

	/**
	 * @param doorStatus the doorStatus to set
	 */
	public void setDoorStatus(int doorStatus) {
		if(doorStatus < IElevator.ELEVATOR_DOORS_OPEN || doorStatus > IElevator.ELEVATOR_DOORS_CLOSING)
            throw new IllegalArgumentException();
		
		this.mDoorStatus = doorStatus;
	}

	/**
     * Returns the floor the elevator is on.
     * @return floor number that the elevator is on.
     */
	public int getFloor() {
		return mFloor;
	}

	/**
	 * @param floor the floor that the elevator is on.
	 */
	public void setFloor(int floor) {
		if(floor < 0 || floor >= mNrOfFloors)
            throw new IllegalArgumentException();
		
		this.mFloor = floor;
	}

	/**
     * Returns the position of the elevator in m above ground.
     * @return elevator position in m above ground.
     */
	public int getPosition() {
		return mPosition;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		if(position < 0)
            throw new IllegalArgumentException();
		
		this.mPosition = position;
	}

	/**
     * Returns the speed of the elevator in m/s.
     * @return elevator speed.
     */
	public int getSpeed() {
		return mSpeed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		if(speed < 0)
            throw new IllegalArgumentException();
		
		this.mSpeed = speed;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return mCapacity;
	}

	/**
     * Returns the capacity of the elevator.
     * @return elevator capacity.
     */
	public void setCapacity(int capacity) {
		if(capacity < 0)
            throw new IllegalArgumentException();
		
		this.mCapacity = capacity;
	}

	/**
     * Returns the weight of the elevator in kg.
     * @return elevator weight in kg.
     */
	public int getWeight() {
		return mWeight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		if(weight < 0)
            throw new IllegalArgumentException();

		this.mWeight = weight;
	}
	
	/**
     * Tells you if the elevator services a given floor.
     * @param floor Floornumber.
     * @return True if elevator servies the floor, false otherwise.
     */
	public boolean isFloorServiced(int floor)
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
	public void setFloorServiced(int floor, boolean isServiced)
	{
		if(floor < 0 || floor >= mNrOfFloors)
            throw new IllegalArgumentException();
		
		mServicedFloors.set(floor, isServiced);
	}
	
	/**
	 * 
	 * @param floor: floor number to be checked
	 * @return is button pressed or not
	 */
	public boolean isStopButtonPressed(int floor)
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
	public void setStopButton(int floor, boolean stop)
	{
		if(floor < 0 || floor >= mNrOfFloors)
            throw new IllegalArgumentException();
			
		mStopButtons.set(floor, stop);
	}
	
}
