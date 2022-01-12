package at.fhhagenberg.sqe;

import java.rmi.RemoteException;

import sqelevator.IElevator;

public class ElevatorMock implements IElevator {

    private int commitedDirection[];
    private int floorNum = 0;

    public ElevatorMock(int nElevators) {
        commitedDirection = new int[nElevators];
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        return commitedDirection[elevatorNumber];
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getFloorNum() throws RemoteException {
        return floorNum;
    }

    
    public void setFloorNum(int floor) throws RemoteException {
        floorNum = floor;
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        commitedDirection[elevatorNumber] = direction;
        
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long getClockTick() throws RemoteException {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
