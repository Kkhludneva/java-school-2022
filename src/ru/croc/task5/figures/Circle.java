package ru.croc.task5.figures;

public class Circle extends Figure {
    private double x0;
    private double y0;
    private double r;

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getR() {
        return r;
    }

    public Circle(double x0, double y0, double r){
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;
    }
    @Override
    public String toString() {
        return "Circle (" + this.x0 + ", " + this.y0 + ") R = " + this.r;
    }
    @Override
    public void move(int dx, int dy) {
        this.x0 += dx;
        this.y0 += dy;
    }

    @Override
    public boolean isPointInside(double x, double y) {
        if(((x-this.x0)*(x-this.x0)+(y-this.y0)*(y-this.y0))<= this.r*this.r){
            return true;
        }
        else{
            return false;
        }
    }
}
