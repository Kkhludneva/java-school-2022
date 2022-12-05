package ru.croc.task15;

public class AgeRangePair {
    private int lowerBound;
    private int upperBound;

    public AgeRangePair(int low, int up){
        if (low >= 0 && up>0 && up<=Person.OLDEST_PERSON_AGE && low < up) {
            this.lowerBound = low;
            this.upperBound = up;
        }else System.err.println("Недопустимый диапазон");
    }
    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    @Override
    public String toString() {
        if (this.upperBound == Person.OLDEST_PERSON_AGE){
            return (this.lowerBound)+"+ :";
        }if (this.lowerBound == 0){
            return this.lowerBound+" - "+this.upperBound+": ";
        }
        return (this.lowerBound+1)+" - "+this.upperBound+": ";
    }
}
