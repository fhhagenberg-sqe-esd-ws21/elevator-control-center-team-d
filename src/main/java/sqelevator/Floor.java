package sqelevator;

/**
 * Class representing a single Floor for the elevator model
 * @author Florian Berghuber
 *
 */

public class Floor {
	private boolean upButtonPressed;
	private boolean downButtonPressed;
	private boolean isServiced;
	
	/**
	 * Constructor of Floor Class
	 * By default both buttons are not set and the floor is marked as serviced
	 */
	public Floor()
	{
		setUpButtonPressed(false);
		setDownButtonPressed(false);
		setServiced(true);
	}

	/**
	 * @return the downButtonPressed
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
	 * @return the upButtonPressed
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

	/**
	 * @return the isServiced
	 */
	public boolean isServiced() {
		return isServiced;
	}

	/**
	 * @param isServiced the isServiced to set
	 */
	public void setServiced(boolean isServiced) {
		this.isServiced = isServiced;
	}
}
