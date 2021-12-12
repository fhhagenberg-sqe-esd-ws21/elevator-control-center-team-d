package at.fhhagenberg.sqe;

/**
 * Todo: write doc
 */
public class SystemInfo {

    /**
     * Todo: write doc
     * @return String yes
     */
    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    /**
     * Todo: write doc
     * @return String yes
     */
    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

}