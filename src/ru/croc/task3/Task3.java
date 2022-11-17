package ru.croc.task3;

import java.util.Scanner;

public class Task3 {
    static class Point{
        double x;
        double y;

        public void ReadPoint(double x, double y){
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Point a = new Point();
        Point b = new Point();
        Point c = new Point();
        a.ReadPoint(s.nextDouble(),s.nextDouble());
        b.ReadPoint(s.nextDouble(),s.nextDouble());
        c.ReadPoint(s.nextDouble(),s.nextDouble());

        //длины сторон
        double ab = Math.sqrt(Math.pow(b.x - a.x,2)+Math.pow(b.y - a.y,2));
        double ac = Math.sqrt(Math.pow(c.x - a.x,2)+Math.pow(c.y - a.y,2));
        double cb = Math.sqrt(Math.pow(b.x - c.x,2)+Math.pow(b.y - c.y,2));

        //sqrt(1 - cos^2), где cos - по теореме косинусов
        double mySin = Math.sqrt(1-Math.pow((ab*ab + ac*ac - cb*cb)/(2*ab*ac),2));
        //по свойству векторного произведения
        double square = ab*ac*mySin*0.5;

        System.out.println(square);
    }
}
