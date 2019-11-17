package utils;

/**
 *
 * @author Sam
 * @version 1.0
 */
public class Point {

    private int x;
    private int y;

    /**
     * Constructor of Point
     *
     * @param x the horizontal position of the point
     * @param y the vertical position of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the value of x
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets a value to x
     *
     * @param x the used value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the value of y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets a value to y
     *
     * @param y the used value
     */
    public void setY(int y) {
        this.y = y;
    }

}
