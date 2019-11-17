package gui;

import java.awt.Graphics;
import shapes.Shape;

/**
 *
 * @author Sam
 * @version 1.0
 */
public class ShapePanel extends javax.swing.JPanel {

    private Shape activeShape = null;
    private int offset;

    /**
     * Creates new form ShapePanel
     */
    public ShapePanel() {
        initComponents();
        setOpaque(false);
    }

    /**
     * Sets a shape to activeShape
     *
     * @param shape the shape set to activeShape
     */
    public void setShape(Shape shape) {
        this.activeShape = shape;
    }

    /**
     * Sets a value to offset
     *
     * @param offset the value which is used
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Draws the activeShape and it's predicted position(borders only)
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        if (activeShape != null) {
            activeShape.drawShape(g);
            if (offset >= 1) {
                activeShape.drawShapeBorderOnly(g, offset);
            }
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
