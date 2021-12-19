package sqelevator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sqelevator.Elevator.ElevatorDirection;
import sqelevator.Elevator.ElevatorDoorStatus;

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
    private IElevator mockedInterface = mock(IElevator.class);

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
		assertEquals(ElevatorDirection.Uncommitted, e.getCommittedDirection());
		assertEquals(ElevatorDoorStatus.Closed, e.getDoorStatus());
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

        assertEquals(ElevatorDirection.Up, e.getCommittedDirection());
		assertEquals(ElevatorDoorStatus.Open, e.getDoorStatus());

        when(mockedInterface.getCommittedDirection(0)).thenReturn(IElevator.ELEVATOR_DIRECTION_DOWN);
		when(mockedInterface.getElevatorDoorStatus(0)).thenReturn(IElevator.ELEVATOR_DOORS_CLOSING);
        updater.updateModel();

        assertEquals(ElevatorDirection.Down, e.getCommittedDirection());
		assertEquals(ElevatorDoorStatus.Closing, e.getDoorStatus());
	}

    // @Test
    // void testThrowRemoteException() throws RemoteException {
    //     when(mockedInterface.getElevatorNum()).thenReturn(5);
	// 	when(mockedInterface.getFloorNum()).thenReturn(10);

    //     RmiWrapper rmiWrapper = new RmiWrapper(mockedInterface);
	// 	ECCFactory fact = new ECCFactory(rmiWrapper);
	// 	ECCDataModel model = fact.createElevatorControlCenter();
	// 	ECCUpdater updater = new ECCUpdater(rmiWrapper, model);
    //     model.getElevator(0).setAccel(12345);

    //     // assertThrows(IllegalArgumentException.class,() -> {updater.updateModel();});
    //     assertEquals(12345, model.getElevator(0).getAccel());
    // }





    // @Test
    // void testUpdateModel_committedDirection_pass() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getCommittedDirection(elevatorNumber)).thenReturn(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getCommittedDirection(elevatorNumber);
    //     assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, model.getCommittedDirection(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorAccel_throwsRemoteException() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setElevatorAccel(elevatorNumber, 12345);

    //     when(mockedInterface.getElevatorAccel(elevatorNumber)).thenThrow(RemoteException.class);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorAccel(elevatorNumber);
    //     assertEquals(12345, model.getElevatorAccel(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorDoorStatus_pass() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getElevatorDoorStatus(elevatorNumber)).thenReturn(IElevator.ELEVATOR_DOORS_OPENING);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorDoorStatus(elevatorNumber);
    //     assertEquals(IElevator.ELEVATOR_DOORS_OPENING, model.getElevatorDoorStatus(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorDoorStatus_throwsRemoteException() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setElevatorDoorStatus(elevatorNumber, IElevator.ELEVATOR_DOORS_CLOSED);

    //     when(mockedInterface.getElevatorDoorStatus(elevatorNumber)).thenThrow(RemoteException.class);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorDoorStatus(elevatorNumber);
    //     assertEquals(IElevator.ELEVATOR_DOORS_CLOSED, model.getElevatorDoorStatus(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorFloor_pass() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getElevatorFloor(elevatorNumber)).thenReturn(9);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorFloor(elevatorNumber);
    //     assertEquals(9, model.getElevatorFloor(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorFloor_throwsRemoteException() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setElevatorFloor(elevatorNumber, 9);

    //     when(mockedInterface.getElevatorFloor(elevatorNumber)).thenThrow(RemoteException.class);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorFloor(elevatorNumber);
    //     assertEquals(9, model.getElevatorFloor(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorPosition_pass() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getElevatorPosition(elevatorNumber)).thenReturn(4712);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorPosition(elevatorNumber);
    //     assertEquals(4712, model.getElevatorPosition(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorPosition_throwsRemoteException() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setElevatorPosition(elevatorNumber, 4712);

    //     when(mockedInterface.getElevatorPosition(elevatorNumber)).thenThrow(RemoteException.class);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorPosition(elevatorNumber);
    //     assertEquals(4712, model.getElevatorPosition(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorSpeed_pass() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getElevatorSpeed(elevatorNumber)).thenReturn(987);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorSpeed(elevatorNumber);
    //     assertEquals(987, model.getElevatorSpeed(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorWeight_pass() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getElevatorWeight(elevatorNumber)).thenReturn(1234);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorWeight(elevatorNumber);
    //     assertEquals(1234, model.getElevatorWeight(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorWeight_throwsRemoteException() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setElevatorWeight(elevatorNumber, 1234);

    //     when(mockedInterface.getElevatorWeight(elevatorNumber)).thenThrow(RemoteException.class);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorWeight(elevatorNumber);
    //     assertEquals(1234, model.getElevatorWeight(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorCapacity_pass() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getElevatorCapacity(elevatorNumber)).thenReturn(1024);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorCapacity(elevatorNumber);
    //     assertEquals(1024, model.getElevatorCapacity(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getElevatorCapacity_throwsRemoteException() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setElevatorCapacity(elevatorNumber, 1025);

    //     when(mockedInterface.getElevatorCapacity(elevatorNumber)).thenThrow(RemoteException.class);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorCapacity(elevatorNumber);
    //     assertEquals(1025, model.getElevatorCapacity(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getTarget_pass() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getTarget(elevatorNumber)).thenReturn(3);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getTarget(elevatorNumber);
    //     assertEquals(3, model.getTarget(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getTarget_throwsRemoteException() throws RemoteException {
    //     int elevatorNumber = 0;
    //     ECCDataModel model = new ECCDataModel(1, 10, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setTarget(elevatorNumber, 4);

    //     when(mockedInterface.getTarget(elevatorNumber)).thenThrow(RemoteException.class);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getTarget(elevatorNumber);
    //     assertEquals(4, model.getTarget(elevatorNumber));
    // }

    // @Test
    // void testUpdateModel_getFloorButtonDown_pass() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getFloorButtonDown(0)).thenReturn(true);
    //     when(mockedInterface.getFloorButtonDown(1)).thenReturn(false);

    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getFloorButtonDown(0);
    //     verify(mockedInterface, times(1)).getFloorButtonDown(1);
    //     assertTrue(model.getFloorButtonDown(0));
    //     assertFalse(model.getFloorButtonDown(1));
    // }

    // @Test
    // void testUpdateModel_getFloorButtonDown_throwsRemoteException() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setFloorButtonDown(0, true);

    //     when(mockedInterface.getFloorButtonDown(0)).thenThrow(RemoteException.class);
    //     when(mockedInterface.getFloorButtonDown(1)).thenThrow(RemoteException.class);

    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getFloorButtonDown(0);
    //     verify(mockedInterface, times(1)).getFloorButtonDown(1);
    //     assertTrue(model.getFloorButtonDown(0));
    //     assertFalse(model.getFloorButtonDown(1));
    // }

    // @Test
    // void testUpdateModel_getFloorButtonUp_pass() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getFloorButtonUp(0)).thenReturn(true);
    //     when(mockedInterface.getFloorButtonUp(1)).thenReturn(false);

    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getFloorButtonUp(0);
    //     verify(mockedInterface, times(1)).getFloorButtonUp(1);
    //     assertTrue(model.getFloorButtonUp(0));
    //     assertFalse(model.getFloorButtonUp(1));
    // }

    // @Test
    // void testUpdateModel_getFloorButtonUp_throwsRemoteException() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setFloorButtonUp(0, true);

    //     when(mockedInterface.getFloorButtonUp(0)).thenThrow(RemoteException.class);
    //     when(mockedInterface.getFloorButtonUp(1)).thenThrow(RemoteException.class);

    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getFloorButtonUp(0);
    //     verify(mockedInterface, times(1)).getFloorButtonUp(1);
    //     assertTrue(model.getFloorButtonUp(0));
    //     assertFalse(model.getFloorButtonUp(1));
    // }

    // @Test
    // void testUpdateModel_getClockTick_pass() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getClockTick()).thenReturn(Integer.toUnsignedLong(47123));
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getClockTick();
    //     assertEquals(47123, model.getClockTick());
    // }

    // @Test
    // void testUpdateModel_getClockTick_throwsRemoteException() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setClockTick(47132);

    //     when(mockedInterface.getClockTick()).thenThrow(RemoteException.class);
    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getClockTick();
    //     assertEquals(47132, model.getClockTick());
    // }

    // @Test
    // void testUpdateModel_getServicesFloors_pass() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getServicesFloors(0,0)).thenReturn(true);
    //     when(mockedInterface.getServicesFloors(0,1)).thenReturn(false);

    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getServicesFloors(0,0);
    //     verify(mockedInterface, times(1)).getServicesFloors(0,1);
    //     assertTrue(model.getServicesFloors(0,0));
    //     assertFalse(model.getServicesFloors(0,1));
    // }

    // @Test
    // void testUpdateModel_getServicesFloors_throwsRemoteException() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setServicesFloors(0,0, false);
    //     model.setServicesFloors(0,1, true);

    //     when(mockedInterface.getServicesFloors(0,0)).thenThrow(RemoteException.class);
    //     when(mockedInterface.getServicesFloors(0,1)).thenThrow(RemoteException.class);

    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getServicesFloors(0,0);
    //     verify(mockedInterface, times(1)).getServicesFloors(0,1);
    //     assertFalse(model.getServicesFloors(0,0));
    //     assertTrue(model.getServicesFloors(0,1));
    // }

    // @Test
    // void testUpdateModel_getElevatorButton_pass() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);

    //     when(mockedInterface.getElevatorButton(0,0)).thenReturn(true);
    //     when(mockedInterface.getElevatorButton(0,1)).thenReturn(false);

    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorButton(0,0);
    //     verify(mockedInterface, times(1)).getElevatorButton(0,1);
    //     assertTrue(model.getFloorRequestedInElevator(0,0));
    //     assertFalse(model.getFloorRequestedInElevator(0,1));
    // }

    // @Test
    // void testUpdateModel_getElevatorButton_throwsRemoteException() throws RemoteException {
    //     ECCDataModel model = new ECCDataModel(1, 2, 2500);
    //     ECCUpdater updater = new ECCUpdater(mockedInterface, model);
    //     model.setFloorRequestedInElevator(0,0, false);
    //     model.setFloorRequestedInElevator(0,1, true);

    //     when(mockedInterface.getElevatorButton(0,0)).thenThrow(RemoteException.class);
    //     when(mockedInterface.getElevatorButton(0,1)).thenThrow(RemoteException.class);

    //     updater.updateModel();

    //     verify(mockedInterface, times(1)).getElevatorButton(0,0);
    //     verify(mockedInterface, times(1)).getElevatorButton(0,1);
    //     assertFalse(model.getFloorRequestedInElevator(0,0));
    //     assertTrue(model.getFloorRequestedInElevator(0,1));
    // }
}