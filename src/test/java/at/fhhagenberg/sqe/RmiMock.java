package at.fhhagenberg.sqe;

import org.mockito.Mock;

import sqelevator.IRmiWrapper;
import sqelevator.ElevatorDirection;
import sqelevator.ElevatorDoorStatus;

import java.rmi.RemoteException;

import static org.mockito.Mockito.when;

public class RmiMock {
    
    private IRmiWrapper RmiMock;

    public RmiMock(IRmiWrapper iRmiMock) throws RemoteException {
        RmiMock = iRmiMock;
    }


    public void setup(int elevators, int floors) throws RemoteException {
        when(RmiMock.getElevatorNum()).thenReturn(elevators);
        when(RmiMock.getFloorNum()).thenReturn(floors);

        for(int i = 0; i < elevators; i++) {
            initElevatorValues(i, ElevatorDirection.DOWN, 20, 1000, ElevatorDoorStatus.CLOSED, 1, 0, 20, 500, 5);
        }

        for(int i = 0; i < floors; i++) {
            initFloorValues(i, false, false, 3);
        }
    }


    public void initElevatorValues(int elevator, ElevatorDirection direction, int accel, int capacity, ElevatorDoorStatus doorStatus,
                                  int floor, int pos, int speed, int weight, int target) throws RemoteException {
        when(RmiMock.getCommittedDirection(elevator)).thenReturn(direction);
        when(RmiMock.getElevatorDoorStatus(elevator)).thenReturn(doorStatus);
        when(RmiMock.getElevatorAccel(elevator)).thenReturn(accel);
        when(RmiMock.getElevatorCapacity(elevator)).thenReturn(capacity);
        when(RmiMock.getElevatorFloor(elevator)).thenReturn(floor);
        when(RmiMock.getElevatorPosition(elevator)).thenReturn(pos);
        when(RmiMock.getElevatorSpeed(elevator)).thenReturn(speed);
        when(RmiMock.getElevatorWeight(elevator)).thenReturn(weight);
        when(RmiMock.getTarget(elevator)).thenReturn(target);

        // for(var button : stopButtons ) {
        //     lenient().when(IElevatorMock.getElevatorButton(elevator, button)).thenReturn(true);
        // }
    }


    public void initFloorValues(int floor, boolean up, boolean down, int height) throws RemoteException {
        when(RmiMock.getFloorButtonDown(floor)).thenReturn(down);
        when(RmiMock.getFloorButtonUp(floor)).thenReturn(up);
        when(RmiMock.getFloorHeight()).thenReturn(height);
    }



}
