package sqelevator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Class representing a single Floor for the elevator model
 * @author Florian Berghuber
 *
 */

public class Floor {
	private BooleanProperty upButtonPressed = new SimpleBooleanProperty(false);
	private BooleanProperty downButtonPressed = new SimpleBooleanProperty(false);
	// private boolean upButtonPressed;
	// private boolean downButtonPressed;
	
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
		return downButtonPressed.get();
	}

	/**
	 * @param downButtonPressed the downButtonPressed to set
	 */
	public void setDownButtonPressed(boolean downButtonPressed) {
		this.downButtonPressed.set(downButtonPressed);
	}

	public BooleanProperty downButtonPressedProperty() {
		return downButtonPressed;
	}



	/**
     * Provides information if the up button was pressed on a particular floor.
     * @return True if the up button was pressed on floor, false otherwise.
     */
	public boolean isUpButtonPressed() {
		return upButtonPressed.get();
	}

	/**
	 * @param upButtonPressed the upButtonPressed to set
	 */
	public void setUpButtonPressed(boolean upButtonPressed) {
		this.upButtonPressed.set(upButtonPressed);
	}

	public BooleanProperty upButtonPressedProperty() {
		return upButtonPressed;
	}
}
