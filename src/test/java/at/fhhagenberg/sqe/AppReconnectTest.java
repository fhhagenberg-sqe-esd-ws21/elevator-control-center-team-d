package at.fhhagenberg.sqe;

import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class AppReconnectTest {

    private App app;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public void start(Stage stage) {
        app = new App();
        app.start(stage);
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
}
