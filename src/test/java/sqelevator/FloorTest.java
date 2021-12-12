package sqelevator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FloorTest {
	@Test
	void TestConstructor()
	{
		Floor f = new Floor();
		assertFalse(f.isDownButtonPressed());
		assertFalse(f.isUpButtonPressed());
		assertTrue(f.isServiced());
	}
	
	@Test
	void TestDownButton()
	{
		Floor f = new Floor();
		
		f.setDownButtonPressed(true);
		assertTrue(f.isDownButtonPressed());
		
		f.setDownButtonPressed(false);
		assertFalse(f.isDownButtonPressed());
	}
	
	@Test
	void TestUpButton()
	{
		Floor f = new Floor();
		
		f.setUpButtonPressed(true);
		assertTrue(f.isUpButtonPressed());
		
		f.setUpButtonPressed(false);
		assertFalse(f.isUpButtonPressed());
	}
	
	@Test
	void TestServced()
	{
		Floor f = new Floor();
		
		f.setServiced(false);
		assertFalse(f.isServiced());
		
		f.setServiced(true);
		assertTrue(f.isServiced());
	}
}
