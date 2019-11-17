package gui;

import java.awt.Color;
import java.awt.Graphics;
import shapes.Shape;

/**
 *
 * @author Sam
 * @version 1.0
 */
public class NextShapePanel extends javax.swing.JPanel {

    private Shape nextShape;

    /**
     * Sets a value to nextShape
     *
     * @param nextShape the shape given to nextShape
     */
    public void setShape(Shape nextShape) {
        this.nextShape = nextShape;
    }

    /**
     * Creates new form NextShapePanel
     */
    public NextShapePanel() {
        initComponents();
    }

    /**
     * Draws the background, the border and the shape of nextShapePanel
     *
     * @param g the graphics used to draw everything
     */
    @Override
    public void paintComponent(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        //black border
        g.setColor(Color.white);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        //shape
        if (nextShape != null) {
            nextShape.drawNextShape(g);
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
