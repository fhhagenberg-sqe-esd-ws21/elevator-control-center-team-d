package sqelevator;

import java.rmi.RemoteException;

/**
 * Class to handle cyclic updates of low level elevator system.
 * @author Daniel Herzog (s2010567013@fhooe.at)
 */
public class ElevatorModelUpdater {

    IElevator elevatorInterface;
    ElevatorModel model;

    /**
     * Constructor of the class.
     * @param elevatorInterface Interface to communicate with elevator system.
     * @param model Model used to store the information
     */
    public ElevatorModelUpdater(IElevator elevatorInterface, ElevatorModel model) {
        this.elevatorInterface = elevatorInterface;
        this.model = model;
    }

    /**
     * Method for storing the information from elevator system into model
     * This method should be called every 100 milliseconds.
     */
    public void updateModel() {
        for(int i = 0; i < model.getNumOfElevators(); ++i) {
            try {
                model.setCommittedDirection(i, elevatorInterface.getCommittedDirection(i));
                model.setElevatorAccel(i, elevatorInterface.getElevatorAccel(i));
                model.setElevatorDoorStatus(i, elevatorInterface.getElevatorDoorStatus(i));
                model.setElevatorFloor(i, elevatorInterface.getElevatorFloor(i));
                model.setElevatorPosition(i, elevatorInterface.getElevatorPosition(i));
                model.setElevatorSpeed(i, elevatorInterface.getElevatorSpeed(i));
                model.setElevatorWeight(i, elevatorInterface.getElevatorWeight(i));
                model.setElevatorCapacity(i, elevatorInterface.getElevatorCapacity(i));
                model.setTarget(i, elevatorInterface.getTarget(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            updateServicedFloors(i);
            updateElevatorButtons(i);
        }

        for(int i = 0; i < model.getNumOfFloors(); ++i) {
            try {
                model.setFloorButtonDown(i, elevatorInterface.getFloorButtonDown(i));
                model.setFloorButtonUp(i, elevatorInterface.getFloorButtonUp(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }

        try {
            model.setClockTick(elevatorInterface.getClockTick());
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private void updateServicedFloors(int elevatorNumber) {
        for(int i = 0; i < model.getNumOfFloors(); ++i) {
            try {
                model.setServicesFloors(elevatorNumber, i,
                        elevatorInterface.getServicesFloors(elevatorNumber, i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void updateElevatorButtons(int elevatorNumber) {
        for(int i = 0; i < model.getNumOfFloors(); ++i) {
            try {
                model.setFloorRequestedInElevator(elevatorNumber, i,
                        elevatorInterface.getElevatorButton(elevatorNumber, i));
            } catch (RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}