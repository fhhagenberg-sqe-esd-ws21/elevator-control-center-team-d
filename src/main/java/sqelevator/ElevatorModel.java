package sqelevator;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sqelevator.Elevator;

/**
 *
 * @author Lucas Drack S2010567003
 */
public class ElevatorModel {

    private List<Elevator> elevators;
    private List<Floor> floors;

    private long clockTick;
    private int floorHeight;
    private int loggingLevel;

    /**
     * Constructor of class ElevatorModel.
     * @param numberOfElevators Represents the number of elevators in the building.
     * @param numberOfFloors Represents the number of floors in the building.
     * @param floorHeight Represents the height of a single floor in m.
     */
    public ElevatorModel(int numberOfElevators, int numberOfFloors, int floorHeight) {
        elevators = new ArrayList<>();
        for(int i = 0; i < numberOfElevators; ++i) {
<<<<<<< Updated upstream
            elevators.add(new Elevator());
=======
            elevators.add(new Elevator(numberOfFloors));
>>>>>>> Stashed changes
        }
        floors = new ArrayList<>(numberOfFloors);
        for(int i = 0; i < numberOfFloors; ++i) {
            floors.add(new Floor());
        }

        this.floorHeight = floorHeight;
        clockTick = 0;
        loggingLevel = 0;
    }

    /**
     * Returns total number of elevators in the building.
     * @return number of elevators.
     */
    public int getNumOfElevators() {
        return elevators.size();
    }

    /**
     * Returns total number of floors in the building.
     * @return number of floors.
     */
    public int getNumOfFloors() {
        return floors.size();
    }

    /**
     * Provides information if the down button was pressed on a particular floor.
<<<<<<< Updated upstream
     * @return True if the down button was pressed on floor, false otherwise.
=======
     * @param floor Floornumber.
     * @return True if the down button was pressed on floor, false otherwise.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public boolean getFloorButtonDown(int floor) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return floors.get(floor).isDownButtonPressed();
    }

    /**
     * Provides information if the up button was pressed on a particular floor.
<<<<<<< Updated upstream
     * @return True if the up button was pressed on floor, false otherwise.
=======
     * @param floor Floornumber.
     * @return True if the up button was pressed on floor, false otherwise.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public boolean getFloorButtonUp(int floor) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return floors.get(floor).isUpButtonPressed();
    }

    /**
     * Provides information if the elevator is going up, down or uncommited.
<<<<<<< Updated upstream
     * @return ELEVATOR_DIRECTION_UP, ELEVATOR_DIRECTION_DOWN or ELEVATOR_DIRECTION_UNCOMMITED.
=======
     * @param elevatorNumber number of the elevator.
     * @return ELEVATOR_DIRECTION_UP, ELEVATOR_DIRECTION_DOWN or ELEVATOR_DIRECTION_UNCOMMITED.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public int getCommittedDirection(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getCommittedDirection();
    }

<<<<<<< Updated upstream

    // public boolean getServicesFloors(int elevatorNumber, int floor) throws IllegalArgumentException {
    //     if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
    //         throw new IllegalArgumentException();

    //     return elevators.get(elevatorNumber).getServicesFloors(floor);
    // }

    /**
     * Returns the target floor of the elevator.
     * @return Target floor number.
=======
    /**
     * Tells you if the elevator services a given floor.
     * @param elevatorNumber number of the elevator.
     * @param floor Floornumber.
     * @return True if elevator servies the floor, false otherwise.
     * @throws IllegalArgumentException
     */
    public boolean getServicesFloors(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).isFloorServiced(floor);
    }

    /**
     * Returns the target floor of the elevator.
     * @param elevatorNumber number of the elevator.
     * @return Target floor number.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public int getTarget(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getTarget();
    }
    
    /**
     * Provides information about how fast the elevator is going.
<<<<<<< Updated upstream
     * @return acceleration in m/s.
=======
     * @param elevatorNumber number of the elevator.
     * @return acceleration in m/s.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public int getElevatorAccel(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || elevatorNumber < 0)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getSpeed();
    }
    
    /**
<<<<<<< Updated upstream
     * Tells you if a certain elevator has requested a certain floor.
     * @return acceleration in m/s.
     */
    // public boolean getFloorRequestedInElevator(int elevatorNumber, int floor) throws IllegalArgumentException {
    //     if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
    //         throw new IllegalArgumentException();

    //     return elevators.get(elevatorNumber).getElevatorButton(floor);
    // }
    
    /**
     * Returns the door status of the elevator.
     * @return ELEVATOR_DOORS_OPEN, ELEVATOR_DOORS_CLOSED, ELEVATOR_DOORS_OPENING or ELEVATOR_DOORS_CLOSING.
=======
     * Tells you if a given floor is requested in the elevator
     * @param elevatorNumber number of the elevator.
     * @param floor Floornumber.
     * @return True if floor is requested, false otherwise.
     * @throws IllegalArgumentException
     */
    public boolean getFloorRequestedInElevator(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).isFloorButtonPressed(floor);
    }
    
    /**
     * Returns the door status of the elevator.
     * @param elevatorNumber number of the elevator.
     * @return ELEVATOR_DOORS_OPEN, ELEVATOR_DOORS_CLOSED, ELEVATOR_DOORS_OPENING or ELEVATOR_DOORS_CLOSING.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public int getElevatorDoorStatus(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getDoorStatus();
    }
    
    /**
     * Returns the floor the elevator is on.
<<<<<<< Updated upstream
     * @return floor number that the elevator is on.
=======
     * @param elevatorNumber number of the elevator.
     * @return floor number that the elevator is on.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public int getElevatorFloor(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getFloor();
    }
    
    /**
     * Returns the position of the elevator in m.
<<<<<<< Updated upstream
     * @return elevator position in m.
=======
     * @param elevatorNumber number of the elevator.
     * @return elevator position in m.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public int getElevatorPosition(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || elevatorNumber < 0)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getPosition();
    }
    
    /**
     * Returns the speed of the elevator in m/s.
<<<<<<< Updated upstream
     * @return elevator speed.
=======
     * @param elevatorNumber number of the elevator.
     * @return elevator speed.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public int getElevatorSpeed(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getSpeed();
    }

    /**
     * Returns the weight of the elevator in pounds.
<<<<<<< Updated upstream
     * @return elevator weight in pounds.
=======
     * @param elevatorNumber number of the elevator.
     * @return elevator weight in pounds.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public int getElevatorWeight(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getWeight();
    }

    /**
     * Returns the position of the elevator in m.
<<<<<<< Updated upstream
     * @return elevator position in m.
=======
     * @param elevatorNumber number of the elevator.
     * @return elevator position in m.
     * @throws IllegalArgumentException
>>>>>>> Stashed changes
     */
    public int getElevatorCapacity(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || elevatorNumber < 0)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getCapacity();
    }



    

