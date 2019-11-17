package shapes;

import java.awt.Color;
import java.awt.Graphics;
import utils.Point;

/**
 * @author Sam
 * @version 1.1
 */
public class ShapeT extends Shape {

    /**
     * Constructor of ShapeT
     */
    public ShapeT() {
        super();
        Color color = new Color(255, 170, 0);
        if (getRotatePosition() == getROTATEPOSITION1()) {
            setBlock(new Block(4, -1, color), 0);
        } else {
            setBlock(new Block(4, -2, color), 0);
        }
        ///rotate 0
        setPoint(0, 0, new Point(0, -1));
        setPoint(0, 1, new Point(1, 0));
        setPoint(0, 2, new Point(0, 1));
        ///rotate 1
        setPoint(1, 0, new Point(-1, 0));
        setPoint(1, 1, new Point(0, -1));
        setPoint(1, 2, new Point(1, 0));
        ///rotate 2
        setPoint(2, 0, new Point(0, 1));
        setPoint(2, 1, new Point(-1, 0));
        setPoint(2, 2, new Point(0, -1));
        ///rotate 3
        setPoint(3, 0, new Point(1, 0));
        setPoint(3, 1, new Point(0, 1));
        setPoint(3, 2, new Point(-1, 0));
    }

    @Override
    public void drawNextShape(Graphics g) {
        int rotatePos = getRotatePosition();
        Block[] list = getBlockList();
        for (int i = 0; i <= 3; i++) {
            if (rotatePos == 0) {
                list[i].drawWithOffset(g, 40, -20, 0);
            } else if (rotatePos == 1) {
                list[i].drawWithOffset(g, 40, 0, -20);
            } else if (rotatePos == 2) {
                list[i].drawWithOffset(g, 40, 20, 0);
            } else {
                list[i].drawWithOffset(g, 40, 0, -20);
            }
        }
    }
}
