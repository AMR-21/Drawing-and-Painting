package Shapes2D;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
//singleton and factory
//1 line 2 square 3 rectangle 4 circle 5 triangle

public class ShapesFactory {

    private static final ShapesFactory sf = new ShapesFactory();

    private ShapesFactory() {

    }

    public static ShapesFactory getInstance() {
        return sf;
    }

    // line
    public void inject(ArrayList<Shapes> sh, Point2D p1, Point2D p2, Color c, int s) {
        LineSegment l = new LineSegment(p1, p2, "Line", c, false, s);
        sh.add(l);
    }

    //square - rectangle
    public void inject(ArrayList<Shapes> sh, double w, double h, Point2D p, Color c, boolean f, int s) {
        Rectangle r = new Rectangle(w, h, p, "Rectangle", c, f, s);
        if (w == h) {
            r.setID("Square");
        }
        sh.add(r);
    }

    //circle
    public void inject(ArrayList<Shapes> sh, double d, Point2D p, Color c, boolean f, int s) {
        Circle ci = new Circle(d, p, "Circle", c, f, s);
        sh.add(ci);
    }

    //triangle
    public void inject(ArrayList<Shapes> sh, int[] x, int[] y, Color c, boolean f, int s) {
        sh.add(new Triangle(x, y, "Triangle", c, f, s));
    }

    //function
    public void inject(ArrayList<Shapes> sh, Shapes s, String mode, Color currentColor, double percentage) {
        switch (mode) {
            case "color":
                if (s instanceof Rectangle) {
                    Rectangle r = (Rectangle) s;
                    inject(sh, r.getWidth(), r.getHeight(), r.getCenter(), currentColor, true, r.getStroke());
                    break;
                } else if (s instanceof Circle) {
                    Circle c = (Circle) s;
                    inject(sh, c.getDiameter(), c.getCenter(), currentColor, true, c.getStroke());
                } else if (s instanceof Triangle) {
                    Triangle t = (Triangle) s;
                    int[] xs = t.getXPoints();
                    int[] ys = t.getYPoints();
                    inject(sh, xs, ys, currentColor, true, t.getStroke());
                } else {
                    LineSegment l = (LineSegment) s;
                    inject(sh, l.getP1(), l.getP2(), currentColor, l.getStroke());
                }
                break;
            case "copy":
                if (s instanceof Rectangle) {
                    Rectangle r = (Rectangle) s;
                    inject(sh, r.getWidth(), r.getHeight(), new Point2D.Double(r.getCenter().getX() + 20, r.getCenter().getY() + 20), r.getColor(), r.getFill(), r.getStroke());
                    break;
                } else if (s instanceof Circle) {
                    Circle c = (Circle) s;
                    inject(sh, c.getDiameter(), new Point2D.Double(c.getCenter().getX() + 20, c.getCenter().getY() + 20), c.getColor(), c.getFill(), c.getStroke());
                } else if (s instanceof Triangle) {
                    Triangle t = (Triangle) s;
                    int[] xs = {t.getXPoints()[0] + 20, t.getXPoints()[1] + 20, t.getXPoints()[2] + 20};
                    int[] ys = {t.getYPoints()[0] + 20, t.getYPoints()[1] + 20, t.getYPoints()[2] + 20};
                    inject(sh, xs, ys, t.getColor(), t.getFill(), t.getStroke());
                } else {
                    LineSegment l = (LineSegment) s;
                    inject(sh, new Point2D.Double(l.getP1().getX() + 50, l.getP1().getY() + 40), new Point2D.Double(l.getP2().getX() + 50, l.getP2().getY() + 50), l.getColor(), l.getStroke());
                }
                break;
            case "resize":
                if (s instanceof Rectangle) {
                    System.out.println("Shapes2D.ShapesFactory.inject()");
                    Rectangle r = (Rectangle) s;
                    inject(sh, r.getWidth() + r.getWidth() * percentage / 100, r.getHeight() + r.getHeight() * percentage / 100, r.getCenter(), r.getColor(), r.getFill(), r.getStroke());
                    break;
                } else if (s instanceof Circle) {
                    Circle c = (Circle) s;
                    inject(sh, c.getDiameter() + c.getDiameter() * percentage / 100, c.getCenter(), c.getColor(), c.getFill(), c.getStroke());
                } else if (s instanceof Triangle) {
                    Triangle t = (Triangle) s;
                    int per = (int) Math.round(percentage);
                    int[] xs = {t.getXPoints()[0] + t.getXPoints()[0] * per / 100, t.getXPoints()[1] + t.getXPoints()[1] * per / 100, t.getXPoints()[2] + t.getXPoints()[2] * per / 100};
                    int[] ys = {t.getYPoints()[0] + t.getYPoints()[0] * per / 100, t.getYPoints()[1] + t.getYPoints()[1] * per / 100, t.getYPoints()[2] + t.getYPoints()[2] * per / 100};
                    inject(sh, xs, ys, t.getColor(), t.getFill(), t.getStroke());
                } else {
                    LineSegment l = (LineSegment) s;
                    inject(sh, new Point2D.Double(l.getP1().getX() + l.getP1().getX() * percentage / 100, l.getP1().getY() + l.getP1().getY() * percentage / 100), new Point2D.Double(l.getP2().getX() + l.getP2().getX() * percentage / 100, l.getP2().getY() + l.getP2().getY() * percentage / 100), l.getColor(), l.getStroke());
                }
                break;
        }
    }
}
