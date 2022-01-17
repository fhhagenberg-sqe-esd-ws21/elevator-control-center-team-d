package sqelevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
	void test_getElevator()
	{
		Elevator e = model.getElevator(0);
		assertEquals(ElevatorDirection.UNCOMMITTED, e.getCommittedDirection());
		assertEquals(ElevatorDoorStatus.CLOSED, e.getDoorStatus());
	}

	@Test
	void test_getElevatorNrTooSmall()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevator(-100);
		});
	}

	@Test
	void test_getElevatorNrTooBig()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevator(100);
		});
	}

	@Test
	void test_getFloor()
	{
		Floor f = model.getFloor(0);
		assertFalse(f.isDownButtonPressed());
		assertFalse(f.isUpButtonPressed());
	}

	@Test
	void test_getFloorNrTooSmall()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloor(-100);
		});
	}

	@Test
	void test_getFloorNrTooBig()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloor(100);
		});
	}
	
	@Test
	void test_ClockTick()
	{
		model.setClockTick(50);
		assertEquals(50, model.getClockTick());
	}

	@Test
	void test_getSetErrMsg()
	{
		model.setErrMsg("Anaximander");
		assertEquals("Anaximander", model.getErrorMsg());
	}

	@Test
	void test_checkErrMsgProperty()
	{
		model.setErrMsg("Anaximander");
		assertEquals("Anaximander", model.mErrorMsgProperty().get());
	}
		
}
