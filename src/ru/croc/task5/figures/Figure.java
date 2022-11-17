package ru.croc.task5.figures;

interface Movable{
    void move(int dx, int dy);
}
public abstract class Figure implements Movable{
    public boolean isPointInside(double x, double y){
        return true;
    }
}
