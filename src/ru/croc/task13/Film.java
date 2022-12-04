package ru.croc.task13;

import java.util.Iterator;
import java.util.Objects;

public class Film{
    private int number;
    private String title;

    public int getNumber() {
        return number;
    }
    public String getTitle() {
        return title;
    }


    public Film(int number, String title){
        this.number = number;
        this.title = title;
    }
    public Film(){}


    @Override
    public String toString() {
        return this.getNumber()+") "+this.getTitle()+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film film)) return false;
        return number == film.number && Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, title);
    }

}
