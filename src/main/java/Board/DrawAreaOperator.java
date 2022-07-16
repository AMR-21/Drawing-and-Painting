package Board;

import Shapes2D.Circle;
import Shapes2D.LineSegment;
import Shapes2D.Rectangle;
import Shapes2D.Shapes;
import Shapes2D.ShapesFactory;
import Shapes2D.Triangle;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

public class DrawAreaOperator {

    private static final DrawAreaOperator op = new DrawAreaOperator();
    private ShapesFactory sf = ShapesFactory.getInstance();
    private Point lastLocation = new Point();

    private DrawAreaOperator() {

    }

    public static DrawAreaOperator getInstance() {
        return op;
    }

    public void paintComponent(Graphics g, String drawMode, DrawArea area, ArrayList<Shapes> shapes, ArrayList<Shape> drawShapes,ActionManager manager) {
        Graphics2D g2 = (Graphics2D) g;
        if (drawMode.equals("Clear")) {
            g2.setPaint(Color.white);
            g2.fillRect(0, 0, area.getSize().width, area.getSize().height);
            g2.setPaint(Color.black);
            clear(drawMode, shapes, drawShapes,manager);
        } else {
            g2.setPaint(Color.white);
            g2.fillRect(0, 0, area.getSize().width, area.getSize().height);
            for (int i = 0; i < shapes.size(); i++) {
                g2.setStroke(new BasicStroke(shapes.get(i).getStroke()));
                g2.setColor(shapes.get(i).getColor());
                if (shapes.get(i).getFill()) {
                    g2.fill(drawShapes.get(i));
                } else {
                    g2.draw(drawShapes.get(i));
                }
            }

        }
    }

    public void undo(ActionManager manager, String drawMode, DrawArea area, ArrayList<Shapes> shapes) {

        if (!manager.isEmpty()) {
            if (manager.size() == 1) {
                String cur = drawMode;
                drawMode = "Clear";
                manager.pop();
                drawMode = cur;
                area.repaint();
            } else {
                manager.pop();
                shapes.clear();
                shapes.addAll(manager.top());
                area.draw();
            }
        }

        if (manager.isEmpty()) {
            manager.push(shapes);
        }
    }

    public void redo(ActionManager manager, DrawArea area, ArrayList<Shapes> shapes) {
        if (!manager.isEmptyRedo()) {
            shapes.clear();
            shapes.addAll(manager.popRedo());
            area.draw();
            manager.push(shapes);
        }
    }

    public void clear(String drawMode, ArrayList<Shapes> shapes, ArrayList<Shape> drawShapes,ActionManager manager) {
        manager.push(shapes);
        shapes.clear();
        drawShapes.clear();
        drawMode = "Line";
    }

    public void draw(ArrayList<Shapes> shapes, ArrayList<Shape> drawShapes, DrawArea area) {
        drawShapes.clear();
        for (int i = 0; i < shapes.size(); i++) {
            drawShapes.add(shapes.get(i).draw());
        }
        area.repaint();
    }

    public Shape hitTest(Point p, ArrayList<Shape> drawShapes) {
        Shape hitShape = null;
        for (Shape testShape : drawShapes) {
            if (testShape instanceof Line2D) {
                Rectangle2D rec = testShape.getBounds2D();
                if (rec.contains(p)) {
                    hitShape = testShape;
                    break;
                }
            } else if (testShape.contains(p)) {
                hitShape = testShape;
                break;
            }
        }
        return hitShape;
    }

    public void edit(ArrayList<Shapes> shapes, ArrayList<Shape> drawShapes, Shape selectedShape, String function, Point2D lastLocation, DrawArea area, ActionManager manager) {
        for (int i = 0; i < shapes.size(); i++) {
            if (selectedShape == drawShapes.get(i)) {
                if (function.equals("Move")) {
                    if (shapes.get(i) instanceof Rectangle) {
                        Rectangle s = (Rectangle) shapes.get(i);
                        shapes.remove(i);
                        sf.inject(shapes, s.getWidth(), s.getHeight(), lastLocation, s.getColor(), s.getFill(), s.getStroke());
                        break;
                    } else if (shapes.get(i) instanceof Circle) {
                        Circle c = (Circle) shapes.get(i);
                        shapes.remove(i);
                        sf.inject(shapes, c.getDiameter(), lastLocation, c.getColor(), c.getFill(), c.getStroke());
                        break;
                    } else if (shapes.get(i) instanceof Triangle) {
                        Triangle t = (Triangle) shapes.get(i);
                        Polygon p = (Polygon) drawShapes.get(i);
                        shapes.remove(i);
                        sf.inject(shapes, p.xpoints, p.ypoints, t.getColor(), t.getFill(), t.getStroke());
                        break;
                    } else {
                        LineSegment l = (LineSegment) shapes.get(i);
                        Line2D l2 = (Line2D) drawShapes.get(i);
                        shapes.remove(i);
                        sf.inject(shapes, l2.getP1(), l2.getP2(), l.getColor(), l.getStroke());
                        break;
                    }
                }
            }
        }
        area.draw();
        manager.push(shapes);
    }

