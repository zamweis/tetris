package utils;

/**
 *
 * @author Sam
 * @version 1.0
 */
public class Utils {

    /**
     * Constructor of Utils
     */
    public Utils() {
    }

    /**
     * Gets a random number from a given interval
     *
     * @param min the minimum of the interval
     * @param max the maximum of the interval
     * @return int
     */
    public static int rand(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
