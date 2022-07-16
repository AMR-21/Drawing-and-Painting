package GUI;

import Board.DrawArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
//singleton

public class ProgramUI extends JFrame {

    private javax.swing.JMenuItem circleButton;
    private javax.swing.JMenuItem clearButton;
    private javax.swing.JComboBox<String> colorBox;
    private javax.swing.JMenuItem copyButton;
    private javax.swing.JLabel currentColorLabel;
    private javax.swing.JLabel currentlyDoing;
    private javax.swing.JLabel d1;
    private javax.swing.JTextField d1Box;
    private javax.swing.JLabel d2;
    private javax.swing.JTextField d2Box;
    private javax.swing.JMenuItem deleteMenuButton;
    private javax.swing.JComboBox<String> drawBox;
    private javax.swing.JMenu edit;
    private javax.swing.JMenuItem exitButton;
    private javax.swing.JMenu file;
    private javax.swing.JCheckBox fillBox;
    private javax.swing.JComboBox<String> functionBox;
    private javax.swing.JMenu insert;
    private javax.swing.JMenuItem lineButton;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem rectangleButton;
    private javax.swing.JMenuItem redoButton;
    private javax.swing.JMenuItem saveButton;
    private javax.swing.JButton selectButton;
    private javax.swing.JMenuItem selectMenuButton;
    private javax.swing.JMenuItem squareButton;
    private javax.swing.JLabel status;
    private javax.swing.JComboBox<String> strokeBox;
    private javax.swing.JLabel strokeLabel;
    private javax.swing.JPanel toolBar;
    private javax.swing.JMenuItem triangleButton;
    private javax.swing.JMenuItem undoButton;
    private DrawArea drawArea;
    private ProgramUIFactory factory;
    private ProgramUIOperator op;

    private static final ProgramUI frame = new ProgramUI();

    private ProgramUI() {
        super();
        factory = ProgramUIFactory.getInstance();
        op = ProgramUIOperator.getInstance();
        initComponents();
        this.setVisible(true);
        fillBox.setVisible(false);
        op.setInvisible(d1, d2, d1Box, d2Box);
    }

    public static ProgramUI getInstance() {
        return frame;
    }