    public void move(String function, Shape selectedShape, MouseEvent e, DrawArea area) {
        if (function.equals("Move")) {
            if (selectedShape != null) {
                if (selectedShape instanceof RectangularShape) {
                    RectangularShape rShape = (RectangularShape) selectedShape;
                    float width = (float) rShape.getWidth();
                    float height = (float) rShape.getHeight();
                    rShape.setFrame(new Rectangle2D.Float(e.getX() - width / 2, e.getY() - height / 2, width, height));
                } else if (selectedShape instanceof Polygon) {
                    Polygon rShape = (Polygon) selectedShape;
                    rShape.translate(e.getX() - lastLocation.x, e.getY() - lastLocation.y);
                    lastLocation = e.getPoint();
                } else if (selectedShape instanceof Line2D) {
                    Line2D rShape = (Line2D) selectedShape;
                    rShape.setLine(rShape.getX1() + e.getX() - lastLocation.x, rShape.getY1() + e.getY() - lastLocation.y, rShape.getX2() + e.getX() - lastLocation.x, rShape.getY2() + e.getY() - lastLocation.y);
                    lastLocation = e.getPoint();
                }
                area.repaint();
            }
        }
    }

    public void color(ArrayList<Shapes> shapes, ArrayList<Shape> drawShapes, Shape selectedShape, Color currentColor, DrawArea area, ActionManager manager) {
        for (int i = 0; i < shapes.size(); i++) {
            if (selectedShape == drawShapes.get(i)) {
                Shapes s = shapes.get(i);
                shapes.remove(i);
                sf.inject(shapes, s, "color", currentColor, 0);
                area.draw();
                manager.push(shapes);
                break;
            }
        }
    }

    public void copy(ArrayList<Shapes> shapes, ArrayList<Shape> drawShapes, Shape selectedShape, Color currentColor, DrawArea area, ActionManager manager) {
        for (int i = 0; i < shapes.size(); i++) {
            if (selectedShape == drawShapes.get(i)) {
                sf.inject(shapes, shapes.get(i), "copy", currentColor, 0);
                area.draw();
                manager.push(shapes);
                break;
            }
        }
    }

    public void delete(ArrayList<Shapes> shapes, ArrayList<Shape> drawShapes, Shape selectedShape, DrawArea area, ActionManager manager) {
        for (int i = 0; i < shapes.size(); i++) {
            if (selectedShape == drawShapes.get(i)) {
                shapes.remove(i);
                break;
            }
        }
        area.draw();
        manager.push(shapes);
    }

    public void resize(ArrayList<Shapes> shapes, ArrayList<Shape> drawShapes, Shape selectedShape, DrawArea area, ActionManager manager, double percentage) {
        for (int i = 0; i < shapes.size(); i++) {
            if (selectedShape == drawShapes.get(i)) {
                Shapes s = shapes.get(i);
                shapes.remove(i);
                sf.inject(shapes, s, "resize", null, percentage);
                break;
            }
        }
        area.draw();
        manager.push(shapes);
    }

    public void drawLine(ArrayList<Shapes> shapes, double x1, double y1, double x2, double y2, Color currentColor, int currentStroke, DrawArea area, ActionManager manager) {
        sf.inject(shapes, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), currentColor, currentStroke);
        area.draw();
        manager.push(shapes);
    }

    public void drawCircle(ArrayList<Shapes> shapes, double diameter, MouseEvent e, Color currentColor, boolean currentFill, int currentStroke, DrawArea area, ActionManager manager, int mode) {
        sf.inject(shapes, diameter, new Point2D.Double(e.getX(), e.getY()), currentColor, currentFill, currentStroke);
        area.draw();
        if (mode == 1) {
            manager.push(shapes);
        }
    }

    public void drawRectangle(ArrayList<Shapes> shapes, double width, double height, MouseEvent e, Color currentColor, boolean currentFill, int currentStroke, DrawArea area, ActionManager manager) {
        sf.inject(shapes, width, height, e.getPoint(), currentColor, currentFill, currentStroke);
        area.draw();
        manager.push(shapes);
    }

    public void drawTriangle(int[] xPoints, int[] yPoints, ArrayList<Shapes> shapes, Color currentColor, boolean currentFill, int currentStroke, DrawArea area, ActionManager manager) {
        int[] xs = {xPoints[0], xPoints[1], xPoints[2]};
        int[] ys = {yPoints[0], yPoints[1], yPoints[2]};
        removeLastThree(shapes);
        sf.inject(shapes, xs, ys, currentColor, currentFill, currentStroke);
        manager.push(shapes);
        area.draw();
    }

    public void setLast(Point p) {
        lastLocation = p;
    }

    public void removeLastThree(ArrayList<Shapes> shapes) {
        for (int j = 0; j < 3; j++) {
            shapes.remove((shapes.size() - 1));
        }
    }
}
