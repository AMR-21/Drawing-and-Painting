package Shapes2D;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;

public class Triangle extends Shapes {

    private int[] xPoints;
    private int[] yPoints;

    public Triangle(int[] xPoints, int[] yPoints, String ID, Color color, boolean fill, int stroke) {
        super(ID, color, fill, stroke);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    public int[] getXPoints() {
        return xPoints;
    }

    public int[] getYPoints() {
        return yPoints;
    }

    @Override
    public Shape draw() {
        return new Polygon(xPoints, yPoints, 3);
    }



}
