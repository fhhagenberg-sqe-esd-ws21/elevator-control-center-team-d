package sqelevator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
// import static org.mockito.Mockito

/**
 * @author Lucas Drack
 */
@ExtendWith(MockitoExtension.class)
class RmiWrapperTest {

    @Mock
    private IElevator mockedInterface = mock(IElevator.class);

    @Test
	void testInstantiation() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);

        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
        assertEquals(5, rmiWrapper.getElevatorNum());
        assertEquals(10, rmiWrapper.getFloorNum());
	}

    @Test
	void testNullInstantiationFails() throws RemoteException {
        assertThrows(IllegalArgumentException.class,() -> { new RmiWrapper(null);});
	}

    @Test
	void testInvalidElevatorNumber() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);

        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
        assertThrows(IllegalArgumentException.class,() -> { rmiWrapper.getElevatorAccel(100); });
        assertThrows(IllegalArgumentException.class,() -> { rmiWrapper.getElevatorAccel(-100); });
	}

    @Test
	void testInvalidFloorNumber() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);

        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
        assertThrows(IllegalArgumentException.class,() -> { rmiWrapper.getFloorButtonUp(100); });
        assertThrows(IllegalArgumentException.class,() -> { rmiWrapper.getFloorButtonUp(-100); });
	}

    @Test
	void testSetCommittedDirection() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);
		when(mockedInterface.getCommittedDirection(0)).thenReturn(IElevator.ELEVATOR_DIRECTION_UP);
		when(mockedInterface.getCommittedDirection(1)).thenReturn(IElevator.ELEVATOR_DIRECTION_DOWN);
		when(mockedInterface.getCommittedDirection(2)).thenReturn(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);

        rmiWrapper.setCommittedDirection(0, ElevatorDirection.UP);
        rmiWrapper.setCommittedDirection(1, ElevatorDirection.DOWN);
        rmiWrapper.setCommittedDirection(2, ElevatorDirection.UNCOMMITTED);
        assertEquals(ElevatorDirection.UP, rmiWrapper.getCommittedDirection(0));
        assertEquals(ElevatorDirection.DOWN, rmiWrapper.getCommittedDirection(1));
        assertEquals(ElevatorDirection.UNCOMMITTED, rmiWrapper.getCommittedDirection(2));
	}

    @Test
	void testServicesFloors() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);
		when(mockedInterface.getServicesFloors(0, 5)).thenReturn(false);
        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);

        rmiWrapper.setServicesFloors(0, 5, false);
        assertFalse(rmiWrapper.getServicesFloors(0, 5));
	}

    @Test
	void testSetTarget() throws RemoteException {
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);
		when(mockedInterface.getTarget(0)).thenReturn(5);
        RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);

        rmiWrapper.setTarget(0, 5);
        assertEquals(5, rmiWrapper.getTarget(0));
	}
    
}