    private void initComponents() {

        toolBar = (JPanel) factory.inject(1);
        selectButton = (JButton) factory.inject(10);
        currentColorLabel = (JLabel) factory.inject(4);
        strokeLabel = (JLabel) factory.inject(4);
        drawBox = (JComboBox) factory.inject(3);
        colorBox = (JComboBox) factory.inject(3);
        d1 = (JLabel) factory.inject(4);
        d1Box = (JTextField) factory.inject(5);
        d2 = (JLabel) factory.inject(4);
        strokeBox = (JComboBox) factory.inject(3);
        functionBox = (JComboBox) factory.inject(3);
        d2Box = (JTextField) factory.inject(5);
        fillBox = (JCheckBox) factory.inject(7);
        currentlyDoing = (JLabel) factory.inject(4);
        status = (JLabel) factory.inject(4);
        drawArea = DrawArea.getInstance();
        menuBar = (JMenuBar) factory.inject(8);
        file = (JMenu) factory.inject(6);
        saveButton = (JMenuItem) factory.inject(2);
        clearButton = (JMenuItem) factory.inject(2);
        exitButton = (JMenuItem) factory.inject(2);
        edit = (JMenu) factory.inject(6);
        selectMenuButton = (JMenuItem) factory.inject(2);
        deleteMenuButton = (JMenuItem) factory.inject(2);
        copyButton = (JMenuItem) factory.inject(2);
        undoButton = (JMenuItem) factory.inject(2);
        redoButton = (JMenuItem) factory.inject(2);
        insert = (JMenu) factory.inject(6);
        lineButton = (JMenuItem) factory.inject(2);
        circleButton = (JMenuItem) factory.inject(2);
        squareButton = (JMenuItem) factory.inject(2);
        rectangleButton = (JMenuItem) factory.inject(2);
        triangleButton = (JMenuItem) factory.inject(2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setTitle("Draw and painting application");
        setPreferredSize(new java.awt.Dimension(1100, 730));
        toolBar.setBackground(new java.awt.Color(51, 51, 51));

        selectButton.setBackground(new java.awt.Color(51, 51, 51));
        selectButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        selectButton.setForeground(new java.awt.Color(255, 255, 255));
        selectButton.setText("Select");
        selectButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.selectButtonActionPerformed(evt, functionBox, drawBox, fillBox, strokeBox, d1Box, d2Box, selectButton, drawArea, status);
            }
        });

        currentColorLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        currentColorLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentColorLabel.setText("Color:");

        strokeLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        strokeLabel.setForeground(new java.awt.Color(255, 255, 255));
        strokeLabel.setText("Stroke:");

        drawBox.setBackground(new java.awt.Color(51, 51, 51));
        drawBox.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        drawBox.setForeground(new java.awt.Color(255, 255, 255));
        drawBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Line", "Circle", "Square", "Rectangle", "Triangle"}));
        drawBox.setToolTipText("Draw");
        drawBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.drawBoxActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea, status);
            }
        });

        colorBox.setBackground(new java.awt.Color(51, 51, 51));
        colorBox.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        colorBox.setForeground(new java.awt.Color(255, 255, 255));
        colorBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Black", "Dark Gray", "Gray", "Light Gray", "Red", "Magenta", "Pink", "Orange", "Yellow", "Blue", "Cyan", "Green", "White"}));
        colorBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.colorBoxActionPerformed(evt, colorBox, drawArea, status);
            }
        });

        d1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d1.setForeground(new java.awt.Color(255, 255, 255));
        d1.setText("height:");

        d1Box.setBackground(new java.awt.Color(51, 51, 51));
        d1Box.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d1Box.setForeground(new java.awt.Color(255, 255, 255));
        d1Box.setText("100");
        d1Box.setCaretColor(new java.awt.Color(255, 255, 255));
        d1Box.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        d1Box.setEnabled(true);
        d1Box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.d1BoxActionPerformed(evt, d1Box, d1, drawArea, status);
            }
        });

        d2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d2.setForeground(new java.awt.Color(255, 255, 255));
        d2.setText("height:");

        strokeBox.setBackground(new java.awt.Color(51, 51, 51));
        strokeBox.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        strokeBox.setForeground(new java.awt.Color(255, 255, 255));
        strokeBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        strokeBox.setSelectedIndex(2);
        strokeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.strokeBoxActionPerformed(evt, strokeBox, drawArea, status);
            }
        });

        functionBox.setBackground(new java.awt.Color(51, 51, 51));
        functionBox.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        functionBox.setForeground(new java.awt.Color(255, 255, 255));
        functionBox.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[]{"Move", "Color", "Copy", "Delete", "Resize"}));
        functionBox.setEnabled(false);
        functionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.functionBoxActionPerformed(evt, functionBox, drawArea, status);
            }
        });

        d2Box.setBackground(new java.awt.Color(51, 51, 51));
        d2Box.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d2Box.setForeground(new java.awt.Color(255, 255, 255));
        d2Box.setText("100");
        d2Box.setCaretColor(new java.awt.Color(255, 255, 255));
        d2Box.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        d2Box.setEnabled(true);
        d2Box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.d2BoxActionPerformed(evt, d2Box, d2, drawArea, status);
            }
        });

        fillBox.setBackground(new java.awt.Color(51, 51, 51));
        fillBox.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fillBox.setForeground(new java.awt.Color(255, 255, 255));
        fillBox.setText("Fill");
        fillBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.fillBoxActionPerformed(evt, fillBox, drawArea, status);
            }
        });

        currentlyDoing.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        currentlyDoing.setForeground(new java.awt.Color(255, 255, 255));
        currentlyDoing.setText("Status:");

        status.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        status.setForeground(new java.awt.Color(255, 255, 255));
        status.setText("Line is being drawn");

        javax.swing.GroupLayout toolBarLayout = new javax.swing.GroupLayout(toolBar);
        toolBar.setLayout(toolBarLayout);
        toolBarLayout.setHorizontalGroup(toolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(toolBarLayout.createSequentialGroup().addContainerGap().addComponent(selectButton)
                        .addGap(18, 18, 18)
                        .addComponent(functionBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(drawBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18).addComponent(d1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d1Box, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12).addComponent(d2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d2Box, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24).addComponent(strokeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(strokeBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10).addComponent(currentColorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(colorBox, javax.swing.GroupLayout.PREFERRED_SIZE, 84,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18).addComponent(fillBox).addGap(32, 32, 32).addComponent(currentlyDoing)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                        .addContainerGap()));
        toolBarLayout.setVerticalGroup(toolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(toolBarLayout.createSequentialGroup().addGap(15, 15, 15)
                        .addGroup(toolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(selectButton).addComponent(currentColorLabel).addComponent(strokeLabel)
                                .addComponent(drawBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(colorBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(d1)
                                .addComponent(d1Box, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(strokeBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(functionBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(d2)
                                .addComponent(d2Box, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fillBox).addComponent(currentlyDoing).addComponent(status))
                        .addContainerGap(15, Short.MAX_VALUE)));

        drawArea.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout DrawAreaLayout = new javax.swing.GroupLayout(drawArea);
        drawArea.setLayout(DrawAreaLayout);
        DrawAreaLayout.setHorizontalGroup(DrawAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE));
        DrawAreaLayout.setVerticalGroup(DrawAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 728, Short.MAX_VALUE));

        menuBar.setBackground(new java.awt.Color(51, 51, 51));
        menuBar.setForeground(new java.awt.Color(255, 255, 255));
        menuBar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        file.setForeground(new java.awt.Color(255, 255, 255));
        file.setText("File");
        file.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        saveButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.saveButtonActionPerformed(evt, drawArea, status);
            }
        });
        file.add(saveButton);

        clearButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
                java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.clearButtonActionPerformed(evt, drawArea, drawBox, status);
            }
        });
        file.add(clearButton);

        exitButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q,
                java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.exitButtonActionPerformed(evt);
            }
        });
        file.add(exitButton);

        menuBar.add(file);

        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setText("Edit");
        edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        selectMenuButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        selectMenuButton.setText("Select");
        selectMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.selectMenuButtonActionPerformed(evt, functionBox, drawBox, fillBox, strokeBox, d1Box, d2Box, selectButton, drawArea, status);
            }
        });
        edit.add(selectMenuButton);

        deleteMenuButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        deleteMenuButton.setText("Delete");
        deleteMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.deleteMenuButtonActionPerformed(evt, functionBox, drawBox, fillBox, strokeBox, d1Box, d2Box, selectButton, drawArea, status);
            }
        });
        edit.add(deleteMenuButton);

        copyButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        copyButton.setText("Copy");
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.copyButtonActionPerformed(evt, functionBox, drawBox, fillBox, strokeBox, d1Box, d2Box, selectButton, drawArea, status);
            }
        });
        edit.add(copyButton);

        undoButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        undoButton.setText("Undo");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.undoButtonActionPerformed(evt, drawArea, status);
            }
        });
        edit.add(undoButton);

        redoButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z,
                java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        redoButton.setText("Redo");
        redoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.redoButtonActionPerformed(evt, drawArea, status);
            }
        });
        edit.add(redoButton);

        menuBar.add(edit);

        insert.setForeground(new java.awt.Color(255, 255, 255));
        insert.setText("Insert");
        insert.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lineButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L,
                java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        lineButton.setText("Line");
        lineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.lineButtonActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea, status);
            }
        });
        insert.add(lineButton);

        circleButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
                java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        circleButton.setText("Circle");
        circleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.circleButtonActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea, status);
            }
        });
        insert.add(circleButton);

        squareButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        squareButton.setText("Square");
        squareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.squareButtonActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea, status);
            }
        });
        insert.add(squareButton);

        rectangleButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R,
                java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        rectangleButton.setText("Rectangle");
        rectangleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.rectangleButtonActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea, status);
            }
        });
        insert.add(rectangleButton);

        triangleButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T,
                java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        triangleButton.setText("Triangle");
        triangleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op.triangleButtonActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea, status);
            }
        });
        insert.add(triangleButton);

        menuBar.add(insert);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addComponent(drawArea, javax.swing.GroupLayout.Alignment.TRAILING,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(drawArea,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

}
