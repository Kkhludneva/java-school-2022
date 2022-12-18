package ru.croc.individualProject.verbProcessing;

public class Verb {
    private final String firstForm;
    private final String secondForm;
    private final String thirdForm;


    public Verb (String first, String second, String third){
        this.firstForm = first;
        this.secondForm = second;
        this.thirdForm = third;
    }

    public String getSecondForm() {
        return secondForm;
    }

    public String getThirdForm() {
        return thirdForm;
    }

    public String getFirstForm() {
        return firstForm;
    }

    @Override
    public String toString() {
        return this.firstForm+" "+this.secondForm+" "+this.thirdForm;
    }


}
