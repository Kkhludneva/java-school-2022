package ru.croc.task15;

import java.util.Objects;

public class Person {
    private String name;

    private int age;
    public static final int OLDEST_PERSON_AGE = 123;

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public Person(String name, int age){
        if (age > 0) {
            this.name = name;
            this.age = age;
        }
    }

    public boolean isInAgeGroup(AgeRangePair a){
        return this.age > a.getLowerBound() && this.age <= a.getUpperBound();
    }

    @Override
    public String toString() {
        return this.name+" ("+this.age+")";
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return (age == person.age) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
