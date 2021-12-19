package sqelevator;

/**
 * Class representing a single Floor for the elevator model
 * @author Florian Berghuber
 *
 */

public class Floor {
	private boolean upButtonPressed;
	private boolean downButtonPressed;
	
	/**
	 * Constructor of Floor Class
	 * By default both buttons are not set and the floor is marked as serviced
	 */
	public Floor()
	{
		setUpButtonPressed(false);
		setDownButtonPressed(false);
	}

	/**
     * Provides information if the down button was pressed on a particular floor.
     * @return True if the down button was pressed on floor, false otherwise.
     */
	public boolean isDownButtonPressed() {
		return downButtonPressed;
	}

	/**
	 * @param downButtonPressed the downButtonPressed to set
	 */
	public void setDownButtonPressed(boolean downButtonPressed) {
		this.downButtonPressed = downButtonPressed;
	}

	/**
     * Provides information if the up button was pressed on a particular floor.
     * @return True if the up button was pressed on floor, false otherwise.
     */
	public boolean isUpButtonPressed() {
		return upButtonPressed;
	}

	/**
	 * @param upButtonPressed the upButtonPressed to set
	 */
	public void setUpButtonPressed(boolean upButtonPressed) {
		this.upButtonPressed = upButtonPressed;
	}
}
