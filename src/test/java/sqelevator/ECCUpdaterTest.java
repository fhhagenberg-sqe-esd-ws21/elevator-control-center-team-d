package sqelevator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
// import static org.mockito.Mockito

/**
 * Class for creating an Elevator
 * @author Daniel Herzog (s2010567013@fhooe.at)
 */
@ExtendWith(MockitoExtension.class)
class ECCUpdaterTest {

    @Mock
    private IElevator mockedInterface;

    @Test
	void testInstantiation() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);
		when(mockedInterface.getCommittedDirection(anyInt())).thenReturn(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
		when(mockedInterface.getElevatorDoorStatus(anyInt())).thenReturn(IElevator.ELEVATOR_DOORS_CLOSED);
		when(mockedInterface.getTarget(anyInt())).thenReturn(5);

        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
		ECCFactory fact = new ECCFactory(rmiWrapper);
		ECCDataModel model = fact.createElevatorControlCenter();
		ECCUpdater updater = new ECCUpdater(rmiWrapper, model);
		Elevator e = model.getElevator(0);
        updater.updateModel();

		assertEquals(5, model.getNumOfElevators());
		assertEquals(10, model.getNumOfFloors());
		assertEquals(ElevatorDirection.UNCOMMITTED, e.getCommittedDirection());
		assertEquals(ElevatorDoorStatus.CLOSED, e.getDoorStatus());
		assertEquals(5, e.getTarget());
	}

    @Test
	void testUpdateDirectionAndDoors() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);
		when(mockedInterface.getCommittedDirection(anyInt())).thenReturn(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
		when(mockedInterface.getElevatorDoorStatus(anyInt())).thenReturn(IElevator.ELEVATOR_DOORS_CLOSED);

        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
		ECCFactory fact = new ECCFactory(rmiWrapper);
		ECCDataModel model = fact.createElevatorControlCenter();
		ECCUpdater updater = new ECCUpdater(rmiWrapper, model);

		when(mockedInterface.getCommittedDirection(0)).thenReturn(IElevator.ELEVATOR_DIRECTION_UP);
		when(mockedInterface.getElevatorDoorStatus(0)).thenReturn(IElevator.ELEVATOR_DOORS_OPEN);
        updater.updateModel();
		Elevator e = model.getElevator(0);

        assertEquals(ElevatorDirection.UP, e.getCommittedDirection());
		assertEquals(ElevatorDoorStatus.OPEN, e.getDoorStatus());

        when(mockedInterface.getCommittedDirection(0)).thenReturn(IElevator.ELEVATOR_DIRECTION_DOWN);
		when(mockedInterface.getElevatorDoorStatus(0)).thenReturn(IElevator.ELEVATOR_DOORS_CLOSING);
        updater.updateModel();

        assertEquals(ElevatorDirection.DOWN, e.getCommittedDirection());
		assertEquals(ElevatorDoorStatus.CLOSING, e.getDoorStatus());
	}

    @Test
    void testNullInstantiationThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            ECCUpdater u = new ECCUpdater(null, new ECCDataModel(3, 5, 10));
        });
        assertThrows(IllegalArgumentException.class, () -> {
        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
        ECCUpdater u = new ECCUpdater(rmiWrapper, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ECCUpdater u = new ECCUpdater(null, null);
        });
    }

    @Test
	void testSendElevatorTarget() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);
		when(mockedInterface.getCommittedDirection(anyInt())).thenReturn(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
		when(mockedInterface.getElevatorDoorStatus(anyInt())).thenReturn(IElevator.ELEVATOR_DOORS_CLOSED);
		when(mockedInterface.getTarget(0)).thenReturn(5);

        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
		ECCFactory fact = new ECCFactory(rmiWrapper);
		ECCDataModel model = fact.createElevatorControlCenter();
		ECCUpdater updater = new ECCUpdater(rmiWrapper, model);
		Elevator e = model.getElevator(0);

        updater.updateModel();
        assertEquals(5, e.getTarget());
		
        updater.sendElevatorTarget(0, 1);
		when(mockedInterface.getTarget(0)).thenReturn(1);
        updater.updateModel();
        assertEquals(1, e.getTarget());
	}

    @Test
	void testSendServicesFloors() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);
		when(mockedInterface.getCommittedDirection(anyInt())).thenReturn(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
		when(mockedInterface.getElevatorDoorStatus(anyInt())).thenReturn(IElevator.ELEVATOR_DOORS_CLOSED);
		lenient().when(mockedInterface.getServicesFloors(0, 5)).thenReturn(false);

        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
		ECCFactory fact = new ECCFactory(rmiWrapper);
		ECCDataModel model = fact.createElevatorControlCenter();
		ECCUpdater updater = new ECCUpdater(rmiWrapper, model);
		Elevator e = model.getElevator(0);

        updater.updateModel();
        assertEquals(false, e.isFloorServiced(5));
		
        updater.sendServicesFloors(0, 5, true);
		lenient().when(mockedInterface.getServicesFloors(0, 5)).thenReturn(true);
        updater.updateModel();
        assertEquals(true, e.isFloorServiced(5));
	}

    @Test
	void testSendCommitedDirection() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);
		when(mockedInterface.getCommittedDirection(anyInt())).thenReturn(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
		when(mockedInterface.getElevatorDoorStatus(anyInt())).thenReturn(IElevator.ELEVATOR_DOORS_CLOSED);
		when(mockedInterface.getCommittedDirection(0)).thenReturn(IElevator.ELEVATOR_DIRECTION_UP);

        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
		ECCFactory fact = new ECCFactory(rmiWrapper);
		ECCDataModel model = fact.createElevatorControlCenter();
		ECCUpdater updater = new ECCUpdater(rmiWrapper, model);
		Elevator e = model.getElevator(0);

        updater.updateModel();
        assertEquals(ElevatorDirection.UP, e.getCommittedDirection());
		
        updater.sendCommitedDirection(0, ElevatorDirection.DOWN);
		when(mockedInterface.getCommittedDirection(0)).thenReturn(IElevator.ELEVATOR_DIRECTION_DOWN);
        updater.updateModel();
        assertEquals(ElevatorDirection.DOWN, e.getCommittedDirection());
	}

    
}