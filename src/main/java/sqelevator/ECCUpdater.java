package sqelevator;

import java.rmi.RemoteException;

/**
 * Class to handle cyclic updates of low level elevator system.
 * @author Daniel Herzog (s2010567013@fhooe.at)
 */
public class ECCUpdater {

    RmiWrapper elevatorInterface;
    ECCDataModel model;

    /**
     * Constructor of the class.
     * @param elevatorInterface Interface to communicate with elevator system.
     * @param model Model used to store the information
     */
    public ECCUpdater(RmiWrapper elevatorInterface, ECCDataModel model) {
        if(elevatorInterface == null || model == null)
            throw new IllegalArgumentException("Arguments may not be null.");

        this.elevatorInterface = elevatorInterface;
        this.model = model;
    }

    /**
     * Method for storing the information from elevator system into model
     * This method should be called every 100 milliseconds.
     */
    public void updateModel() {
        try {
            for(int i = 0; i < model.getNumOfElevators(); i++) {
                Elevator e = model.getElevator(i);
                e.setCommittedDirection(elevatorInterface.getCommittedDirection(i));
                e.setDoorStatus(elevatorInterface.getElevatorDoorStatus(i));
                e.setAccel(elevatorInterface.getElevatorAccel(i));
                e.setFloor(elevatorInterface.getElevatorFloor(i));
                e.setPosition(elevatorInterface.getElevatorPosition(i));
                e.setSpeed(elevatorInterface.getElevatorSpeed(i));
                e.setWeight(elevatorInterface.getElevatorWeight(i));
                e.setCapacity(elevatorInterface.getElevatorCapacity(i));
                e.setTarget(elevatorInterface.getTarget(i));
                

                for(int f = 0; f < model.getNumOfFloors(); f++) {
                    e.setFloorServiced(f, elevatorInterface.getServicesFloors(i, f));
                    e.setStopButton(f, elevatorInterface.getElevatorButton(i, f));
                }
            }

            for(int f = 0; f < model.getNumOfFloors(); f++) {
				Floor floor = model.getFloor(f);
                floor.setUpButtonPressed(elevatorInterface.getFloorButtonUp(f));
                floor.setDownButtonPressed(elevatorInterface.getFloorButtonDown(f));
				
			}
        }
        catch(RemoteException e) {
            System.err.println(e.getMessage());
        }

        try {
            model.setClockTick(elevatorInterface.getClockTick());
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
        }

    }
}