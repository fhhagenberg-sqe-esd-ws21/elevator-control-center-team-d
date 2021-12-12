package sqelevator;

import java.util.ArrayList;

/**
 * Datamodel for the Elevator
 * @author Florian
 *
 */

public class Elevator {

	private int committedDirection;
    private int accel;
    private int doorStatus;
    private int floor;
    private int position;
    private int speed;
    private int weight;
    private int capacity;
    private int target;
    private ArrayList<Boolean> servicedFloors;
    private ArrayList<Boolean> pressedFloorButtons;
    
    /**
     * Constructor of Elevator
     * @param numberOfFloors: how many floors does the building have?
     */
    public Elevator(int numberOfFloors)
    {
    	committedDirection = 2;
    	setAccel(0);
    	setDoorStatus(2);
    	setFloor(0);
    	setPosition(0);
    	setSpeed(0);
    	setWeight(0);
    	setCapacity(1);
    	target = 0;
    	servicedFloors = new ArrayList<Boolean>();
    	pressedFloorButtons = new ArrayList<Boolean>();
    	
    	for(int i = 0; i < numberOfFloors; i++)
    	{
    		servicedFloors.add(true);
    		pressedFloorButtons.add(false);
    	}
    }

	/**
	 * @return the committedDirection
	 */
	public int getCommittedDirection() {
		return committedDirection;
	}

	/**
	 * @param committedDirection the committedDirection to set
	 */
	public void setCommittedDirection(int committedDirection) {
		this.committedDirection = committedDirection;
	}

	/**
	 * @return the target
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(int target) {
		this.target = target;
	}

	/**
	 * @return the accel
	 */
	public int getAccel() {
		return accel;
	}

	/**
	 * @param accel the accel to set
	 */
	public void setAccel(int accel) {
		this.accel = accel;
	}

	/**
	 * @return the doorStatus
	 */
	public int getDoorStatus() {
		return doorStatus;
	}

	/**
	 * @param doorStatus the doorStatus to set
	 */
	public void setDoorStatus(int doorStatus) {
		this.doorStatus = doorStatus;
	}

	/**
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * @param floor the floor to set
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * 
	 * @param floorNum: floor number to be checked
	 * @return is floor serviced or not
	 */
	public boolean isFloorServiced(int floorNum)
	{
		if(floorNum <= servicedFloors.size())
		{
			return servicedFloors.get(floorNum);
		}
		return false;
	}
	
	/**
	 * 
	 * @param floorNum: floor number to be checked
	 * @param isServiced: boolean to tell wether the floor is serviced or not
	 */
	public void setFloorServiced(int floorNum, boolean isServiced)
	{
		if(floorNum <= servicedFloors.size())
		{
			servicedFloors.set(floorNum, isServiced);
		}
	}
	
	/**
	 * 
	 * @param floorNum: floor number to be checked
	 * @return is button pressed or not
	 */
	public boolean isFloorButtonPressed(int floorNum)
	{
		if(floorNum <= pressedFloorButtons.size())
		{
			return pressedFloorButtons.get(floorNum);
		}
		return false;
	}
	
	/**
	 * 
	 * @param floorNum: floor number to be checked
	 * @param isServiced: bool the tell if floor should be serviced
	 */
	public void setFloorButton(int floorNum, boolean isServiced)
	{
		if(floorNum <= pressedFloorButtons.size())
		{
			pressedFloorButtons.set(floorNum, isServiced);
		}
	}
	
}
