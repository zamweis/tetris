package shapes;

import java.awt.Color;
import java.awt.Graphics;
import utils.Utils;
import utils.Point;

/**
 *
 * @author Sam
 * @version 1.31
 */
public abstract class Shape {

    private static final int ROTATEPOSITION0 = 0;
    private static final int ROTATEPOSITION1 = 1;
    private static final int ROTATEPOSITION2 = 2;
    private static final int ROTATEPOSITION3 = 3;
    private Block[] blockList = new Block[4];
    private Point[][] positions = new Point[4][3];
    private int rotatePos = Utils.rand(0, 3);

    /**
     * Constructor of Shape
     */
    public Shape() {
    }

    /**
     * Gets the blockList where all the blocks are located in
     *
     * @return Block[] blockList
     */
    public Block[] getBlockList() {
        return blockList;
    }

    /**
     * Gets the list which is used to generate a shape
     *
     * @return positions
     */
    public Point[][] getPositions() {
        return positions;
    }

    /**
     * Sets a block into blockList
     *
     * @param block The block to be set into the blockList
     * @param index The position where the block will be added
     */
    public void setBlock(Block block, int index) {
        blockList[index] = block;
    }

    /**
     * Sets a point into positions
     *
     * @param x the horizontal position in the array
     * @param y the vertical position in the array
     * @param point the point which will be added
     */
    public void setPoint(int x, int y, Point point) {
        positions[x][y] = point;
    }

    /**
     * Sets a value to rotatePosition
     *
     * @param rotatePosition the value set to rotatePosition
     */
    public void setRotatePosition(int rotatePosition) {
        this.rotatePos = rotatePosition;
    }

    /**
     * Gets the current rotatePos
     *
     * @return rotatePos
     */
    public int getRotatePosition() {
        return rotatePos;
    }

    /**
     * @return 0
     */
    public static int getROTATEPOSITION0() {
        return ROTATEPOSITION0;
    }

    /**
     * @return 1
     */
    public static int getROTATEPOSITION1() {
        return ROTATEPOSITION1;
    }

    /**
     * @return 2
     */
    public static int getROTATEPOSITION2() {
        return ROTATEPOSITION2;
    }

    /**
     * @return 3
     */
    public static int getROTATEPOSITION3() {
        return ROTATEPOSITION3;
    }

    /**
     * Gets the block from blockList at the indexed position
     *
     * @param index The position of the wanted block
     * @return blockList[index]
     */
    public Block getBlock(int index) {
        return blockList[index];
    }

    /**
     * Sets the blocks of the new Shape into the blockList
     */
    public void generateShape() {
        for (int i = 1; i <= 3; i++) {
            Color color = getBlock(0).getColor();
            blockList[i] = new Block(getXPosition(i - 1), getYPosition(i - 1), color);
        }
    }

    /**
     * Gets the horizontal coordinate of the indexed block of blockList
     *
     * @param index The position of inspected block
     * @return The value of the horizontal coordinate of the inspected block
     */
    public int getXPosition(int index) {
        int x = getBlock(0).getX() + (int) (positions[rotatePos][index].getX());
        return x;
    }

    /**
     * Gets the vertical coordinate of the indexed block of blockList
     *
     * @param index The position of inspected block
     * @return The value of the vertical coordinate of the inspected block
     */
    public int getYPosition(int index) {
        int y = getBlock(0).getY() + (int) (positions[rotatePos][index].getY());
        return y;
    }

