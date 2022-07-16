package Shapes2D;

import java.awt.Color;
import java.awt.Shape;

public abstract class Shapes {

    private String ID;
    private Color color;
    private boolean fill;
    private int stroke;

    public Shapes(String ID, Color color, boolean fill, int stroke) {
        this.ID = ID;
        this.color = color;
        this.fill = fill;
        this.stroke = stroke;
    }

    public void setID(String s) {
        ID = s;
    }

    public int getStroke() {
        return stroke;
    }

    public Color getColor() {
        return color;
    }

    public boolean getFill() {
        return fill;
    }

    public String getID() {
        return ID;
    }

    public abstract Shape draw();

}
