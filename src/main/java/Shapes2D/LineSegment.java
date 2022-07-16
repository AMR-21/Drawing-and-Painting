package Shapes2D;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class LineSegment extends Shapes {

    private Point2D p1, p2;

    public LineSegment(Point2D p1, Point2D p2, String ID, Color color, boolean fill, int stroke) {
        super(ID, color, fill, stroke);
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point2D getP1() {
        return p1;
    }

    public Point2D getP2() {
        return p2;
    }

    @Override
    public Shape draw() {
        return new Line2D.Double(p1.getX(), p1.getY(),p2.getX(),p2.getY());
    }


}
