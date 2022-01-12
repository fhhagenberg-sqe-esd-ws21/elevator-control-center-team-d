package at.fhhagenberg.sqe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.rmi.RemoteException;

import sqelevator.ElevatorDoorStatus;
import sqelevator.IElevator;

public class ElevatorMock implements IElevator {

    private int commitedDirection[];
    private int targets[];
    private boolean servicedFloors[][];
    private int floorNum = 0;
    private int elevatorNum = 0;

    public ElevatorMock(int nElevators, int nFloors) {
        commitedDirection = new int[nElevators];
        targets = new int[nElevators];
        servicedFloors = new boolean[nElevators][nFloors];
        floorNum = nFloors;
        elevatorNum = nElevators;
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return commitedDirection[elevatorNumber];
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return 3;
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        assertTrue(floor >= 0 && floor < floorNum);

        return true;
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return 1; // closed
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);

        return 0;
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        return elevatorNum;
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return 0;
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return 3;
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return 100;
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return 10;
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        assertTrue(floor >= 0 && floor < floorNum);
        return false;
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        assertTrue(floor >= 0 && floor < floorNum);
        return false;
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        return 2;
    }

    @Override
    public int getFloorNum() throws RemoteException {
        return floorNum;
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        assertTrue(floor >= 0 && floor < floorNum);
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);

        return servicedFloors[elevatorNumber][floor];
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);

        return targets[elevatorNumber];
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);

        commitedDirection[elevatorNumber] = direction;
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        assertTrue(floor >= 0 && floor < floorNum);
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);

        servicedFloors[elevatorNumber][floor] = service;

    }

    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        assertTrue(target >= 0 && target < floorNum);

        targets[elevatorNumber] = target;

    }

    @Override
    public long getClockTick() throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

}
