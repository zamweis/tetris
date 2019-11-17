package shapes;

import java.awt.Graphics;
import java.util.Observable;

/**
 * @author Sam
 * @version 2.0
 */
public final class BlockMap extends Observable {

    private Block[][] map;

    /**
     * Constructor of BlockMap
     */
    public BlockMap() {
        map = new Block[10][22];
    }

    /**
     * Adds a given block to blockMap at the corresponding position
     *
     * @param block The block which will be added
     */
    public void addBlock(Block block) {
        if (block.getY() >= 0 && block.getX() >= 0 && block.getY() <= 21 && block.getX() <= 9) {
            map[block.getX()][block.getY()] = block;
        }
    }

    /**
     * Adds all the blocks of a shape into the blockMap
     *
     * @param blockList the shape's list of blocks
     */
    public void addBlocks(Block[] blockList) {
        for (int i = 0; i <= 3; i++) {
            if (blockList[i] != null) {
                addBlock(blockList[i]);
            }
        }
        notifyObservers();
    }

    /**
     * Checks if a field, given by it's coordinates, has no block inside
     *
     * @param x the horizontal coordinate of the field
     * @param y the vertical coordinate of the field
     * @return false if the field has a block inside, true if not
     */
    public boolean isFieldEmpty(int x, int y) {
        boolean result = true;
        if (x >= 0 && x <= 9 && y >= 0 && y <= 21) {
            if (map[x][y] != null) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Gets the block of blockMap given by it's coordinates
     *
     * @param x the horizontal coordinate of the block/field
     * @param y the vertical coordinate of the block/field
     * @return map[x][y]
     */
    public Block getBlock(int x, int y) {
        return map[x][y];
    }

    /**
     * Draws all the blocks located in the blockMap
     *
     * @param g the graphic being used to draw the blocks
     */
    public void drawAllBlocks(Graphics g) {
        for (int x = 9; x >= 0; x--) {
            for (int y = 21; y >= 0; y--) {
                if (map[x][y] != null) {
                    getBlock(x, y).draw(g, 40);
                }
            }
        }
    }

    /**
     * Removes a specific block from blockMap
     *
     * @param block The block which will be deleted
     */
    public void removeBlock(Block block) {
        if (block != null) {
            map[block.getX()][block.getY()] = null;
        }
    }

    /**
     * Removes a block from blockMap given by his coordinates
     *
     * @param x horizontal coordinate of the block/field
     * @param y vertical coordinate of the block/field
     */
    public void removeBlock(int x, int y) {
        map[x][y] = null;
    }

    /**
     * Removes all blocks of a given line
     *
     * @param lineIndex the linenumber which will be cleared
     */
    public void clearLine(int lineIndex) {
        for (int x = 0; x <= 9; x++) {
            removeBlock(x, lineIndex);
        }
    }

    /**
     * Checks if a line, given by it's index, is full or not
     *
     * @param lineIndex the index of the line which will be checked
     * @return true if the line is full, false if not
     */
    public boolean isLineFull(int lineIndex) {
        boolean result = true;
        for (int x = 0; x <= 9; x++) {
            if (result == true) {
                result = !isFieldEmpty(x, lineIndex);
            }
        }
        return result;
    }

    /**
     * Checks if a line if full in the range of the vertical position of the
     * activeShappe
     *
     * @param activeShape the shape which is used when this method gets called
     * @return true if one of the lines if full, false if none is full
     */
    public boolean isALineFull(Shape activeShape) {
        boolean result = false;
        int startLine = activeShape.getLowestBlock().getY();
        int endLine = activeShape.getHighestBlock().getY();
        for (int i = 0; i <= startLine - endLine; i++) {
            if (isLineFull(startLine - i) == true && result == false) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Removes all the blocks of the lines which are full
     *
     * @param activeShape the shape which is used when this method gets called
     * @return the amount of lines which have been cleared
     */
    public int clearFullLines(Shape activeShape) {
        int numberOfLines = 0;
        int startLine = activeShape.getLowestBlock().getY();
        int endLine = activeShape.getHighestBlock().getY();
        for (int i = 0; i <= startLine - endLine; i++) {
            if (isLineFull(startLine - i) == true) {
                clearLine(startLine - i);
                numberOfLines++;
            }
        }
        return numberOfLines;
    }

    /**
     * Checks if a line, given by it's index, is empty or not
     *
     * @param lineIndex the index of the line
     * @return true if the line if empty, false if not
     */
    public boolean isLineEmpty(int lineIndex) {
        boolean result = true;
        for (int x = 0; x <= 9; x++) {
            if (result == true) {
                result = isFieldEmpty(x, lineIndex);
            }
        }
        return result;
    }

    /**
     * Moves a block, given by it's coordinates, one field downwards
     *
     * @param x the horizontal position of the block
     * @param y the vertical position of the block
     */
    public void moveBlockDown(int x, int y) {
        if (map[x][y] != null) {
            map[x][y + 1] = new Block(x, y + 1, getBlock(x, y).getColor());
            map[x][y] = null;
        }
    }

    /**
     * Checks if a block, given by it coordinates, is movable downwards by one
     * field
     *
     * @param x the horizontal position of the block
     * @param y the vertical position of the block
     * @return true if the block is movable, false if not
     */
    public boolean isBlockMovableDownwards(int x, int y) {
        boolean result = true;
        if (y + 1 <= 21) {
            if (map[x][y + 1] != null) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Moves an entire line, given by it's index, one field downwards
     *
     * @param lineIndex the index of the line
     */
    public void moveLineDown(int lineIndex) {
        if (lineIndex <= 21) {
            for (int x = 0; x <= 9; x++) {
                if (isBlockMovableDownwards(x, lineIndex) == true) {
                    moveBlockDown(x, lineIndex);
                }
            }
        }
    }

    /**
     * Moves a entire line, given by it's index, as many fields downwards as
     * possible
     *
     * @param lineIndex the index of the line
     */
    public void fallLineDown(int lineIndex) {
        for (int i = 0; lineIndex + i <= 20; i++) {
            if (isLineEmpty(lineIndex + i + 1) == true) {
                moveLineDown(lineIndex + i);
            }
        }
    }

    /**
     * Gets the index of the first line which isn't empty
     *
     * @param lineIndex the index from which on the method checks the condition
     *                  of the line
     * @return -1 if all the upper lines are empty else the index of the first
     * line which isn't empty
     */
    public int getFirstNotEmptyLine(int lineIndex) {
        int result = -1;
        boolean condition = true;
        for (int i = lineIndex; i >= 0; i--) {
            if (condition == true) {
                if (isLineEmpty(i) == false && result == -1) {
                    result = i;
                }
                if (i == -1) {
                    condition = false;
                }
            }
        }
        return result;
    }

    /**
     * Moves all the lines which are not empty as far down as possible
     *
     * @param activeShape the shape which is used when this method gets called
     */
    public void moveAllNotEmptyLinesDown(Shape activeShape) {
        int startLine = activeShape.getLowestBlock().getY();
        for (int y = startLine; y > 0; y--) {
            int firstNotEmptyLine = getFirstNotEmptyLine(y);
            if (firstNotEmptyLine != -1) {
                fallLineDown(firstNotEmptyLine);
            }
        }
    }
}
