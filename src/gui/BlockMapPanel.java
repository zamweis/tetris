package gui;

import java.awt.Color;
import java.awt.Graphics;
import shapes.BlockMap;
import shapes.Shape;

/**
 *
 * @author Sam
 * @version 1.2
 */
public class BlockMapPanel extends javax.swing.JPanel {

    private BlockMap map = new BlockMap();

    /**
     * Constructor of GamePanel
     */
    public BlockMapPanel() {
        initComponents();
    }

    /**
     * Gives gamesPanel the new BlockMap
     *
     * @param map the modified map
     */
    public void setMap(BlockMap map) {
        this.map = map;
    }

    /**
     * Gives BlockMapPanel the new Shape
     *
     * @param shape the new Shape
     */
    public void addShape(Shape shape) {
        this.map.addBlocks(shape.getBlockList());
    }

    /**
     * Paints the background, the columns, the lines, the border and all the
     * blocks inside the blockMap
     *
     * @param g the graphics used to draw on the panel
     */
    @Override
    public void paintComponent(Graphics g) {
        //background
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());
        //columns
        g.setColor(Color.black);
        int colums = 0;
        while (colums < 9) {
            g.drawLine(colums * 40 + 40, 1, colums * 40 + 40, getHeight() - 2);
            colums++;
        }
        //lines
        int lines = 0;
        while (lines < 21) {
            g.drawLine(1, lines * 40 + 40, getWidth() - 2, lines * 40 + 40);
            lines++;
        }
        //border
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (map != null) {
            map.drawAllBlocks(g);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
