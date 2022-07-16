package GUI;

import Board.DrawArea;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//singleton
public class ProgramUIOperator {

    private boolean select;
    private boolean fill;
    private String drawMode;
    private Color currentColor;
    private int stroke;
    private int imageCounter;
    private String prev;
    private static ProgramUIOperator op = new ProgramUIOperator();

    private ProgramUIOperator() {

    }

    public static ProgramUIOperator getInstance() {
        return op;
    }

    public void setInvisible(JLabel d1, JLabel d2, JTextField d1Box, JTextField d2Box) {
        d1.setVisible(false);
        d2.setVisible(false);
        d1Box.setVisible(false);
        d2Box.setVisible(false);
    }

    private void setColor(String c) {
        switch (c.toLowerCase()) {
            case "black":
                currentColor = Color.BLACK;
                break;
            case "blue":
                currentColor = Color.BLUE;
                break;
            case "cyan":
                currentColor = Color.CYAN;
                break;
            case "darkgray":
                currentColor = Color.DARK_GRAY;
                break;
            case "gray":
                currentColor = Color.GRAY;
                break;
            case "green":
                currentColor = Color.GREEN;
                break;
            case "yellow":
                currentColor = Color.YELLOW;
                break;
            case "lightgray":
                currentColor = Color.LIGHT_GRAY;
                break;
            case "magenta":
                currentColor = Color.MAGENTA;
                break;
            case "orange":
                currentColor = Color.ORANGE;
                break;
            case "pink":
                currentColor = Color.PINK;
                break;
            case "red":
                currentColor = Color.RED;
                break;
            case "white":
                currentColor = Color.WHITE;
                break;
            default:
                currentColor = Color.BLACK;
                break;
        }
    }

