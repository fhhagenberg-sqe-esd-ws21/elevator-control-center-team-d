package sqelevator;

import java.rmi.RemoteException;

/**
 * Wraps IElevator. Checks indices for elevatorNumber and floorNumber.
 * Uses enums for Elevator status instead of ints.
 * @author Lucas Drack S2010567003
 */
public interface IRmiWrapper {	
	
	/**
	 * Retrieves the committed direction of the specified elevator (up / down / uncommitted). 
	 * @param elevatorNumber - elevator number whose committed direction is being retrieved 
	 * @return the current direction of the specified elevator
	 * @throws RemoteException when shit fails
	 */
	public ElevatorDirection getCommittedDirection(int elevatorNumber) throws RemoteException; 

	/**
	 * Provides the current acceleration of the specified elevator in feet per sec^2. 
	 * @param elevatorNumber - elevator number whose acceleration is being retrieved 
	 * @return returns the acceleration of the indicated elevator where positive speed is acceleration and negative is deceleration
	 * @throws RemoteException when shit fails
	 */
	public int getElevatorAccel(int elevatorNumber) throws RemoteException;

	/**
	 * Provides the status of a floor request button on a specified elevator (on/off).      
	 * @param elevatorNumber - elevator number whose button status is being retrieved
	 * @param floor - floor number button being checked on the selected elevator 
	 * @return returns boolean to indicate if floor button on the elevator is active (true) or not (false)
	 * @throws RemoteException when shit fails
	 */
	public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException;

	/**
	 * Provides the current status of the doors of the specified elevator (open/closed).      
	 * @param elevatorNumber - elevator number whose door status is being retrieved 
	 * @return returns the door status of the indicated elevator
	 * @throws RemoteException when shit fails
	 */
	public ElevatorDoorStatus getElevatorDoorStatus(int elevatorNumber) throws RemoteException; 

	/**
	 * Provides the current location of the specified elevator to the nearest floor 
	 * @param elevatorNumber - elevator number whose location is being retrieved 
	 * @return returns the floor number of the floor closest to the indicated elevator
	 * @throws RemoteException when shit fails
	 */
	public int getElevatorFloor(int elevatorNumber) throws RemoteException; 

	/**
	 * Retrieves the number of elevators in the building. 
	 * @return total number of elevators
	 * @throws RemoteException when shit fails
	 */
	public int getElevatorNum() throws RemoteException; 

	/**
	 * Provides the current location of the specified elevator in feet from the bottom of the building. 
	 * @param elevatorNumber  - elevator number whose location is being retrieved 
	 * @return returns the location in feet of the indicated elevator from the bottom of the building
	 * @throws RemoteException when shit fails
	 */
	public int getElevatorPosition(int elevatorNumber) throws RemoteException; 

	/**
	 * Provides the current speed of the specified elevator in feet per sec. 
	 * @param elevatorNumber - elevator number whose speed is being retrieved 
	 * @return returns the speed of the indicated elevator where positive speed is up and negative is down
	 * @throws RemoteException when shit fails
	 */
	public int getElevatorSpeed(int elevatorNumber) throws RemoteException; 

	/**
	 * Retrieves the weight of passengers on the specified elevator. 
	 * @param elevatorNumber - elevator number whose service is being retrieved
	 * @return total weight of all passengers in lbs
	 * @throws RemoteException when shit fails
	 */
	public int getElevatorWeight(int elevatorNumber) throws RemoteException; 

	/**
	 * Retrieves the maximum number of passengers that can fit on the specified elevator.
	 * @param elevatorNumber - elevator number whose service is being retrieved
	 * @return number of passengers
	 * @throws RemoteException when shit fails
	 */
	public int getElevatorCapacity(int elevatorNumber) throws RemoteException;
	
	/**
	 * Provides the status of the Down button on specified floor (on/off). 
	 * @param floor - floor number whose Down button status is being retrieved 
	 * @return returns boolean to indicate if button is active (true) or not (false)
	 * @throws RemoteException when shit fails
	 */
	public boolean getFloorButtonDown(int floor) throws RemoteException; 

	/**
	 * Provides the status of the Up button on specified floor (on/off). 
	 * @param floor - floor number whose Up button status is being retrieved 
	 * @return returns boolean to indicate if button is active (true) or not (false)
	 * @throws RemoteException when shit fails
	 */
	public boolean getFloorButtonUp(int floor) throws RemoteException; 

	/**
	 * Retrieves the height of the floors in the building. 
	 * @return floor height (ft)
	 * @throws RemoteException when shit fails
	 */
	public int getFloorHeight() throws RemoteException; 

	/**
	 * Retrieves the number of floors in the building. 
	 * @return total number of floors
	 * @throws RemoteException when shit fails
	 */
	public int getFloorNum() throws RemoteException; 

	/** 
	 * Retrieves whether or not the specified elevator will service the specified floor (yes/no). 
	 * @param elevatorNumber elevator number whose service is being retrieved
	 * @param floor floor whose service status by the specified elevator is being retrieved
	 * @return service status whether the floor is serviced by the specified elevator (yes=true,no=false)
	 * @throws RemoteException when shit fails
	 */
	public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException; 

	/**
	 * Retrieves the floor target of the specified elevator. 
	 * @param elevatorNumber elevator number whose target floor is being retrieved
	 * @return current floor target of the specified elevator
	 * @throws RemoteException when shit fails
	 */
	public int getTarget(int elevatorNumber) throws RemoteException; 

	/**
	 * Sets the committed direction of the specified elevator (up / down / uncommitted). 
	 * @param elevatorNumber elevator number whose committed direction is being set
	 * @param direction direction being set where up=0, down=1 and uncommitted=2
	 * @throws RemoteException when shit fails
	 */
	public void setCommittedDirection(int elevatorNumber, ElevatorDirection direction) throws RemoteException;

	/**
	 * Sets whether or not the specified elevator will service the specified floor (yes/no). 
	 * @param elevatorNumber elevator number whose service is being defined
	 * @param floor floor whose service by the specified elevator is being set
	 * @param service indicates whether the floor is serviced by the specified elevator (yes=true,no=false)
	 * @throws RemoteException when shit fails
	 */
	public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException; 

	/**
	 * Sets the floor target of the specified elevator. 
	 * @param elevatorNumber elevator number whose target floor is being set
	 * @param target floor number which the specified elevator is to target
	 * @throws RemoteException when shit fails
	 */
	public void setTarget(int elevatorNumber, int target) throws RemoteException; 

	/**
	 * Retrieves the current clock tick of the elevator control system. 
	 * @return clock tick
	 * @throws RemoteException when shit fails
	 */
	public long getClockTick() throws RemoteException;
	
}
