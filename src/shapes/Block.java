package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Sam
 * @version 1.1
 */
public class Block {

    private int x;
    private int y;
    private Color color;

    /**
     * @param x     the horizontal position of the block
     * @param y     the vertical position of the block
     * @param color the color in which the block will be painted on the panel
     */
    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Sets a value to the attributes x and y
     *
     * @param x the value for x
     * @param y the value for y
     */
    public void setCoordinates(int x, int y) {
        this.y = y;
        this.x = x;
    }

    /**
     * Sets a value to x
     *
     * @param x the value for x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets a value to y
     *
     * @param y the value for y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the value of X
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the value of y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the color
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets a color to color
     *
     * @param color the color set to color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Moves the block one field to the left
     */
    public void moveLeft() {
        x = x - 1;
    }

    /**
     * Moves the block one field to the right
     */
    public void moveRight() {
        x = x + 1;
    }

    /**
     * Moves the block one field downwards
     */
    public void moveDown() {
        y = y + 1;
    }

    /**
     * Draws the block
     *
     * @param g       the graphic be used to draw the block
     * @param boxSize the size of the block (length/height)
     */
    public void draw(Graphics g, int boxSize) {
        g.setColor(color);
        g.fillRect(x * boxSize + 2, y * boxSize + 2, boxSize - 3, boxSize - 3);
    }

    /**
     * Draws the block with a given offset (is used to draw on the
     * nextShapePanel)
     *
     * @param g       the graphic be used to draw the block
     * @param boxSize the size of the block (length/height)
     * @param xOffset the horizontal offset
     * @param yOffset the vertical offset
     */
    public void drawWithOffset(Graphics g, int boxSize, int xOffset, int yOffset) {
        g.setColor(color);
        g.fillRect((x - 2) * boxSize + xOffset, (y + 4) * boxSize + yOffset, boxSize - 3, boxSize - 3);
    }

    /**
     * Draws only the border of the block (is used to predict the position of
     * the block if dropped down)
     *
     * @param g       the graphic be used to draw the block
     * @param boxSize the size of the block (length/height)
     * @param offset  the vertical offset
     */
    public void drawBorderOnly(Graphics g, int boxSize, int offset) {
        g.setColor(color);
        g.drawRect(x * boxSize + 2, (y + offset) * boxSize + 2, boxSize - 3, boxSize - 3);
    }
}
