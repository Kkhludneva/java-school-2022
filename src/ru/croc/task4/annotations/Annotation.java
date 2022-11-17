package ru.croc.task4.annotations;

import ru.croc.task4.figures.*;

public class Annotation {

        private String caption; // подпись
        private Figure figure;

        public Annotation(Figure figure,String string){
            this.figure = figure;
            this.caption = string;
        }
        public Annotation(double x0, double y0, double r, String s){
            this.figure = new Circle(x0, y0, r);
            this.caption = s;
        }

        public Annotation(double x0, double y0, double x1,double y1, String s){
            this.figure = new Rectangle(x0, y0, x1,y1);
            this.caption = s;
        }

        @Override
        public String toString() {
            return figure.toString()+": "+caption;
        }
}
