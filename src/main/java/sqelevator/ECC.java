package sqelevator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Lucas Drack S2010567003
 */
public class ECC {
    private ECCDataModel model;
    private ECCUpdater updater;
    private RmiWrapper wrapper;
    private boolean connected = false;

    public void init() {
        try {
            connect();
            model = new ECCFactory(wrapper).createElevatorControlCenter();
            updater = new ECCUpdater(wrapper, model);
            updater.updateModel();
        } 
        catch (NotBoundException e) {
            System.err.println("Remote server not reachable. " + e.getMessage());
        }
        catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
        }
        catch (RemoteException e) {
            System.err.println("Remote exception on connecting: " + e.getMessage());
        }
    }

    // Try to connect to RMI.
    private void connect() throws MalformedURLException, RemoteException, NotBoundException {
        connected = false;
        IElevator rmiInterface = getRmiInterface();
        wrapper = new RmiWrapper(rmiInterface);
        connected = true;
    }

    // Do the actual RMI lookup.
    // Overwrite this is your tests to inject a mocked IElevator.
    protected IElevator getRmiInterface() throws MalformedURLException, RemoteException, NotBoundException {
        return (IElevator)Naming.lookup("rmi://localhost/ElevatorSim");
    }


    // Call this in an interval by using a timer or something
    public void update() {
        if (connected) {
            updater.updateModel();
        }
    }

    public boolean isConnected() {
        return connected;
    }

    // Access to Model - maybe refine this sometime
    public ECCDataModel getModel() {
        if (connected) {
            return model;
        }
        return null;
    }

    public ECCUpdater getUpdater() {
        if (connected) {
            return updater;
        }
        return null;
    }

}
