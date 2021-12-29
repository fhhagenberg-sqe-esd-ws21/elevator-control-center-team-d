package sqelevator;

/**
	 * Enum wrapper for elevator direction status.
	 * Up          - Elevator is moving up
	 * Down        - Elevator is moving down
	 * Uncommitted - Elevator is currently not moving
	 */
	public enum ElevatorDirection {
		UP("Up"), 
		DOWN("Down"), 
		UNCOMMITTED("Uncommitted");

		private String description;

        private ElevatorDirection(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
	}

