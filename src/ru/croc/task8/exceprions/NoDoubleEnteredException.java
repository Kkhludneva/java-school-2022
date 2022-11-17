package ru.croc.task8.exceprions;

public class NoDoubleEnteredException extends Exception{

    private String message;

    public NoDoubleEnteredException (String m){
        super(m);
        this.message = m;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
