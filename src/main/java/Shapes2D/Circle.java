package Shapes2D;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Circle extends Shapes {

    private double diameter;
    private Point2D center;

    public Circle(double diameter, Point2D center, String ID, Color color, boolean fill, int stroke) {
        super(ID, color, fill, stroke);
        this.diameter = diameter;
        this.center = center;
    }

    public double getDiameter() {
        return diameter;
    }

    public Point2D getCenter() {
        return center;
    }

    @Override

    public Shape draw() {
        return new Ellipse2D.Double((center.getX() - diameter / 2), (center.getY() - diameter / 2), diameter, diameter);
    }



}
