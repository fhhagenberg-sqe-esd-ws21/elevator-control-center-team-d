package sqelevator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElevatorTest {
	Elevator e;

	@BeforeEach
	void setup() {
		e = new Elevator(3);
	}

	@Test
	void TestCommitedDirection()
	{
		assertEquals(2, e.getCommittedDirection());
		
		e.setCommittedDirection(1);
		assertEquals(1, e.getCommittedDirection());
	}
	
	@Test
	void TestTarget()
	{
		assertEquals(0, e.getTarget());
		
		e.setTarget(1);
		assertEquals(1, e.getTarget());
	}
	
	@Test
	void TestAccell()
	{
		assertEquals(0, e.getAccel());
		
		e.setAccel(5);
		assertEquals(5, e.getAccel());
	}
	
	@Test
	void TestDoorStatus()
	{
		assertEquals(2, e.getDoorStatus());
		
		e.setDoorStatus(1);
		assertEquals(1, e.getDoorStatus());
	}
	
	@Test
	void TestFloor()
	{
		assertEquals(0, e.getFloor());
		
		e.setFloor(1);
		assertEquals(1, e.getFloor());
	}
	
	@Test
	void TestPosition()
	{
		assertEquals(0, e.getPosition());
		
		e.setPosition(100);
		assertEquals(100, e.getPosition());
	}
	
	@Test
	void TestSpeed()
	{
		assertEquals(0, e.getSpeed());
		
		e.setSpeed(10);
		assertEquals(10, e.getSpeed());
	}
	
	@Test
	void TestCapacity()
	{
		assertEquals(1, e.getCapacity());
		
		e.setCapacity(10);
		assertEquals(10, e.getCapacity());
	}
	
	@Test
	void TestWeight()
	{
		assertEquals(0, e.getWeight());
		
		e.setWeight(100);
		assertEquals(100, e.getWeight());
	}
	
	@Test
	void TestServicedFloors()
	{
		for(int i = 0; i < 3; i++)
		{
			assertTrue(e.isFloorServiced(i));
		}
		
		e.setFloorServiced(1, false);
		assertFalse(e.isFloorServiced(1));
		
		assertThrows(IllegalArgumentException.class, () -> {e.isFloorServiced(10);});
	}
	
	@Test
	void TestPressedButtons()
	{
		for(int i = 0; i < 3; i++)
		{
			assertFalse(e.isStopButtonPressed(i));
		}
		
		e.setStopButton(1, true);
		assertTrue(e.isStopButtonPressed(1));
		
		assertThrows(IllegalArgumentException.class,() -> {e.setStopButton(10, false);});
		assertThrows(IllegalArgumentException.class,() -> {e.isStopButtonPressed(10);});
	}
	
}
