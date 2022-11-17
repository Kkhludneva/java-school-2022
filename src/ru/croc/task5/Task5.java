package ru.croc.task5;

import ru.croc.task5.annotations.*;

public class Task5 {

    public static void main(String[] args) {
        Annotation a1 = new Annotation(0,0 , 5,"Круг первый");
        Annotation a2 = new Annotation(0,0, 2,3,"Прямоугольник первый");

        AnnotatedImage image = new AnnotatedImage("address",a2,a1);
        System.out.println("Изображение содержит объекты: \n"+image+"\n");

        System.out.println("Подстрока \"руг\" впервые встречается в подписи объекта:"+image.findByLabel("руг"));
        System.out.println("Подстрока \"первый\" впервые встречается в подписи объекта:"+image.findByLabel("первый"));

        System.out.println("Точка (-3,-3) лежит в " +image.findByPoint(-3,-3));

        System.out.println("\n"+a1);
        a1.getFigure().move(1,-1);
        System.out.println("---Сдвиг фигуры на (1,-1)\n" + a1+ "\n");

        System.out.println(a2);
        a2.getFigure().move(1,1);
        System.out.println("---Сдвиг фигуры на (1,1) \n" +a2);

    }

}
