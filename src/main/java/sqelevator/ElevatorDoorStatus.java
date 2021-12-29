package sqelevator;

/**
 * Enum wrapper for elevator door status.
 * Open    - Doors are open
 * Closed  - Doors are closed
 * Opening - Door are opening
 * Closing - Door are closing
 */
public enum ElevatorDoorStatus {
	OPEN("Open"), 
	CLOSED("Closed"), 
	OPENING("Opening"),
	CLOSING("Closing");

	private String description;

	private ElevatorDoorStatus(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
}

