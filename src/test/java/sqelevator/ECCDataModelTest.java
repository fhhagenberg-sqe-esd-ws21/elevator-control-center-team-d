package sqelevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Lucas Drack S2010567003
 */
public class ECCDataModelTest {	
	ECCDataModel model;
	
	@BeforeEach
	void setup() {
		model = new ECCDataModel(5, 10, 2);
	}

	@Test
	void test_GetNumOfElevators()
	{
		assertEquals(5, model.getNumOfElevators());
	}
	
	@Test
	void test_GetNumOfFloors()
	{
		assertEquals(10, model.getNumOfFloors());
	}
	
	@Test
	void test_getFloorHeight()
	{
		assertEquals(2, model.getFloorHeight());
	}
	
	@Test
	void test_getCommittedDirectionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getCommittedDirection(-1);
		});
	}
	
	@Test
	void test_getCommittedDirectionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getCommittedDirection(5);
		});
	}
	
	@Test
	void test_setCommittedDirectionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setCommittedDirection(-1, 5);
		});
	}
	
	@Test
	void test_setCommittedDirectionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setCommittedDirection(5, 5);
		});
	}
	
	@Test
	void test_CommittedDirection()
	{
		model.setCommittedDirection(1, 2);
		assertEquals(2, model.getCommittedDirection(1));
	}
	
	@Test
	void test_getServicesFloorsTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(-1, 5);
		});
	}
	
	@Test
	void test_getServicesFloorsTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(5, 5);
		});
	}
	
	@Test
	void test_getServicesFloorsTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(1, -1);
		});
	}
	
	@Test
	void test_getServicesFloorsTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(1, 10);
		});
	}
	
	@Test
	void test_setServicesFloorsTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(-1, 5, true);
		});
	}
	
	@Test
	void test_setServicesFloorsTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(5, 5, true);
		});
	}
	
	@Test
	void test_setServicesFloorsTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(1, -1, true);
		});
	}
	
	@Test
	void test_setServicesFloorsTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(1, 10, true);
		});
	}
	
	@Test
	void test_ServicesFloors()
	{
		model.setServicesFloors(2, 9, true);
		assertEquals(true, model.getServicesFloors(2, 9));
	}
	
	@Test
	void test_getTargetTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getTarget(-1);
		});
	}
	
	@Test
	void test_getTargetTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getTarget(5);
		});
	}
	
	@Test
	void test_setTargetTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(-1, 5);
		});
	}
	
	@Test
	void test_setTargetTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(5, 5);
		});
	}
	
	@Test
	void test_setTargetTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(1, -1);
		});
	}
	
	@Test
	void test_setTargetTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(1, 10);
		});
	}
	
	@Test
	void test_Target()
	{
		model.setTarget(0, 9);
		assertEquals(9, model.getTarget(0));
	}
	
	@Test
	void test_getElevatorAccelTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorAccel(-1);
		});
	}
	
	@Test
	void test_getElevatorAccelTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorAccel(5);
		});
	}
	
	
	@Test
	void test_setElevatorAccelTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorAccel(-1, 923);
		});
	}
	
	@Test
	void test_setElevatorAccelTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorAccel(5, 123);
		});
	}
	
	@Test
	void test_ElevatorAccel()
	{
		model.setElevatorAccel(2, 100);
		assertEquals(100, model.getElevatorAccel(2));
	}
	
	@Test
	void test_getFloorRequestedInElevatorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorRequestedInElevator(-1, 5);
		});
	}
	
	@Test
	void test_getFloorRequestedInElevatorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorRequestedInElevator(5, 5);
		});
	}
	
	@Test
	void test_getFloorRequestedInElevatorTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorRequestedInElevator(1, -1);
		});
	}
	
	@Test
	void test_getFloorRequestedInElevatorTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorRequestedInElevator(1, 10);
		});
	}
	
	
	@Test
	void test_setFloorRequestedInElevatorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorRequestedInElevator(-1, 4, false);
		});
	}
	
	@Test
	void test_setFloorRequestedInElevatorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorRequestedInElevator(5, 3, false);
		});
	}
	
	@Test
	void test_setFloorRequestedInElevatorTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorRequestedInElevator(0, -1, false);
		});
	}
	
	@Test
	void test_setFloorRequestedInElevatorTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorRequestedInElevator(0, 10, false);
		});
	}
	
	@Test
	void test_ElevatorButton()
	{
		model.setFloorRequestedInElevator(1, 6, true);
		assertEquals(true, model.getFloorRequestedInElevator(1, 6));
	}
	
	@Test
	void test_getElevatorDoorStatusTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorDoorStatus(-1);
		});
	}
	
	@Test
	void test_getElevatorDoorStatusTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorDoorStatus(5);
		});
	}
	
	
	@Test
	void test_setElevatorDoorStatusTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorDoorStatus(-1, 923);
		});
	}
	
	@Test
	void test_setElevatorDoorStatusTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorDoorStatus(5, 123);
		});
	}
	
	@Test
	void test_ElevatorDoorStatus()
	{
		model.setElevatorDoorStatus(1, 3);
		assertEquals(3, model.getElevatorDoorStatus(1));
	}
	
	@Test
	void test_getElevatorFloorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorFloor(-1);
		});
	}
	
	@Test
	void test_getElevatorFloorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorFloor(5);
		});
	}
	
	@Test
	void test_setElevatorFloorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(-1, 5);
		});
	}
	
	@Test
	void test_setElevatorFloorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(5, 5);
		});
	}
	
	@Test
	void test_setElevatorFloorTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(1, -1);
		});
	}
	
	@Test
	void test_setElevatorFloorTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(1, 10);
		});
	}
	
	@Test
	void test_ElevatorFloor()
	{
		model.setElevatorFloor(0, 3);
		assertEquals(3, model.getElevatorFloor(0));
	}
	
	@Test
	void test_getElevatorPositionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorPosition(-1);
		});
	}
	
	@Test
	void test_getElevatorPositionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorPosition(5);
		});
	}
	
	@Test
	void test_setElevatorPositionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorPosition(-1, 5);
		});
	}
	
	@Test
	void test_setElevatorPositionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorPosition(5, 5);
		});
	}
	
	@Test
	void test_ElevatorPosition()
	{
		model.setElevatorPosition(1, 256);
		assertEquals(256, model.getElevatorPosition(1));
	}
	
	@Test
	void test_getElevatorSpeedTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorSpeed(-1);
		});
	}
	
	@Test
	void test_getElevatorSpeedTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorSpeed(5);
		});
	}
	
	@Test
	void test_setElevatorSpeedTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorSpeed(-1, 5);
		});
	}
	
	@Test
	void test_setElevatorSpeedTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorSpeed(5, 5);
		});
	}
	
	@Test
	void test_ElevatorSpeed()
	{
		model.setElevatorSpeed(0, 203);
		assertEquals(203, model.getElevatorSpeed(0));
	}
	
	@Test
	void test_getElevatorWeightTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorWeight(-1);
		});
	}
	
	@Test
	void test_getElevatorWeightTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorWeight(5);
		});
	}
	
	@Test
	void test_setElevatorWeightTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorWeight(-1, 5);
		});
	}
	
	@Test
	void test_setElevatorWeightTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorWeight(5, 5);
		});
	}
	
	@Test
	void test_ElevatorWeight()
	{
		model.setElevatorWeight(2, 500);
		assertEquals(500, model.getElevatorWeight(2));
	}
	
	@Test
	void test_getElevatorCapacityTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorCapacity(-1);
		});
	}
	
	@Test
	void test_getElevatorCapacityTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorCapacity(5);
		});
	}
	
	@Test
	void test_setElevatorCapacityTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorCapacity(-1, 5);
		});
	}
	
	@Test
	void test_setElevatorCapacityTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorCapacity(5, 5);
		});
	}
	
	@Test
	void test_ElevatorCapacity()
	{
		model.setElevatorCapacity(2, 10);
		assertEquals(10, model.getElevatorCapacity(2));
	}
	
	@Test
	void test_getFloorButtonDownTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonDown(-1);
		});
	}
	
	@Test
	void test_getFloorButtonDownTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonDown(10);
		});
	}
	
	@Test
	void test_setFloorButtonDownTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonDown(-1, true);
		});
	}
	
	@Test
	void test_setFloorButtonDownTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonDown(10, true);
		});
	}
	
	@Test
	void test_FloorButtonDown()
	{
		model.setFloorButtonDown(2, true);
		assertEquals(true, model.getFloorButtonDown(2));
	}
	
	@Test
	void test_getFloorButtonUpTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonUp(-1);
		});
	}
	
	@Test
	void test_getFloorButtonUpTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonUp(10);
		});
	}
	
	@Test
	void test_setFloorButtonUpTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonUp(-1, true);
		});
	}
	
	@Test
	void test_setFloorButtonUpTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonUp(10, true);
		});
	}
	
	@Test
	void test_FloorButtonUp()
	{
		model.setFloorButtonUp(6, true);
		assertEquals(true, model.getFloorButtonUp(6));
	}
	
	
	@Test
	void test_LoggingLevel()
	{
		model.setLogging(5);
		assertEquals(5, model.getLogging());
	}
	
	@Test
	void test_ClockTick()
	{
		model.setClockTick(50);
		assertEquals(50, model.getClockTick());
	}
		
}
