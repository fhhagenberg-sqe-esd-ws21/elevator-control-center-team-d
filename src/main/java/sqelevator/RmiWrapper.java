package sqelevator;
import sqelevator.Elevator.ElevatorDirection;
import sqelevator.Elevator.ElevatorDoorStatus;
import java.rmi.RemoteException;

public class RmiWrapper implements IRmiWrapper{

	private final IElevator mRmiInterface;
	private final int mNrOfElevators;
	private final int mNrOfFloors;

	public RmiWrapper(IElevator rmiInterface) throws RemoteException {
		if (rmiInterface == null) {
			throw new IllegalArgumentException("IElevator must not be null");
		}
		
		mRmiInterface = rmiInterface;
		mNrOfElevators = mRmiInterface.getElevatorNum();
		mNrOfFloors = mRmiInterface.getFloorNum();
	}

	private void checkElevatorNumber(int elevatorNumber) {
		if (elevatorNumber >= mNrOfElevators || elevatorNumber < 0) {
			throw new IllegalArgumentException("Invalid elevator number " + elevatorNumber);
		}
	}
	
	private void checkFloorNumber(int floor) {
		if (floor >= mNrOfFloors || floor < 0) { 
			throw new IllegalArgumentException("Invalid floor number " + floor);
		}
	}
	
	@Override
	public ElevatorDirection getCommittedDirection(int elevatorNumber) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		
		int direction = mRmiInterface.getCommittedDirection(elevatorNumber);
		return switch (direction) {
			case IElevator.ELEVATOR_DIRECTION_UP -> ElevatorDirection.Up;
			case IElevator.ELEVATOR_DIRECTION_DOWN -> ElevatorDirection.Down;
			case IElevator.ELEVATOR_DIRECTION_UNCOMMITTED -> ElevatorDirection.Uncommitted;
			default -> throw new IllegalArgumentException("Invalid Elevator direction " + direction);
		};
	}

	@Override
	public int getElevatorAccel(int elevatorNumber) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		return mRmiInterface.getElevatorAccel(elevatorNumber);
	}

	@Override
	public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		checkFloorNumber(floor);
		return mRmiInterface.getElevatorButton(elevatorNumber, floor);
	}

	@Override
	public ElevatorDoorStatus getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		
		int door = mRmiInterface.getElevatorDoorStatus(elevatorNumber);

		ElevatorDoorStatus status = switch (door) {
			case IElevator.ELEVATOR_DOORS_OPEN -> ElevatorDoorStatus.Open;
			case IElevator.ELEVATOR_DOORS_CLOSED -> ElevatorDoorStatus.Closed;
			case IElevator.ELEVATOR_DOORS_OPENING -> ElevatorDoorStatus.Opening;
			case IElevator.ELEVATOR_DOORS_CLOSING -> ElevatorDoorStatus.Closing;
			default -> throw new IllegalArgumentException("Invalid Elevator door status " + door);
		};
		return status;
	}

	@Override
	public int getElevatorFloor(int elevatorNumber) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		return mRmiInterface.getElevatorFloor(elevatorNumber);
	}

	@Override
	public int getElevatorNum() {
		return mNrOfElevators;
	}

	@Override
	public int getElevatorPosition(int elevatorNumber) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		return mRmiInterface.getElevatorPosition(elevatorNumber);
	}

	@Override
	public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		return mRmiInterface.getElevatorSpeed(elevatorNumber);
	}

	@Override
	public int getElevatorWeight(int elevatorNumber) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		return mRmiInterface.getElevatorWeight(elevatorNumber);
	}

	@Override
	public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		return mRmiInterface.getElevatorCapacity(elevatorNumber);
	}

	@Override
	public boolean getFloorButtonDown(int floor) throws RemoteException {
		checkFloorNumber(floor);
		return mRmiInterface.getFloorButtonDown(floor);
	}

	@Override
	public boolean getFloorButtonUp(int floor) throws RemoteException {
		checkFloorNumber(floor);
		return mRmiInterface.getFloorButtonUp(floor);
	}

	@Override
	public int getFloorHeight() throws RemoteException {
		return mRmiInterface.getFloorHeight();
	}

	@Override
	public int getFloorNum() {
		return mNrOfFloors;
	}

	@Override
	public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		checkFloorNumber(floor);
		return mRmiInterface.getServicesFloors(elevatorNumber, floor);
	}

	@Override
	public int getTarget(int elevatorNumber) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		return mRmiInterface.getTarget(elevatorNumber);
	}

	@Override
	public void setCommittedDirection(int elevatorNumber, ElevatorDirection direction) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		
		int d = switch (direction) {
			case Up -> IElevator.ELEVATOR_DIRECTION_UP;
			case Down -> IElevator.ELEVATOR_DIRECTION_DOWN;
			case Uncommitted -> IElevator.ELEVATOR_DIRECTION_UNCOMMITTED;
			default -> throw new IllegalArgumentException("Invalid ElevatorDirection");
		};

		mRmiInterface.setCommittedDirection(elevatorNumber, d);
	}

	@Override
	public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		checkFloorNumber(floor);
		mRmiInterface.setServicesFloors(elevatorNumber, floor, service);
	}

	@Override
	public void setTarget(int elevatorNumber, int targetFloor) throws RemoteException {
		checkElevatorNumber(elevatorNumber);
		checkFloorNumber(targetFloor);
		mRmiInterface.setTarget(elevatorNumber, targetFloor);
	}

	@Override
	public long getClockTick() throws RemoteException {
		return mRmiInterface.getClockTick();
	}
}
