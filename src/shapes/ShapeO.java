package shapes;

import java.awt.Color;
import java.awt.Graphics;
import utils.Point;

/**
 *
 * @author Sam
 * @version 1.0
 */
public class ShapeO extends Shape {

    /**
     * Constructor of ShapeO
     */
    public ShapeO() {
        super();
        Color color = new Color(200, 0, 0);
        setBlock(new Block(4, -1, color), 0);
        ///rotatePostion 0
        setPoint(0, 0, new Point(1, 0));
        setPoint(0, 1, new Point(0, -1));
        setPoint(0, 2, new Point(1, -1));
        ///rotatePostion 1
        setPoint(1, 0, new Point(1, 0));
        setPoint(1, 1, new Point(0, -1));
        setPoint(1, 2, new Point(1, -1));
        ///rotatePostion 2
        setPoint(2, 0, new Point(1, 0));
        setPoint(2, 1, new Point(0, -1));
        setPoint(2, 2, new Point(1, -1));
        ///rotatePostion 3
        setPoint(3, 0, new Point(1, 0));
        setPoint(3, 1, new Point(0, -1));
        setPoint(3, 2, new Point(1, -1));
    }

    @Override
    public void drawNextShape(Graphics g) {
        Block[] list = getBlockList();
        for (int i = 0; i <= 3; i++) {
            list[i].drawWithOffset(g, 40, -20, -20);
        }
    }
}
