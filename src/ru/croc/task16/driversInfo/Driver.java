package ru.croc.task16.driversInfo;

public class Driver extends Person{
    private String identifier;


    @Override
    public String toString() {
        return super.toString()+"\n"+this.identifier;
    }
}
