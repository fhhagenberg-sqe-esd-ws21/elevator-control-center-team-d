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
	
	@Test
	void TestAccell()
	{
		Elevator e = new Elevator();
		assertEquals(0, e.getAccel());
		
		e.setAccel(5);
		assertEquals(5, e.getAccel());
	}
	
	@Test
	void TestDoorStatus()
	{
		Elevator e = new Elevator();
		assertEquals(2, e.getDoorStatus());
		
		e.setDoorStatus(1);
		assertEquals(1, e.getDoorStatus());
	}
	
	@Test
	void TestFloor()
	{
		Elevator e = new Elevator();
		assertEquals(0, e.getFloor());
		
		e.setFloor(1);
		assertEquals(1, e.getFloor());
	}
	
	@Test
	void TestPosition()
	{
		Elevator e = new Elevator();
		assertEquals(0, e.getPosition());
		
		e.setPosition(100);
		assertEquals(100, e.getPosition());
	}
	
	@Test
	void TestSpeed()
	{
		Elevator e = new Elevator();
		assertEquals(0, e.getSpeed());
		
		e.setSpeed(10);
		assertEquals(10, e.getSpeed());
	}
	
	@Test
	void TestCapacity()
	{
		Elevator e = new Elevator();
		assertEquals(1, e.getCapacity());
		
		e.setCapacity(10);
		assertEquals(10, e.getCapacity());
	}
	
	@Test
	void TestWeight()
	{
		Elevator e = new Elevator();
		assertEquals(0, e.getWeight());
		
		e.setWeight(100);
		assertEquals(100, e.getWeight());
	}
	
}
