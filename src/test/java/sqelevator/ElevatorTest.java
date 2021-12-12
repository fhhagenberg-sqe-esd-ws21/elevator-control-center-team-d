package sqelevator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import at.fhhagenberg.sqe.Elevator;

class ElevatorTest {
	@Test
	void TestCommitedDirection()
	{
		Elevator e = new Elevator();
		assertEquals(2, e.getCommittedDirection());
		
		e.setCommittedDirection(1);
		assertEquals(1, e.getCommittedDirection());
	}
	
	@Test
	void TestTarget()
	{
		Elevator e = new Elevator();
		assertEquals(0, e.getTarget());
		
		e.setTarget(1);
		assertEquals(1, e.getTarget());
	}
	
}
