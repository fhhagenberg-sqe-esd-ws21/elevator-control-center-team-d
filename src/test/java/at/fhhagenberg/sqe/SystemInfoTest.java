package at.fhhagenberg.sqe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class SystemInfoTest {
    @Test
    public void testJavaVersion() {
        assertEquals("15", SystemInfo.javaVersion());
    }

    @Test
    public void testJavafxVersion() {
        assertEquals("15", SystemInfo.javafxVersion());
    }
}