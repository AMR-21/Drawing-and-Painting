package Shapes2D;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shapes {

    private double width;
    private double height;
    private Point2D center;

    public Rectangle(double width, double height, Point2D center, String ID, Color color, boolean fill, int stroke) {
        super(ID, color, fill, stroke);
        this.width = width;
        this.height = height;
        this.center = center;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Point2D getCenter() {
        return center;
    }

    @Override

    public Shape draw() {
        return new Rectangle2D.Double((center.getX() - width / 2), (center.getY() - height / 2), width, height);
    }


}
