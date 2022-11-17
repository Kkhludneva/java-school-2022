package ru.croc.task4;

import ru.croc.task4.annotations.*;
import ru.croc.task4.figures.*;
public class Task4 {

    public static void main(String[] args) {
        Annotation a1 = new Annotation(0,0 , 2,"Круг");
        Annotation a2 = new Annotation(0,0, 2,3,"Прямоугольник");

        Figure f1 = new Circle(1,1,5);
        Annotation a3 = new Annotation (f1, "Большой круг");

        System.out.println(a3);
        System.out.println(a1);
        System.out.println(a2);
    }

}