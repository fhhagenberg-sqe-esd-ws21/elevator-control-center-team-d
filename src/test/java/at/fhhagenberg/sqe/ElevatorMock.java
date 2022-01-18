package at.fhhagenberg.sqe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.rmi.RemoteException;

import sqelevator.IElevator;

public class ElevatorMock implements IElevator {

    private final int floorNum;
    private final int elevatorNum;
    private final int floorHeight;

    private int commitedDirection[];
    private int doorStatus[];
    private int targets[];
    private boolean stopButtons[][];
    private boolean servicedFloors[][];
    private int weights[];
    private int accelerations[];
    private int positions[];
    private int floors[];
    private int speeds[];
    private int capacities[];

    private boolean floorButtonsDown[];
    private boolean floorButtonsUp[];


    public ElevatorMock(int nElevators, int nFloors, int floorHeightFt) {
        assertTrue(nElevators >= 0);
        assertTrue(nFloors >= 0);
        assertTrue(floorHeightFt >= 0);

        floorNum = nFloors;
        elevatorNum = nElevators;
        floorHeight = floorHeightFt;

        stopButtons = new boolean[nElevators][nFloors];
        servicedFloors = new boolean[nElevators][nFloors];
        commitedDirection = new int[nElevators];
        doorStatus = new int[nElevators];
        targets = new int[nElevators];
        weights = new int[nElevators];
        accelerations = new int[nElevators];
        positions = new int[nElevators];
        floors = new int[nElevators];
        speeds = new int[nElevators];
        capacities = new int[nElevators];

        floorButtonsDown = new boolean[nFloors];
        floorButtonsUp = new boolean[nFloors];

        for (int i = 0; i < nElevators; i++) {
            commitedDirection[i] = 0;
            doorStatus[i] = IElevator.ELEVATOR_DOORS_OPEN;
            targets[i] = 0;
            weights[i] = 0;
            accelerations[i] = 0;
            positions[i] = 0;
            floors[i] = 0;
            speeds[i] = 0;
            capacities[i] = 0;

            for (int f = 0; f < nFloors; f++) {
                stopButtons[i][f] = false;
                servicedFloors[i][f] = false;
            }
        }

        for (int f = 0; f < nFloors; f++) {
            floorButtonsDown[f] = false;
            floorButtonsUp[f] = false;
        }
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return commitedDirection[elevatorNumber];
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return doorStatus[elevatorNumber];
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return accelerations[elevatorNumber];
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        assertTrue(floor >= 0 && floor < floorNum);

        return stopButtons[elevatorNumber][floor];
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return floors[elevatorNumber];
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        return elevatorNum;
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return positions[elevatorNumber];
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return speeds[elevatorNumber];
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return weights[elevatorNumber];
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        return capacities[elevatorNumber];
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        assertTrue(floor >= 0 && floor < floorNum);
        return floorButtonsDown[floor];
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        assertTrue(floor >= 0 && floor < floorNum);
        return floorButtonsUp[floor];
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        return floorHeight;
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
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        assertTrue(target >= 0 && target < floorNum);

        targets[elevatorNumber] = target;
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        assertTrue(direction >= IElevator.ELEVATOR_DIRECTION_UP && direction <= IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);

        commitedDirection[elevatorNumber] = direction;
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        assertTrue(floor >= 0 && floor < floorNum);
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);

        servicedFloors[elevatorNumber][floor] = service;
    }
    
    public void setDoorStatus(int elevatorNumber, int status) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        assertTrue(status >= IElevator.ELEVATOR_DOORS_OPEN && status <= IElevator.ELEVATOR_DOORS_CLOSING);

        doorStatus[elevatorNumber] = status;
    }

    



    public void setAccel(int elevatorNumber, int acc) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        accelerations[elevatorNumber] = acc;
    }

    public void setStopButton(int elevatorNumber, int floor, boolean button) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        assertTrue(floor >= 0 && floor < floorNum);

        stopButtons[elevatorNumber][floor] = button;
    }

    public void setFloor(int elevatorNumber, int floor) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        floors[elevatorNumber] = floor;
    }

    public void setPosition(int elevatorNumber, int pos) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        positions[elevatorNumber] = pos;
    }

    public void setSpeed(int elevatorNumber, int speed) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        speeds[elevatorNumber] = speed;
    }

    public void setWeight(int elevatorNumber, int weight) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        weights[elevatorNumber] = weight;
    }

    public void setCapacity(int elevatorNumber, int cap) throws RemoteException {
        assertTrue(elevatorNumber >= 0 && elevatorNumber < elevatorNum);
        capacities[elevatorNumber] = cap;
    }

    public void setFloorButtonDown(int floor, boolean val) throws RemoteException {
        assertTrue(floor >= 0 && floor < floorNum);
        floorButtonsDown[floor] = val;
    }

    public void setFloorButtonUp(int floor, boolean val) throws RemoteException {
        assertTrue(floor >= 0 && floor < floorNum);
        floorButtonsUp[floor] = val;
    }






    @Override
    public long getClockTick() throws RemoteException {
        return 0;
    }

}
