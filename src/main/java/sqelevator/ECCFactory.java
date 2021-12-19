package sqelevator;

import java.rmi.RemoteException;

/**
 * Class for creating an Elevator
 * @author Daniel Herzog (s2010567013@fhooe.at)
 */
public class ECCFactory {

    IElevator elevatorInterface;

    /**
     * Constructor
     * @param elevatorInterface Interface of Elevator
     */
    public ECCFactory(IElevator elevatorInterface) {
        this.elevatorInterface = elevatorInterface;
    }

    /**
     * Creates a Elevator
     * @return new Elevator
     * @throws RemoteException Exception if something goes wrong
     */
    public ECCDataModel createElevatorControlCenter() throws RemoteException {
        return new ECCDataModel(elevatorInterface.getElevatorNum(), elevatorInterface.getFloorNum(), elevatorInterface.getFloorHeight());
    }
}