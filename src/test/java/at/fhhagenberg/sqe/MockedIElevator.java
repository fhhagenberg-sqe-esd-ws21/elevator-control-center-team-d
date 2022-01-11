package at.fhhagenberg.sqe;

import org.mockito.Mock;
import org.mockito.Mockito;

import sqelevator.IElevator;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 *
 * @author Lucas Drack S2010567003
 */
public class MockedIElevator {
    
    @Mock
    private IElevator mock;

    public MockedIElevator() {
        mock = Mockito.mock(IElevator.class);
    }

    public void setup(int elevators, int floors) throws RemoteException {
        when(mock.getElevatorNum()).thenReturn(elevators);
        when(mock.getFloorNum()).thenReturn(floors);

        for(int i = 0; i < elevators; i++) {
            initElevatorValues(i, IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, 20, 1000,
            IElevator.ELEVATOR_DOORS_CLOSED, 1, 0, 20, 500, 5);
        }

        for(int i = 0; i < floors; i++) {
            initFloorValues(i, false, false, 3);
        }
    }

    public IElevator getMockedIElevator() {
        return mock;
    }


    public void initElevatorValues(int elevator, int direction, int accel, int capacity,
    int doorStatus, int floor, int pos, int speed, int weight, int target) throws RemoteException {
        when(mock.getCommittedDirection(elevator)).thenReturn(direction);
        when(mock.getElevatorDoorStatus(elevator)).thenReturn(doorStatus);
        when(mock.getElevatorAccel(elevator)).thenReturn(accel);
        when(mock.getElevatorCapacity(elevator)).thenReturn(capacity);
        when(mock.getElevatorFloor(elevator)).thenReturn(floor);
        when(mock.getElevatorPosition(elevator)).thenReturn(pos);
        when(mock.getElevatorSpeed(elevator)).thenReturn(speed);
        when(mock.getElevatorWeight(elevator)).thenReturn(weight);
        when(mock.getTarget(elevator)).thenReturn(target);
    }


    public void initFloorValues(int floor, boolean up, boolean down, int height) throws RemoteException {
        when(mock.getFloorButtonDown(floor)).thenReturn(down);
        when(mock.getFloorButtonUp(floor)).thenReturn(up);
        when(mock.getFloorHeight()).thenReturn(height);
    }


    public void sendCommittedDirection() throws RemoteException {
        doAnswer(invocation -> {
            // Fetch arguments of call to setCommitedDirection
            Integer elevatorNumber = invocation.getArgument(0);
            Integer direction = invocation.getArgument(1);
            
            // Validate that arguments are legal
            assertTrue(elevatorNumber >= 0 && elevatorNumber < mock.getElevatorNum());
            assertTrue(direction >= IElevator.ELEVATOR_DIRECTION_UP && 
            direction <= IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);

            // Change future mock behaviour
            when(mock.getCommittedDirection(elevatorNumber)).thenReturn(direction);
            return null;
        }).when(mock).setCommittedDirection(anyInt(), anyInt());
    }

    public void sendServicesFloors() throws RemoteException {
        doAnswer(invocation -> {
            Integer elevatorNr = invocation.getArgument(0);
            Integer floorNr = invocation.getArgument(1);
            Boolean services = invocation.getArgument(2);
            
            assertTrue(elevatorNr >= 0 && elevatorNr < mock.getElevatorNum());
            assertTrue(floorNr >= 0 && floorNr < mock.getFloorNum());

            when(mock.getServicesFloors(elevatorNr, floorNr)).thenReturn(services);
            return null;
        }).when(mock).setServicesFloors(anyInt(), anyInt(), anyBoolean());
    }

    public void sendTarget() throws RemoteException {
        doAnswer(invocation -> {
            Integer elevatorNr = invocation.getArgument(0);
            Integer target = invocation.getArgument(1);
            
            assertTrue(elevatorNr >= 0 && elevatorNr < mock.getElevatorNum());
            assertTrue(target >= 0 && target < mock.getElevatorNum());

            when(mock.getTarget(elevatorNr)).thenReturn(target);
            return null;
        }).when(mock).setTarget(anyInt(), anyInt());
    }




}
