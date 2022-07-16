package Board;

import Shapes2D.Shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//singleton

public class DrawArea extends JPanel implements MouseMotionListener, MouseListener {

    private ArrayList<Shapes> shapes;
    private ArrayList<Shape> drawShapes;
    private ActionManager manager;
    private Shape selectedShape;
    private boolean select;
    private Color currentColor;
    private int currentStroke;
    private boolean currentFill;
    private String drawMode;
    private String function;
    private double x1, x2, y1, y2;
    private double diameter;
    private double width, height;
    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];
    private int t = 0;
    private Point lastLocation;
    private double percentage;
    private DrawAreaOperator op;

    private static final DrawArea area = new DrawArea();

    private DrawArea() {
        super();
        addMouseListener(this);
        addMouseMotionListener(this);
        op = DrawAreaOperator.getInstance();
        manager = ActionManager.getInstance();
        shapes = new ArrayList<>();
        drawShapes = new ArrayList<>();
        drawMode = "Line";
        currentColor = Color.BLACK;
        currentStroke = 3;
        selectedShape = null;
        manager.push(shapes);
    }

    public static DrawArea getInstance() {
        return area;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        op.paintComponent(g, drawMode, this, shapes, drawShapes,manager);
    }

    public void undo() {
        op.undo(manager, drawMode, this, shapes);
    }

    public void redo() {
        op.redo(manager, this, shapes);
    }

    public void clear() {
        op.clear(drawMode, shapes, drawShapes,manager);
    }

    public void draw() {
        op.draw(shapes, drawShapes, this);
    }
    // setters *****************************************************************

    public void setStroke(int x) {
        currentStroke = x;
    }

    public void setFill(boolean b) {
        currentFill = b;
    }

    public void setColor(Color c) {
        currentColor = c;
    }

    public void setSelect(boolean b) {
        select = b;
    }

    public void setDrawMode(String d) {
        drawMode = d;
    }

    public void setFunction(String f) {
        function = f;
    }

    public void setDiameter(double d) {
        diameter = d;
    }

    public void setWidth(double d) {
        width = d;
    }

    public void setHeight(double d) {
        height = d;
    }
    // end setters *****************************************************************

    // move
    @Override
    public void mouseDragged(MouseEvent e) {
        if (select) {
            op.move(function, selectedShape, e, area);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (select) {
            selectedShape = op.hitTest(e.getPoint(), drawShapes);
            lastLocation = e.getPoint();
            op.setLast(lastLocation);
            if (selectedShape != null) {
                switch (function) {
                    case "Color":
                        op.color(shapes, drawShapes, selectedShape, currentColor, this, manager);
                        break;
                    case "Copy":
                        op.copy(shapes, drawShapes, selectedShape, currentColor, this, manager);
                        break;
                    case "Delete":
                        op.delete(shapes, drawShapes, selectedShape, this, manager);
                        break;
                    case "Resize":
                        String perc = JOptionPane.showInputDialog("Enter the percentage of resize");
                        if (perc != null) {
                            try {
                                percentage = Double.parseDouble(perc);
                            } catch (NumberFormatException e1) {
                                JOptionPane.showMessageDialog(null, "Wrong input");
                            }
                            if (percentage != 0) {
                                op.resize(shapes, drawShapes, selectedShape, this, manager, percentage);
                            }
                        }
                        break;
                }
            }
        } else {
            switch (drawMode) {
                case "Line":
                    x1 = e.getX();
                    y1 = e.getY();
                    break;
                case "Circle":
                    op.drawCircle(shapes, diameter, e, currentColor, currentFill, currentStroke, this, manager, 1);
                    break;
                case "Square":
                    op.drawRectangle(shapes, width, width, e, currentColor, currentFill, currentStroke, this, manager);
                    break;
                case "Rectangle":
                    op.drawRectangle(shapes, width, height, e, currentColor, currentFill, currentStroke, this, manager);
                    break;
                case "Triangle":
                    xPoints[t] = (int) e.getX();
                    yPoints[t] = (int) e.getY();
                    if (currentColor.equals(Color.MAGENTA)) {
                        op.drawCircle(shapes, 8.0, e, Color.GREEN, true, 3, this, manager, 0);
                    } else {
                        op.drawCircle(shapes, 8.0, e, Color.MAGENTA, true, 3, this, manager, 0);
                    }
                    t++;
                    if (t == 3) {
                        op.drawTriangle(xPoints, yPoints, shapes, currentColor, currentFill, currentStroke, this, manager);
                        t = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (select) {
            if (function.equals("Move")) {
                lastLocation = e.getPoint();
                op.setLast(lastLocation);
                op.edit(shapes, drawShapes, selectedShape, function, lastLocation, this, manager);
            }
            selectedShape = null;
            lastLocation = null;
        } else if (drawMode.equals("Line")) {
            x2 = e.getX();
            y2 = e.getY();
            op.drawLine(shapes, x1, y1, x2, y2, currentColor, currentStroke, this, manager);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
