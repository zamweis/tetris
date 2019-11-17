package gui;

import java.awt.Color;
import java.awt.Graphics;
import shapes.Shape;

/**
 *
 * @author Sam
 * @version 1.4
 */
public class AfterNextShapePanel extends javax.swing.JPanel {

    private Shape afterNextShape;

    /**
     * Sets a value to nextShape
     *
     * @param afterNextShape the shape given to nextShape
     */
    public void setShape(Shape afterNextShape) {
        this.afterNextShape = afterNextShape;
    }

    /**
     * Creates new form afterNextShapePanel
     */
    public AfterNextShapePanel() {
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        //black border
        g.setColor(Color.white);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        //shape
        if (afterNextShape != null) {
            afterNextShape.drawNextShape(g);
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
