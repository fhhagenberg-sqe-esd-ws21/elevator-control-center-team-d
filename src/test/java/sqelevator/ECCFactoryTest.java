package sqelevator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.rmi.RemoteException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ECCFactoryTest {

    @Mock
    private IElevator mockedInterface = mock(IElevator.class);

    ECCFactory fac;

    @Test
    void testInstantiate() throws RemoteException{
        fac = new ECCFactory(mockedInterface);
    }

    @Test
	void testInstantiateWithNullThrows() {
		assertThrows(IllegalArgumentException.class, () -> new ECCFactory(null));
	}

    @Test
    void testCreateECCDataModel() throws RemoteException{
        when(mockedInterface.getElevatorNum()).thenReturn(5);
		when(mockedInterface.getFloorNum()).thenReturn(10);
		when(mockedInterface.getFloorHeight()).thenReturn(3);

        fac = new ECCFactory(mockedInterface);
        ECCDataModel model = fac.createElevatorControlCenter();

        assertEquals(5,  model.getNumOfElevators());
        assertEquals(10, model.getNumOfFloors());
        assertEquals(3,  model.getFloorHeight());
    }
}
