package sqelevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 *
 * @author Lucas Drack S2010567003
 */
public class ElevatorModelTest {
	
    final int nElevators = 5;
    final int nFloors = 10;
    final int floorHeight = 2;
	
	ElevatorModel model = new ElevatorModel(nElevators, nFloors, floorHeight);

	@Test
	void testElevatorModel_GetNumOfElevators()
	{
		assertEquals(nElevators, model.getNumOfElevators());
	}
	
	@Test
	void testElevatorModel_GetNumOfFloors()
	{
		assertEquals(nFloors, model.getNumOfFloors());
	}
	
	@Test
	void testElevatorModel_GetFloorHeight()
	{
		assertEquals(floorHeight, model.getFloorHeight());
	}
	
	@Test
	void testElevatorModel_getCommittedDirectionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getCommittedDirection(-1);
		});
	}
	
	@Test
	void testElevatorModel_getCommittedDirectionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getCommittedDirection(nElevators);
		});
	}
	
	@Test
	void testElevatorModel_setCommittedDirectionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setCommittedDirection(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setCommittedDirectionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setCommittedDirection(nElevators, 5);
		});
	}
	
	@Test
	void testElevatorModel_CommittedDirection()
	{
		model.setCommittedDirection(1, 2);
		assertEquals(2, model.getCommittedDirection(1));
	}
	
	@Test
	void testElevatorModel_getServicesFloorsTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_getServicesFloorsTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(nElevators, 5);
		});
	}
	
	@Test
	void testElevatorModel_getServicesFloorsTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(1, -1);
		});
	}
	
	@Test
	void testElevatorModel_getServicesFloorsTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(1, nFloors);
		});
	}
	
	@Test
	void testElevatorModel_setServicesFloorsTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(-1, 5, true);
		});
	}
	
	@Test
	void testElevatorModel_setServicesFloorsTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(nElevators, 5, true);
		});
	}
	
	@Test
	void testElevatorModel_setServicesFloorsTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(1, -1, true);
		});
	}
	
	@Test
	void testElevatorModel_setServicesFloorsTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(1, nFloors, true);
		});
	}
	
	@Test
	void testElevatorModel_ServicesFloors()
	{
		model.setServicesFloors(2, 9, true);
		assertEquals(true, model.getServicesFloors(2, 9));
	}
	
	@Test
	void testElevatorModel_getTargetTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getTarget(-1);
		});
	}
	
	@Test
	void testElevatorModel_getTargetTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getTarget(nElevators);
		});
	}
	
	@Test
	void testElevatorModel_setTargetTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setTargetTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(nElevators, 5);
		});
	}
	
	@Test
	void testElevatorModel_setTargetTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(1, -1);
		});
	}
	
	@Test
	void testElevatorModel_setTargetTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(1, nFloors);
		});
	}
	
	@Test
	void testElevatorModel_Target()
	{
		model.setTarget(0, 9);
		assertEquals(9, model.getTarget(0));
	}
	
	@Test
	void testElevatorModel_getElevatorAccelTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorAccel(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorAccelTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorAccel(nElevators);
		});
	}
	
	
	@Test
	void testElevatorModel_setElevatorAccelTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorAccel(-1, 923);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorAccelTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorAccel(nElevators, 123);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorAccel()
	{
		model.setElevatorAccel(2, 100);
		assertEquals(100, model.getElevatorAccel(2));
	}
	
	@Test
	void testElevatorModel_getFloorRequestedInElevatorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorRequestedInElevator(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_getFloorRequestedInElevatorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorRequestedInElevator(nElevators, 5);
		});
	}
	
	@Test
	void testElevatorModel_getFloorRequestedInElevatorTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorRequestedInElevator(1, -1);
		});
	}
	
	@Test
	void testElevatorModel_getFloorRequestedInElevatorTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorRequestedInElevator(1, nFloors);
		});
	}
	
	
	@Test
	void testElevatorModel_setFloorRequestedInElevatorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorRequestedInElevator(-1, 4, false);
		});
	}
	
	@Test
	void testElevatorModel_setFloorRequestedInElevatorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorRequestedInElevator(nElevators, 3, false);
		});
	}
	
	@Test
	void testElevatorModel_setFloorRequestedInElevatorTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorRequestedInElevator(0, -1, false);
		});
	}
	
	@Test
	void testElevatorModel_setFloorRequestedInElevatorTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorRequestedInElevator(0, nFloors, false);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorButton()
	{
		model.setFloorRequestedInElevator(1, 6, true);
		assertEquals(true, model.getFloorRequestedInElevator(1, 6));
	}
	
	@Test
	void testElevatorModel_getElevatorDoorStatusTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorDoorStatus(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorDoorStatusTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorDoorStatus(nElevators);
		});
	}
	
	
	@Test
	void testElevatorModel_setElevatorDoorStatusTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorDoorStatus(-1, 923);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorDoorStatusTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorDoorStatus(nElevators, 123);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorDoorStatus()
	{
		model.setElevatorDoorStatus(1, 3);
		assertEquals(3, model.getElevatorDoorStatus(1));
	}
	
	@Test
	void testElevatorModel_getElevatorFloorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorFloor(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorFloorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorFloor(nElevators);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorFloorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorFloorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(nElevators, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorFloorTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(1, -1);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorFloorTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(1, nFloors);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorFloor()
	{
		model.setElevatorFloor(0, 3);
		assertEquals(3, model.getElevatorFloor(0));
	}
	
	@Test
	void testElevatorModel_getElevatorPositionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorPosition(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorPositionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorPosition(nElevators);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorPositionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorPosition(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorPositionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorPosition(nElevators, 5);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorPosition()
	{
		model.setElevatorPosition(1, 256);
		assertEquals(256, model.getElevatorPosition(1));
	}
	
	@Test
	void testElevatorModel_getElevatorSpeedTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorSpeed(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorSpeedTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorSpeed(nElevators);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorSpeedTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorSpeed(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorSpeedTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorSpeed(nElevators, 5);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorSpeed()
	{
		model.setElevatorSpeed(0, 203);
		assertEquals(203, model.getElevatorSpeed(0));
	}
	
	@Test
	void testElevatorModel_getElevatorWeightTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorWeight(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorWeightTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorWeight(nElevators);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorWeightTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorWeight(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorWeightTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorWeight(nElevators, 5);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorWeight()
	{
		model.setElevatorWeight(2, 500);
		assertEquals(500, model.getElevatorWeight(2));
	}
	
	@Test
	void testElevatorModel_getElevatorCapacityTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorCapacity(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorCapacityTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorCapacity(nElevators);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorCapacityTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorCapacity(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorCapacityTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorCapacity(nElevators, 5);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorCapacity()
	{
		model.setElevatorCapacity(2, 10);
		assertEquals(10, model.getElevatorCapacity(2));
	}
	
	@Test
	void testElevatorModel_getFloorButtonDownTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonDown(-1);
		});
	}
	
	@Test
	void testElevatorModel_getFloorButtonDownTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonDown(nFloors);
		});
	}
	
	@Test
	void testElevatorModel_setFloorButtonDownTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonDown(-1, true);
		});
	}
	
	@Test
	void testElevatorModel_setFloorButtonDownTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonDown(nFloors, true);
		});
	}
	
	@Test
	void testElevatorModel_FloorButtonDown()
	{
		model.setFloorButtonDown(2, true);
		assertEquals(true, model.getFloorButtonDown(2));
	}
	
	@Test
	void testElevatorModel_getFloorButtonUpTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonUp(-1);
		});
	}
	
	@Test
	void testElevatorModel_getFloorButtonUpTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonUp(nFloors);
		});
	}
	
	@Test
	void testElevatorModel_setFloorButtonUpTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonUp(-1, true);
		});
	}
	
	@Test
	void testElevatorModel_setFloorButtonUpTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonUp(nFloors, true);
		});
	}
	
	@Test
	void testElevatorModel_FloorButtonUp()
	{
		model.setFloorButtonUp(6, true);
		assertEquals(true, model.getFloorButtonUp(6));
	}
	
	
	@Test
	void testElevatorModel_LoggingLevel()
	{
		model.setLogging(5);
		assertEquals(5, model.getLogging());
	}
	
	@Test
	void testElevatorModel_ClockTick()
	{
		model.setClockTick(50);
		assertEquals(50, model.getClockTick());
	}
		
}
