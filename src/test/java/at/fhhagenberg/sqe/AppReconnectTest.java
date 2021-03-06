package at.fhhagenberg.sqe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.stage.Stage;
import sqelevator.ECC;

@ExtendWith(ApplicationExtension.class)
public class AppReconnectTest {

    private App app;
    private Stage s;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     * @throws Exception
     */
    @Start
    public void start(Stage stage) throws Exception {
        app = new App(){
            @Override
            protected ECC getECC() {
                return new ECC() {
                    @Override
                    public void init()  {
                        return;
                    }
                };
            }
        };
        
        s = stage;
        // app.start(stage);
    }

    /**
     * @param robot - Will be injected by the test runner.
     * @throws InterruptedException
     */
    // @Test
    // public void testGoButtonClick(FxRobot robot) throws InterruptedException {
    //     robot.clickOn("#floorComboBox0");
    //     robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

    //     robot.clickOn("#goButton0");
    //     // Thread.sleep(20);
    //     WaitForAsyncUtils.waitForFxEvents();
    //     FxAssert.verifyThat("#TargetVal0", LabeledMatchers.hasText("2"));
    // }

    @Test
    void testStart() {
        app.start(s);
    }

    @Test
    void testStop() throws Exception {
        app.stop();
    }
}
