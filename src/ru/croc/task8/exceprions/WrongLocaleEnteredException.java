package ru.croc.task8.exceprions;

public class WrongLocaleEnteredException extends Exception{
    private String message;
    private String enteredLanguage;
    private String enteredCountry;

    public WrongLocaleEnteredException (String m,String lang,String count, Throwable e){
        super(m,e);
        this.message = m;
        this.enteredLanguage=lang;
        this.enteredCountry=count;
    }

    public WrongLocaleEnteredException (String m, Throwable e){
        super(m,e);
        this.message = m;
    }

    @Override
    public String getMessage() {
        return message+" language: "+enteredLanguage+" country: "+enteredCountry;
    }
}
