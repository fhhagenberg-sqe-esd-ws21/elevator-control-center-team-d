package at.fhhagenberg.sqe;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    private boolean debug = true;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public void start(Stage stage) {

        if (debug) {
            System.out.println("*** Running App in debug mode ***");
            app = new App() {
                @Override
                protected ECC getECC() {
                    return new ECC() {
                        @Override
                        protected IElevator getRmiInterface() throws RemoteException  {
                            // This is where the actual mock is injected into the App.
                            // ElevatorMock implements IElevator with all getters and 
                            // setters.
                            return new ElevatorMock(3, 10);
                        }
                    };
                }
            };
        }
        else {
            app = new App();
        }

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
}