    public void lineButtonActionPerformed(java.awt.event.ActionEvent evt, JComboBox drawBox, JCheckBox fillBox, JLabel d1, JLabel d2, JTextField d1Box, JTextField d2Box, DrawArea drawArea,JLabel status) {
        drawBox.setSelectedIndex(0);
        drawBoxActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea,status);
    }

    public void selectButtonActionPerformed(java.awt.event.ActionEvent evt, JComboBox functionBox, JComboBox drawBox, JCheckBox fillBox, JComboBox strokeBox, JTextField d1Box, JTextField d2Box, JButton selectButton, DrawArea drawArea, JLabel status) {
        if (!select) {
            select = true;
            functionBox.setEnabled(true);
            drawBox.setEnabled(false);
            fillBox.setEnabled(false);
            strokeBox.setEnabled(false);
            d1Box.setEnabled(false);
            d2Box.setEnabled(false);
            selectButton.setText("Deselect");
            drawArea.setSelect(select);
            drawArea.setFunction("Move");
            prev = status.getText();
            status.setText("Move operation is underway");
        } else {
            select = false;
            functionBox.setEnabled(false);
            drawBox.setEnabled(true);
            fillBox.setEnabled(true);
            strokeBox.setEnabled(true);
            d1Box.setEnabled(true);
            d2Box.setEnabled(true);
            selectButton.setText("Select");
            drawArea.setSelect(select);
            functionBox.setSelectedIndex(0);
            drawArea.setFunction(null);
            status.setText(prev);
        }
    }

    public void strokeBoxActionPerformed(java.awt.event.ActionEvent evt, JComboBox strokeBox, DrawArea drawArea, JLabel status) {
        stroke = Integer.parseInt((String) strokeBox.getSelectedItem());
        drawArea.setStroke(stroke);
        status.setText("Stroke changed to " + stroke);
    }

    public void fillBoxActionPerformed(java.awt.event.ActionEvent evt, JCheckBox fillBox, DrawArea drawArea, JLabel status) {

        if (fillBox.isSelected()) {
            status.setText("Fill is activated");
            fill = true;
        } else {
            fill = false;
            status.setText("Fill is deactivated");
        }
        drawArea.setFill(fill);
    }

    public void colorBoxActionPerformed(java.awt.event.ActionEvent evt, JComboBox colorBox, DrawArea drawArea, JLabel status) {
        setColor((String) colorBox.getSelectedItem());
        status.setText("Color changed to " + colorBox.getSelectedItem());
        drawArea.setColor(currentColor);
    }

    public void d1BoxActionPerformed(java.awt.event.ActionEvent evt, JTextField d1Box, JLabel d1, DrawArea drawArea, JLabel status) {
        double val = 0;
        try {
            val = Double.parseDouble(d1Box.getText());
        } catch (NumberFormatException e) {
            d1Box.setText("100");
            val = 100;
        }
        if (drawMode.equals("Circle")) {
            drawArea.setDiameter(val);
        }
        if (drawMode.equals("Square")) {
            drawArea.setWidth(val);
        }
        if (drawMode.equals("Rectangle")) {
            drawArea.setWidth(val);
        }
        status.setText(d1.getText() + " changed to " + val);
    }

    public void d2BoxActionPerformed(java.awt.event.ActionEvent evt, JTextField d2Box, JLabel d2, DrawArea drawArea, JLabel status) {
        double val = 0;
        try {
            val = Double.parseDouble(d2Box.getText());
        } catch (NumberFormatException e) {
            d2Box.setText("100");
            val = 100;
        }
        if (drawMode.equals("Circle")) {
            drawArea.setDiameter(val);
        }
        if (drawMode.equals("Square")) {
            drawArea.setWidth(val);
        }
        if (drawMode.equals("Rectangle")) {
            drawArea.setHeight(val);
        }
        status.setText(d2.getText() + " changed to " + val);
    }

    public void saveButtonActionPerformed(java.awt.event.ActionEvent evt, DrawArea drawArea, JLabel status) {
        BufferedImage imagebuf = null;
        try {
            imagebuf = new Robot().createScreenCapture(drawArea.bounds());
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        Graphics2D graphics2D = imagebuf.createGraphics();
        drawArea.paint(graphics2D);
        try {
            ImageIO.write(imagebuf, "jpeg", new File("Elrasam2021 (" + imageCounter + ").jpeg"));
            imageCounter++;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Save failed !");
        }
        status.setText("Image saved");
    }

    public void functionBoxActionPerformed(java.awt.event.ActionEvent evt, JComboBox functionBox, DrawArea drawArea, JLabel status) {
        drawArea.setFunction((String) functionBox.getSelectedItem());
        status.setText(functionBox.getSelectedItem() + " operation is underway");
    }

    public void clearButtonActionPerformed(java.awt.event.ActionEvent evt, DrawArea drawArea, JComboBox drawBox, JLabel status) {
        drawMode = "Clear";
        drawArea.setDrawMode(drawMode);
        drawArea.clear();
        drawArea.repaint();
        drawBox.setSelectedIndex(0);
        status.setText("Draw board cleared");
    }

    public void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public void copyButtonActionPerformed(java.awt.event.ActionEvent evt, JComboBox functionBox, JComboBox drawBox, JCheckBox fillBox, JComboBox strokeBox, JTextField d1Box, JTextField d2Box, JButton selectButton, DrawArea drawArea, JLabel status) {
        select = true;
        functionBox.setEnabled(false);
        drawBox.setEnabled(false);
        fillBox.setVisible(false);
        strokeBox.setEnabled(false);
        d1Box.setEnabled(false);
        d2Box.setEnabled(false);
        selectButton.setText("Deselect");
        drawArea.setSelect(select);
        drawArea.setFunction("Copy");
        functionBox.setSelectedIndex(2);
        status.setText("Copy operation is underway");
    }

    public void deleteMenuButtonActionPerformed(java.awt.event.ActionEvent evt, JComboBox functionBox, JComboBox drawBox, JCheckBox fillBox, JComboBox strokeBox, JTextField d1Box, JTextField d2Box, JButton selectButton, DrawArea drawArea, JLabel status) {
        select = true;
        functionBox.setEnabled(false);
        drawBox.setEnabled(false);
        fillBox.setVisible(false);
        strokeBox.setEnabled(false);
        d1Box.setEnabled(false);
        d2Box.setEnabled(false);
        selectButton.setText("Deselect");
        drawArea.setSelect(select);
        drawArea.setFunction("Delete");
        functionBox.setSelectedIndex(3);
        status.setText("Delete operation is underway");
    }

    public void undoButtonActionPerformed(java.awt.event.ActionEvent evt, DrawArea drawArea, JLabel status) {
        drawArea.undo();
        status.setText("Undo done");
    }

    public void redoButtonActionPerformed(java.awt.event.ActionEvent evt, DrawArea drawArea, JLabel status) {
        drawArea.redo();
        status.setText("Redo done");
    }

    public void circleButtonActionPerformed(java.awt.event.ActionEvent evt, JComboBox drawBox, JCheckBox fillBox, JLabel d1, JLabel d2, JTextField d1Box, JTextField d2Box, DrawArea drawArea, JLabel status) {
        drawBox.setSelectedIndex(1);
        drawBoxActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea,status);
        status.setText("Circle is being drawn");
    }

    public void squareButtonActionPerformed(java.awt.event.ActionEvent evt, JComboBox drawBox, JCheckBox fillBox, JLabel d1, JLabel d2, JTextField d1Box, JTextField d2Box, DrawArea drawArea, JLabel status) {
        drawBox.setSelectedIndex(2);
        drawBoxActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea,status);
        status.setText("Square is being drawn");
    }

    public void rectangleButtonActionPerformed(java.awt.event.ActionEvent evt, JComboBox drawBox, JCheckBox fillBox, JLabel d1, JLabel d2, JTextField d1Box, JTextField d2Box, DrawArea drawArea, JLabel status) {
        drawBox.setSelectedIndex(3);
        drawBoxActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea,status);
        status.setText("Rectangle is being drawn");
    }

    public void triangleButtonActionPerformed(java.awt.event.ActionEvent evt, JComboBox drawBox, JCheckBox fillBox, JLabel d1, JLabel d2, JTextField d1Box, JTextField d2Box, DrawArea drawArea, JLabel status) {
        drawBox.setSelectedIndex(4);
        drawBoxActionPerformed(evt, drawBox, fillBox, d1, d2, d1Box, d2Box, drawArea,status);
        status.setText("Triangle is being drawn");
    }

    public void drawBoxActionPerformed(java.awt.event.ActionEvent evt, JComboBox drawBox, JCheckBox fillBox, JLabel d1, JLabel d2, JTextField d1Box, JTextField d2Box, DrawArea drawArea, JLabel status) {
        drawMode = (String) drawBox.getSelectedItem();
        drawArea.setDrawMode(drawMode);
        fillBox.setVisible(true);
        if (drawMode.equals("Line")) {
            setInvisible(d1, d2, d1Box, d2Box);
            fillBox.setVisible(false);
            status.setText("Line is being drawn");
        } else if (drawMode.equals("Circle")) {
            setInvisible(d1, d2, d1Box, d2Box);
            d1.setVisible(true);
            d1Box.setVisible(true);
            d1.setText("Diameter:");
            d1Box.setText("150");
            d1Box.setEnabled(true);
            drawArea.setDiameter(150.0);
            status.setText("Circle is being drawn");
        } else if (drawMode.equals("Square")) {
            setInvisible(d1, d2, d1Box, d2Box);
            d1.setVisible(true);
            d1Box.setVisible(true);
            d1.setText("Edge:");
            d1Box.setText("100");
            d1Box.setEnabled(true);
            drawArea.setWidth(100.0);
            status.setText("Square is being drawn");
        } else if (drawMode.equals("Rectangle")) {
            setInvisible(d1, d2, d1Box, d2Box);
            d1.setVisible(true);
            d1Box.setVisible(true);
            d2.setVisible(true);
            d2Box.setVisible(true);
            d1.setText("Width:");
            d2.setText("Height:");
            d1Box.setText("200");
            d2Box.setText("100");
            d1Box.setEnabled(true);
            d2Box.setEnabled(true);
            drawArea.setWidth(200.0);
            drawArea.setHeight(100.0);
            status.setText("Rectangle is being drawn");
        } else if (drawMode.equals("Triangle")) {
            setInvisible(d1, d2, d1Box, d2Box);
            status.setText("Triangle is being drawn");
        }
    }

    public void selectMenuButtonActionPerformed(java.awt.event.ActionEvent evt, JComboBox functionBox, JComboBox drawBox, JCheckBox fillBox, JComboBox strokeBox, JTextField d1Box, JTextField d2Box, JButton selectButton, DrawArea drawArea, JLabel status) {
        selectButtonActionPerformed(evt, functionBox, drawBox, fillBox, strokeBox, d1Box, d2Box, selectButton, drawArea, status);
    }

}
