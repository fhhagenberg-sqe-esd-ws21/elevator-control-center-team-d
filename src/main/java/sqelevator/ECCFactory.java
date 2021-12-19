package sqelevator;

import java.rmi.RemoteException;

/**
 * Class for creating an Elevator
 * @author Daniel Herzog (s2010567013@fhooe.at)
 */
public class ECCFactory {

    RmiWrapper elevatorInterface;

    /**
     * Constructor
     * @param elevatorInterface Interface of Elevator
     */
    public ECCFactory(RmiWrapper elevatorInterface) {
        if(elevatorInterface == null)
            throw new IllegalArgumentException("IElevator must not be null.");

        this.elevatorInterface = elevatorInterface;
    }

    /**
     * Creates an ECCDataModel
     * @return new ECCDataModel
     * @throws RemoteException Exception if something goes wrong
     */
    public ECCDataModel createElevatorControlCenter() throws RemoteException {
        return new ECCDataModel(elevatorInterface.getElevatorNum(), elevatorInterface.getFloorNum(), elevatorInterface.getFloorHeight());
    }
}