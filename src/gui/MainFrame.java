package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shapes.Shape;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import shapes.BlockMap;
import shapes.ShapeI;
import shapes.ShapeJ;
import shapes.ShapeL;
import shapes.ShapeO;
import shapes.ShapeS;
import shapes.ShapeT;
import shapes.ShapeZ;
import utils.Utils;

/**
 *
 * @author Sam
 * @version 6.122
 */
public final class MainFrame extends javax.swing.JFrame {

    private int score = 0;
    private int level = 1;
    private int lines = 0;
    private int speed = 600;
    private Timer timer;
    private BlockMap blockMap;
    private Shape activeShape = null;
    private Shape nextShape = null;
    private Shape afterNexShape = null;
    private boolean isGameRunning = false;
    private boolean cleanGame = false;
    private boolean isMoveAllowed = true;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        setTitle("Tetris");
        blockMap = new BlockMap();
        setLocationRelativeTo(null);
        playerTextField.requestFocus();
        initTimer();
        Color background = new Color(11, 6, 12);
        this.getContentPane().setBackground(Color.black);
    }

    /**
     * Generates a random type of shape an and returns it
     *
     * @return Shape
     */
    public Shape newShapeGenerator() {
        int rand = Utils.rand(0, 6);
        Shape newShape = new ShapeI();
        switch (rand) {
            case 0:
                newShape = new ShapeI();
                break;
            case 1:
                newShape = new ShapeJ();
                break;
            case 2:
                newShape = new ShapeL();
                break;
            case 3:
                newShape = new ShapeO();
                break;
            case 4:
                newShape = new ShapeS();
                break;
            case 5:
                newShape = new ShapeT();
                break;
            case 6:
                newShape = new ShapeZ();
                break;
            default:
                break;
        }
        newShape.generateShape();
        return newShape;
    }

    public void updateNextShapePanels(int mode) {
        if (mode == 1) {
            nextShapePanel.setShape(nextShape);
            afterNextShapePanel.setShape(afterNexShape);
        } else {
            nextShapePanel.setShape(null);
            afterNextShapePanel.setShape(null);
        }
        nextShapePanel.repaint();
        afterNextShapePanel.repaint();
    }

    /**
     * Generates the nextShape and the activeShape if necessary
     */
    public void shapeGenerator() {
        if (nextShape == null && activeShape == null && afterNexShape == null) {
            activeShape = newShapeGenerator();
            nextShape = newShapeGenerator();
            afterNexShape = newShapeGenerator();
        } else if (afterNexShape != null) {
            activeShape = nextShape;
            nextShape = afterNexShape;
            afterNexShape = newShapeGenerator();
        }
        updateNextShapePanels(1);
    }

    /**
     * Finds and cleans the full lines an then moves all the not-empty lines
     * down
     */
    public void cleanFlow() {
        blockMap.addBlocks(activeShape.getBlockList());
        blockMapPanel.setMap(blockMap);
        blockMapPanel.repaint();
        updateShapePanel(0);
        if (blockMap.isALineFull(activeShape) == true) {
            int amountOfLines = blockMap.clearFullLines(activeShape);
            blockMap.moveAllNotEmptyLinesDown(activeShape);
            setNumbers(amountOfLines);
            updateView();
            scoreLabel.setText(String.valueOf(score));
        }
        blockMapPanel.setMap(blockMap);
        blockMapPanel.repaint();
    }

    /**
     * Returns the possible offset of the activeShape relative to it's current
     * position
     *
     * @return int
     */
    public int getOffset() {
        int[] offset = new int[4];
        int result = -1;
        if (activeShape.isMovableDownWards(blockMap) == true) {
            for (int i = 0; i <= 3; i++) {
                int y = activeShape.getBlock(i).getY();
                int x = activeShape.getBlock(i).getX();
                int tmpOffset = 0;
                if (y < 0) {
                    tmpOffset = Math.abs(y);
                    y = 0;
                }
                boolean condition = true;
                while (y <= 21 && condition == true) {
                    if (blockMap.isBlockMovableDownwards(x, y) == true && y < 21) {
                        tmpOffset++;
                        y++;
                    } else {
                        condition = false;
                    }
                }
                offset[i] = tmpOffset;
            }
            result = getLowestEntry(offset);
        }
        return result;
    }

    /**
     * Returns the lowest offset from a list of offsets
     *
     * @param offset the list which will be analyzed
     * @return int
     */
    public int getLowestEntry(int[] offset) {
        int tmp = offset[0];
        for (int i = offset.length - 1; i > 0; i--) {
            if (tmp > offset[i]) {
                tmp = offset[i];
            }
        }
        return tmp;
    }

    /**
     * Sets the new values to score, lines, level and speed according to the
     * number of lines just cleared
     *
     * @param amountOfLines the amount of lines just being cleared
     */
    public void setNumbers(int amountOfLines) {
        lines = lines + amountOfLines;
        score = score + amountOfLines * amountOfLines * 50 * (level + 1) + level * amountOfLines;
        int tmp = level;
        if (lines >= 10 && level < 30) {
            level = lines / 10 + 1;
            if (tmp != level) {
                if (speed - (35 - level) >= 40) {
                    speed = speed - (35 - level);
                } else {
                    speed = 40;
                }
            }
        }
        if (blockMap.isLineEmpty(21) == true) {
            score = score + 2000 * (level);
        }
        timer.setDelay(speed);
    }

    /**
     * Initializes the timer
     */
    public void initTimer() {
        timer = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (activeShape != null) {
                    if (cleanGame == true) {
                        if (activeShape.getHighestBlock().getY() < 0) {
                            setNewGame();
                        } else {
                            cleanFlow();
                            shapeGenerator();
                            updateShapePanel(1);
                            isMoveAllowed = true;
                        }
                        cleanGame = false;
                    } else if (activeShape.isMovableDownWards(blockMap) == true) {
                        activeShape.moveDown(blockMap);
                        updateShapePanel(1);
                    } else {
                        shapePanel.setOffset(0);
                        cleanGame = true;
                    }
                } else {
                    shapeGenerator();
                }
            }
        });
    }

    /**
     * Initializes the start parameters of the game for a new game
     */
    public void setNewGame() {
        timer.stop();
        blockMap = new BlockMap();
        blockMapPanel.setMap(blockMap);
        updateNextShapePanels(0);
        updateShapePanel(0);
        blockMapPanel.repaint();
        score = 0;
        level = 1;
        lines = 0;
        speed = 600;
        isGameRunning = false;
        activeShape = null;
        playerTextField.requestFocus();
    }

    /**
     * Gives shapePanel the modified shape and it's corresponding offset and
     * repaints it at the end
     *
     * @param mode
     */
    public void updateShapePanel(int mode) {
        if (mode == 1) {
            shapePanel.setShape(activeShape);
            if (activeShape.isMovableDownWards(blockMap) == true) {
                shapePanel.setOffset(getOffset());
            } else {
                shapePanel.setOffset(0);
            }
        } else {
            shapePanel.setShape(null);
            shapePanel.setOffset(0);
        }
        shapePanel.repaint();
    }

    /**
     * Updates the value of scoreLabel, levelLabel, linesLabel and gives
     * blockMapPanel the modified blockMap and the activeShape the orders a
     * repaint.
     */
    public void updateView() {
        levelLabel.setText(String.valueOf(level));
        linesLabel.setText(String.valueOf(lines));
        scoreLabel.setText(String.valueOf(score));
    }

    /**
     * Checks if the state of the game allows a move
     *
     * @return true if a move is allowed, false if not
     */
    public boolean isMoveAllowed() {
        boolean result = true;
        if (isGameRunning == false || isMoveAllowed == false || activeShape == null || blockMap == null || timer.isRunning() == false || cleanGame == true) {
            result = false;
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        linesLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        playerTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        scoreList = new javax.swing.JList();
        blockMapPanel = new gui.BlockMapPanel();
        shapePanel = new gui.ShapePanel();
        nextShapePanel = new gui.NextShapePanel();
        afterNextShapePanel = new gui.AfterNextShapePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 51));
        setName("MainFrame"); // NOI18N
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Gisha", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(243, 243, 243));
        jLabel1.setText("Player:");

        jLabel2.setFont(new java.awt.Font("Gisha", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(243, 243, 243));
        jLabel2.setText("Score:");

        scoreLabel.setFont(new java.awt.Font("Gisha", 0, 24)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(243, 243, 243));
        scoreLabel.setText("0");

        jLabel4.setFont(new java.awt.Font("Gisha", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(243, 243, 243));
        jLabel4.setText("High Scores:");

        jLabel7.setFont(new java.awt.Font("Gisha", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(243, 243, 243));
        jLabel7.setText("Level:");

        levelLabel.setFont(new java.awt.Font("Gisha", 0, 24)); // NOI18N
        levelLabel.setForeground(new java.awt.Color(243, 243, 243));
        levelLabel.setText("0");

        jLabel10.setFont(new java.awt.Font("Gisha", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(243, 243, 243));
        jLabel10.setText("Lines:");

        linesLabel.setFont(new java.awt.Font("Gisha", 0, 24)); // NOI18N
        linesLabel.setForeground(new java.awt.Color(243, 243, 243));
        linesLabel.setText("0");

        jLabel12.setFont(new java.awt.Font("Gisha", 0, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(243, 243, 243));
        jLabel12.setText("Next shapes:");

        jLabel11.setFont(new java.awt.Font("Gisha", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(243, 243, 243));
        jLabel11.setText("Player");

        jLabel13.setFont(new java.awt.Font("Gisha", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(243, 243, 243));
        jLabel13.setText("Score");

        jLabel14.setFont(new java.awt.Font("Gisha", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(243, 243, 243));
        jLabel14.setText("Level");

        jLabel15.setFont(new java.awt.Font("Gisha", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(243, 243, 243));
        jLabel15.setText("Lines");

        playerTextField.setFont(new java.awt.Font("Gisha", 0, 24)); // NOI18N
        playerTextField.setToolTipText("");
        playerTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerTextFieldActionPerformed(evt);
            }
        });
        playerTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                playerTextFieldKeyPressed(evt);
            }
        });

        scoreList.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jScrollPane1.setViewportView(scoreList);

        blockMapPanel.setMaximumSize(new java.awt.Dimension(401, 881));
        blockMapPanel.setMinimumSize(new java.awt.Dimension(401, 881));
        blockMapPanel.setPreferredSize(new java.awt.Dimension(401, 881));

        javax.swing.GroupLayout shapePanelLayout = new javax.swing.GroupLayout(shapePanel);
        shapePanel.setLayout(shapePanelLayout);
        shapePanelLayout.setHorizontalGroup(
            shapePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );
        shapePanelLayout.setVerticalGroup(
            shapePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout blockMapPanelLayout = new javax.swing.GroupLayout(blockMapPanel);
        blockMapPanel.setLayout(blockMapPanelLayout);
        blockMapPanelLayout.setHorizontalGroup(
            blockMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shapePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        blockMapPanelLayout.setVerticalGroup(
            blockMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shapePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout nextShapePanelLayout = new javax.swing.GroupLayout(nextShapePanel);
        nextShapePanel.setLayout(nextShapePanelLayout);
        nextShapePanelLayout.setHorizontalGroup(
            nextShapePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        nextShapePanelLayout.setVerticalGroup(
            nextShapePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout afterNextShapePanelLayout = new javax.swing.GroupLayout(afterNextShapePanel);
        afterNextShapePanel.setLayout(afterNextShapePanelLayout);
        afterNextShapePanelLayout.setHorizontalGroup(
            afterNextShapePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        afterNextShapePanelLayout.setVerticalGroup(
            afterNextShapePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(playerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(blockMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(scoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(levelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(linesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(nextShapePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(afterNextShapePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(blockMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(playerTextField))
                                .addGap(11, 11, 11)
                                .addComponent(jLabel4)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(levelLabel))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(linesLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(36, 36, 36)
                        .addComponent(nextShapePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(afterNextShapePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGap(15, 15, 15))
        );

        pack();
    }

    private void playerTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerTextFieldActionPerformed
        if (isGameRunning == false) {
            if (playerTextField.getText().isEmpty() == false) {
                playerTextField.setText(null);
                this.requestFocus();
                updateView();
                isGameRunning = true;
                initTimer();
                timer.start();
            }
        } else {
            timer.stop();
            blockMap = new BlockMap();
            blockMapPanel.setMap(blockMap);
            updateNextShapePanels(0);
            updateShapePanel(0);
            blockMapPanel.repaint();
            score = 0;
            level = 1;
            lines = 0;
            speed = 600;
            isGameRunning = false;
            activeShape = null;
            if (playerTextField.getText().isEmpty() == false) {
                playerTextField.setText(null);
                this.requestFocus();
                updateView();
                isGameRunning = true;
                initTimer();
                timer.start();
            }
        }
    }//GEN-LAST:event_playerTextFieldActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_P:
                if (timer.isRunning() == true) {
                    timer.stop();
                    score = score - 2000 * level;
                    if (score < 0) {
                        score = 0;
                    }
                    scoreLabel.setText(String.valueOf(score));
                } else if (timer.isRunning() == false) {
                    timer.start();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (isMoveAllowed() == true) {
                    activeShape.moveDown(blockMap);
                    updateShapePanel(1);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (isMoveAllowed() == true) {
                    if (activeShape.isMovableToTheLeft(blockMap)) {
                        activeShape.moveLeft(blockMap);
                        updateShapePanel(1);
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (isMoveAllowed() == true) {
                    if (activeShape.isMovableToTheRight(blockMap)) {
                        activeShape.moveRight(blockMap);
                        updateShapePanel(1);
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
                if (isMoveAllowed() == true) {
                    isMoveAllowed = false;
                    activeShape.fallDown(blockMap);
                    blockMap.addBlocks(activeShape.getBlockList());
                    blockMapPanel.setMap(blockMap);
                    blockMapPanel.repaint();
                    updateShapePanel(0);
                }
                break;
            case KeyEvent.VK_D:
                if (isMoveAllowed() == true) {
                    activeShape.rotateClockwise(blockMap);
                    updateShapePanel(1);
                }
                break;
            case KeyEvent.VK_A:
                if (isMoveAllowed() == true) {
                    activeShape.rotateAntiClockwise(blockMap);
                    updateShapePanel(1);
                }
                break;
            case KeyEvent.VK_N:
                if (isGameRunning == true) {
                    setNewGame();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_formKeyPressed

    private void playerTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_playerTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }//GEN-LAST:event_playerTextFieldKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.AfterNextShapePanel afterNextShapePanel;
    private gui.BlockMapPanel blockMapPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel linesLabel;
    private gui.NextShapePanel nextShapePanel;
    private javax.swing.JTextField playerTextField;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JList scoreList;
    private gui.ShapePanel shapePanel;
    // End of variables declaration//GEN-END:variables
}
