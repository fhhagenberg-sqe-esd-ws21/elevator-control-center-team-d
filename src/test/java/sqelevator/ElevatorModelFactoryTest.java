package sqelevator;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.rmi.RemoteException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ElevatorModelFactoryTest {

    @Mock
    private IElevator mockedInterface = mock(IElevator.class);

    ECCFactory factory = new ECCFactory(mockedInterface);

    @Test
    void testCreateSucessfull() throws RemoteException{
        ECCDataModel model = factory.createElevatorControlCenter();
        assertNotNull(model);
    }
}