    /**
     * Checks if the shape is allowed to rotate anticlockwise
     *
     * @param blockMap the blockMap used to check if the rotate is allowed
     * @return true if the shape is allowed to rotate, false if not
     */
    public boolean isRotatableAntiClockwise(BlockMap blockMap) {
        boolean result = true;
        int tmpRotatePosition;
        if (rotatePos == 3) {
            tmpRotatePosition = 0;
        } else {
            tmpRotatePosition = rotatePos + 1;
        }
        Color color = getBlock(0).getColor();
        for (int i = 1; i <= 3 && result == true; i++) {
            int y = getBlock(0).getY() + (int) (positions[tmpRotatePosition][i - 1].getY());
            int x = getBlock(0).getX() + (int) (positions[tmpRotatePosition][i - 1].getX());
            result = blockMap.isFieldEmpty(x, y);
            if (y > 21 || x > 9 || x < 0) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Checks if the shape is allowed to rotate clockwise
     *
     * @param blockMap the blockMap used to check if the rotate is allowed
     * @return true if the shape is allowed to rotate, false if not
     */
    public boolean isRotatableClockwise(BlockMap blockMap) {
        boolean result = true;
        int tmpRotatePosition;
        if (rotatePos == 0) {
            tmpRotatePosition = 3;
        } else {
            tmpRotatePosition = rotatePos - 1;
        }
        Color color = getBlock(0).getColor();
        for (int i = 1; i <= 3 && result == true; i++) {
            int y = getBlock(0).getY() + (int) (positions[tmpRotatePosition][i - 1].getY());
            int x = getBlock(0).getX() + (int) (positions[tmpRotatePosition][i - 1].getX());
            result = blockMap.isFieldEmpty(x, y);
            if (y > 21 || x > 9 || x < 0) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Rotates the shape anticlockwise
     *
     * @param blockMap the blockMap used to check if the rotate is allowed
     */
    public void rotateAntiClockwise(BlockMap blockMap) {
        if (isRotatableAntiClockwise(blockMap) == true) {
            if (rotatePos == 3) {
                rotatePos = 0;
            } else {
                rotatePos++;
            }
            generateShape();
        }
    }

    /**
     * Rotates the shape clockwise
     *
     * @param blockMap the blockMap used to check if the rotate is allowed
     */
    public void rotateClockwise(BlockMap blockMap) {
        if (isRotatableClockwise(blockMap) == true) {
            if (rotatePos == 0) {
                rotatePos = 3;
            } else {
                rotatePos--;
            }
            generateShape();
        }
    }

    /**
     * Gets the block with the lowest horizontal-coordinate value
     *
     * @return Block leftBlock
     */
    public Block getLeftBlock() {
        Block leftBlock = getBlock(0);
        for (int i = 1; i <= 3; i++) {
            if (leftBlock.getX() > getBlock(i).getX()) {
                leftBlock = getBlock(i);
            }
        }
        return leftBlock;
    }

    /**
     * Checks if all the blocks of the shape can be moved one field to the left
     *
     * @param blockMap The 2D-array where all the field of the gameField are
     * located in
     * @return True if all blocks are movable, false if not
     */
    public boolean isMovableToTheLeft(BlockMap blockMap) {
        boolean result = true;
        if (getLeftBlock().getX() == 0) {
            result = false;
        } else {
            for (int i = 0; i <= 3; i++) {
                if (result == true) {
                    result = !isInCollisionWithLeftBlock(getBlock(i), blockMap);
                }
            }
        }
        return result;
    }

    /**
     * Checks if a given block can be moved one field to the left without
     * colliding
     *
     * @param block The block which is being analyzed
     * @param blockMap The 2D-array where all the field of the gameField are
     * located in
     * @return True if block will collide, false if not
     */
    public boolean isInCollisionWithLeftBlock(Block block, BlockMap blockMap) {
        boolean result = false;
        int x = block.getX() - 1;
        int y = block.getY();
        if (blockMap.isFieldEmpty(x, y) == false) {
            result = true;
        }
        return result;
    }

    /**
     * Moves all the blocks of the shape one field to the left
     *
     * @param blockMap The 2D-array where all the field of the gameField are
     * located in
     */
    public void moveLeft(BlockMap blockMap) {
        if (isMovableToTheLeft(blockMap)) {
            for (int i = 0; i <= 3; i++) {
                blockList[i].moveLeft();
            }
        }
    }

    /**
     * Gets the block with the highest horizontal coordinate value
     *
     * @return Block rightBlock
     */
    public Block getRightBlock() {
        Block rightBlock = getBlock(0);
        for (int i = 1; i <= 3; i++) {
            if (rightBlock.getX() < getBlock(i).getX()) {
                rightBlock = getBlock(i);
            }
        }
        return rightBlock;
    }

    /**
     * Checks if all the blocks of the shape can be moved one field to the right
     *
     * @param blockMap The 2D-array where all the field of the gameField are
     * located in
     * @return True if all blocks are movable, false if not
     */
    public boolean isMovableToTheRight(BlockMap blockMap) {
        boolean result = true;
        if (getRightBlock().getX() == 9) {
            result = false;
        } else {
            for (int i = 0; i <= 3; i++) {
                if (result == true) {
                    result = !isInCollisionWithRightBlock(getBlock(i), blockMap);
                }
            }
        }
        return result;
    }

    /**
     * Checks if a given block can be moved one field to the right without
     * colliding
     *
     * @param block The block which is being analyzed
     * @param blockMap The 2D-array where all the field of the gameField are
     * located in
     * @return True if block will collide, false if not
     */
    public boolean isInCollisionWithRightBlock(Block block, BlockMap blockMap) {
        boolean result = false;
        int x = block.getX() + 1;
        int y = block.getY();
        if (blockMap.isFieldEmpty(x, y) == false) {
            result = true;
        }
        return result;
    }

    /**
     * Moves all the blocks of the shape one field to the right
     *
     * @param blockMap The 2D-array where all the field of the gameField are
     * located in
     */
    public void moveRight(BlockMap blockMap) {
        if (isMovableToTheRight(blockMap)) {
            for (int i = 0; i <= 3; i++) {
                blockList[i].moveRight();
            }
        }
    }

    /**
     * Gets the block with the highest vertical coordinate value
     *
     * @return Block lowestBlock
     */
    public Block getLowestBlock() {
        Block lowestBlock = getBlock(0);
        for (int i = 1; i <= 3; i++) {
            if (lowestBlock.getY() < getBlock(i).getY()) {
                lowestBlock = getBlock(i);
            }
        }
        return lowestBlock;
    }

    /**
     * Gets the block with the lowest vertical coordinate value
     *
     * @return Block
     */
    public Block getHighestBlock() {
        Block highestBlock = getBlock(0);
        for (int i = 1; i <= 3; i++) {
            if (highestBlock.getY() > getBlock(i).getY()) {
                highestBlock = getBlock(i);
            }
        }
        return highestBlock;
    }

    /**
     * Checks if all the blocks of the shape can be moved one field downwards
     *
     * @param blockMap The 2D-array where all the field of the gameField are
     * located in
     * @return True if all blocks are movable, false if not
     */
    public boolean isMovableDownWards(BlockMap blockMap) {
        boolean result = true;
        if (blockMap != null) {
            if (getLowestBlock().getY() == 21) {
                result = false;
            } else {
                for (int i = 0; i <= 3; i++) {
                    if (result == true) {
                        result = !isInCollisionWithLowerBlock(getBlock(i), blockMap);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Checks if a given block can be moved one field downwards without
     * colliding
     *
     * @param block The block which is being analyzed
     * @param blockMap The 2D-array where all the field of the gameField are
     * located in
     * @return True if block will collide, false if not
     */
    public boolean isInCollisionWithLowerBlock(Block block, BlockMap blockMap) {
        boolean result = false;
        int x = block.getX();
        int y = block.getY() + 1;
        if (blockMap.isFieldEmpty(x, y) == false) {
            result = true;
        }
        return result;
    }

    /**
     * Moves all the blocks of the shape one field downwards
     *
     * @param blockMap The 2D-array where all the field of the gameField are
     * located in
     */
    public void moveDown(BlockMap blockMap) {
        if (isMovableDownWards(blockMap) == true) {
            for (int i = 0; i <= 3; i++) {
                blockList[i].moveDown();
            }
        }
    }

    /**
     * Moves the shape downwards as far as possible
     *
     * @param blockMap
     */
    public void fallDown(BlockMap blockMap) {
        while (isMovableDownWards(blockMap) == true) {
            moveDown(blockMap);
        }
    }

    /**
     * Draws all the blocks of the shape
     *
     * @param g Graphics g
     */
    public void drawShape(Graphics g) {
        for (int i = 0; i <= 3; i++) {
            blockList[i].draw(g, 40);
        }
    }

    /**
     * Draws the shape if it is the nextShape
     *
     * @param g the graphics used to draw the shape
     */
    public abstract void drawNextShape(Graphics g);

    /**
     * Draws only the borders of every block of the shape (is used to predict
     * the possible position of the shape if fallen down=
     *
     * @param g the graphics used to draw the shape
     * @param offset the offset of the predicted position
     */
    public void drawShapeBorderOnly(Graphics g, int offset) {
        for (int i = 0; i <= 3; i++) {
            blockList[i].drawBorderOnly(g, 40, offset);
        }
    }
}
