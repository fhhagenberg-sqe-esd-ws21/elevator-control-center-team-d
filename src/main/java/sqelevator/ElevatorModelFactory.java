package sqelevator;

import java.rmi.RemoteException;

/**
 * Class for creating an Elevator
 * @author Daniel Herzog (s2010567013@fhooe.at)
 */
public class ElevatorModelFactory {

    IElevator elevatorInterface;

    /**
     * Constructor
     * @param elevatorInterface Interface of Elevator
     */
    public ElevatorModelFactory(IElevator elevatorInterface) {
        this.elevatorInterface = elevatorInterface;
    }

    /**
     * Creates a Elevator
     * @return
     * @throws RemoteException
     */
    public ElevatorModel createElevatorControlCenter() throws RemoteException {
        return new ElevatorModel(elevatorInterface.getElevatorNum(), elevatorInterface.getFloorNum(), elevatorInterface.getFloorHeight());
    }
}