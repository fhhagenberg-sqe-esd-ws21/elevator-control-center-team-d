package at.fhhagenberg.sqe;

import java.rmi.RemoteException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sqelevator.ECC;
import sqelevator.IElevator;

@ExtendWith(ApplicationExtension.class)
public class AppTest {

    private App app;
    private ElevatorMock elevatorMock;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public void start(Stage stage) {
        app = new App() {
            @Override
            protected ECC getECC() {
                return new ECC() {
                    @Override
                    protected IElevator getRmiInterface() throws RemoteException  {
                        // This is where the actual mock is injected into the App.
                        // ElevatorMock implements IElevator with all getters and 
                        // setters.
                        elevatorMock = new ElevatorMock(3, 10, 100);
                        return elevatorMock;
                    }
                };
            }
        };

        app.start(stage);
    }

    /**
     * @param robot - Will be injected by the test runner.
     * @throws InterruptedException
     */
    @Test
    public void testGoButtonClick(FxRobot robot) throws InterruptedException {
        robot.clickOn("#floorComboBox0");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#goButton0");
        // Thread.sleep(20);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#TargetVal0", LabeledMatchers.hasText("2"));
    }

    @Test
    public void testPayload(FxRobot robot) throws InterruptedException, RemoteException {
        elevatorMock.setWeight(0, 4711);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#Payload0", LabeledMatchers.hasText("4711"));
    }

    @Test
    public void testSpeed(FxRobot robot) throws InterruptedException, RemoteException {
        elevatorMock.setSpeed(0, 4711);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#Speed0", LabeledMatchers.hasText("4711"));
    }

    @Test
    public void testDoor(FxRobot robot) throws InterruptedException, RemoteException {
        elevatorMock.setDoorStatus(0, IElevator.ELEVATOR_DOORS_OPEN);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#Door0", LabeledMatchers.hasText("Open"));

        elevatorMock.setDoorStatus(0, IElevator.ELEVATOR_DOORS_CLOSED);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#Door0", LabeledMatchers.hasText("Closed"));

        elevatorMock.setDoorStatus(0, IElevator.ELEVATOR_DOORS_OPENING);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#Door0", LabeledMatchers.hasText("Opening"));

        elevatorMock.setDoorStatus(0, IElevator.ELEVATOR_DOORS_CLOSING);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#Door0", LabeledMatchers.hasText("Closing"));
    }

    @Test
    public void testFloorDir(FxRobot robot) throws InterruptedException, RemoteException {

        elevatorMock.setFloorButtonDown(0, true);
        elevatorMock.setFloorButtonDown(1, false);
        elevatorMock.setFloorButtonUp(0, false);
        elevatorMock.setFloorButtonUp(1, true);
        WaitForAsyncUtils.waitForFxEvents();
        
        FxAssert.verifyThat("#Floor0,0,DOWN", LabeledMatchers.hasText("▼"));
        FxAssert.verifyThat("#Floor0,1,DOWN", LabeledMatchers.hasText(""));
        FxAssert.verifyThat("#Floor0,0,UP", LabeledMatchers.hasText(""));
        FxAssert.verifyThat("#Floor0,1,UP", LabeledMatchers.hasText("▲"));
    }

    @Test
    public void testCommitedDir(FxRobot robot) throws InterruptedException, RemoteException {
        elevatorMock.setCommittedDirection(0, IElevator.ELEVATOR_DIRECTION_UP);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#CommitedDir0", LabeledMatchers.hasText("Up"));

        elevatorMock.setCommittedDirection(0, IElevator.ELEVATOR_DIRECTION_DOWN);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#CommitedDir0", LabeledMatchers.hasText("Down"));

        elevatorMock.setCommittedDirection(0, IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat("#CommitedDir0", LabeledMatchers.hasText("Uncommitted"));
    }
}
