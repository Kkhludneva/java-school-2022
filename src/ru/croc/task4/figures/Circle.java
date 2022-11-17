package ru.croc.task4.figures;

public class Circle extends Figure{
    private double x0;

    private double y0;
    private double r;

    public Circle(double x0, double y0, double r){
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;
    }
    @Override
    public String toString() {
        return "Circle (" + this.x0 + ", " + this.y0 + ") R = " + this.r;
    }

}