<<<<<<< Updated upstream

=======
    /**
     * Sets the down button on a particular floor.
     * @param floor Floornumber.
     * @param value True or false.
     * @throws IllegalArgumentException
     */
>>>>>>> Stashed changes
    public void setFloorButtonDown(int floor, boolean value) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setDownButtonPressed(value);
    }

<<<<<<< Updated upstream

=======
    /**
     * Sets the up button on a particular floor.
     * @param floor Floornumber.
     * @param value True or false.
     * @throws IllegalArgumentException
     */
>>>>>>> Stashed changes
    public void setFloorButtonUp(int floor, boolean value) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setUpButtonPressed(value);
    }

<<<<<<< Updated upstream

=======
    /**
     * Sets the commited direction on a particular elevator.
     * @param elevatorNumber Elevator number.
     * @param direction ELEVATOR_DIRECTION_UP, ELEVATOR_DIRECTION_DOWN or ELEVATOR_DIRECTION_UNCOMMITED.
     * @throws IllegalArgumentException
     */
>>>>>>> Stashed changes
    public void setCommittedDirection(int elevatorNumber, int direction) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setCommittedDirection(direction);
    }

<<<<<<< Updated upstream

    // public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws IllegalArgumentException {
    //     if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
    //         throw new IllegalArgumentException();

    //     elevators.get(elevatorNumber).setServicesFloors(floor, service);
    // }
=======
    /**
     * Configure a particualr elevator to service a particular floor.
     * @param elevatorNumber Elevator number.
     * @param floor Floor number.
     * @param service True or false.
     * @throws IllegalArgumentException
     */
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setFloorServiced(floor, service);
    }
>>>>>>> Stashed changes


    public void setTarget(int elevatorNumber, int target) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || target < 0 || target >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setTarget(target);
    }


    public void setElevatorAccel(int elevatorNumber, int value) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setSpeed(value);
    }


<<<<<<< Updated upstream
    // public void setElevatorButton(int elevatorNumber, int floor, boolean value) throws IllegalArgumentException {
    //     if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
    //         throw new IllegalArgumentException();

    //     elevators.get(elevatorNumber).setElevatorButton(floor, value);
    // }
=======
    public void setFloorRequestedInElevator(int elevatorNumber, int floor, boolean value) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setFloorButton(floor, value);
    }
>>>>>>> Stashed changes


    public void setElevatorDoorStatus(int elevatorNumber, int doorStatus) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setDoorStatus(doorStatus);
    }


    public void setElevatorFloor(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setFloor(floor);
    }


    public void setElevatorPosition(int elevatorNumber, int position) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setPosition(position);
    }


    public void setElevatorSpeed(int elevatorNumber, int speed) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setSpeed(speed);
    }


    public void setElevatorWeight(int elevatorNumber, int weight) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setWeight(weight);
    }


    public void setElevatorCapacity(int elevatorNumber, int capacity) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setCapacity(capacity);
    }

    
    public int getLogging() {
        return this.loggingLevel;
    }
    

    public void setLogging(int loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    
    public long getClockTick() {
        return this.clockTick;
    }
    

    public void setClockTick(long clockTick) {
        this.clockTick = clockTick;
    }

    
    public int getFloorHeight() {
        return this.floorHeight;
    }
}