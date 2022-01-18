package sqelevator;

import org.junit.jupiter.api.Test;
import at.fhhagenberg.sqe.ElevatorMock;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ECCTest {

    private ECC ecc;

    @Test
    void testConnectToMock() {
        assertDoesNotThrow(() -> {
            ecc = new ECC() {
                @Override
                protected IElevator getRmiInterface() throws RemoteException  {
                    return new ElevatorMock(3, 10, 100);
                }
            };
            ecc.init();
            ecc.update();
        });
    }

    // Note: When RMI server is down, this will lead to an exception in
    // getRmiInterface() which is catched in ECC.init()
    @Test
    void testConnectToRMI() {
        assertDoesNotThrow(() -> {
            ecc = new ECC();
            ecc.init();
        });
    }

    @Test
    void testHandleNotBoundException() {
        assertDoesNotThrow(() -> {
            ecc = new ECC() {
                @Override
                protected IElevator getRmiInterface() throws NotBoundException  {
                    throw new NotBoundException();
                }
            };
            ecc.init();
        });
    }

    @Test
    void testHandleMalformedURLException() {
        assertDoesNotThrow(() -> {
            ecc = new ECC() {
                @Override
                protected IElevator getRmiInterface() throws MalformedURLException  {
                    throw new MalformedURLException();
                }
            };
            ecc.init();
        });
    }

    @Test
    void testHandleRemoteException() {
        assertDoesNotThrow(() -> {
            ecc = new ECC() {
                @Override
                protected IElevator getRmiInterface() throws RemoteException  {
                    throw new RemoteException();
                }
            };
            ecc.init();
        });
    }

    @Test
    void testModelNotConnected() {
        assertDoesNotThrow(() -> {
            ecc = new ECC() {
                @Override
                protected IElevator getRmiInterface() throws RemoteException  {
                    return new ElevatorMock(3, 10, 100);
                }
            };
            ecc.update();
            assertNull(ecc.getModel());

            ecc.init();
            ecc.update();
            assertNotNull(ecc.getModel());
        });            
    }
    
    @Test
    void testGetModel() {
        assertDoesNotThrow(() -> {
            ecc = new ECC() {
                @Override
                protected IElevator getRmiInterface() throws RemoteException  {
                    return new ElevatorMock(3, 10, 100);
                }
            };
            assertNull(ecc.getModel());

            ecc.init();
            assertNotNull(ecc.getModel());
        });            
    }
}
