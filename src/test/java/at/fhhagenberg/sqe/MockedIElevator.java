package at.fhhagenberg.sqe;

import org.mockito.Mock;
import org.mockito.Mockito;

import sqelevator.IElevator;
import java.rmi.RemoteException;

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





}
