package ru.croc.task15;

import java.util.*;
import java.lang.*;

public class Task15 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> ageBounders = parseToAgeBounders(args);

        //System.out.println(ageBounders);

        ArrayList<Person> people = new ArrayList<>();
        String s = scanner.nextLine();

        while (!s.equals("END")) {
            people.add(parseToPerson(s));
            s = scanner.nextLine();
        }

        DistributionIntoGroups distributor = new DistributionIntoGroups(ageBounders);

        Map<AgeRangePair, List<Person>> distribution =  distributor.getDistribution(people);

        for (AgeRangePair arp: distribution.keySet()) {
            System.out.print(arp+" ");
            for (Person p: distribution.get(arp)) {
                System.out.print(p+", ");
            }
            System.out.println();
        }
    }

    public static Person parseToPerson(String s) {
        String[] personData = s.split(",");
        if (Integer.valueOf(personData[1]) < 0 || Integer.valueOf(personData[1])>Person.OLDEST_PERSON_AGE){
            System.err.println("Некорректный ввод");
            System.exit(0);
        }
        return new Person(personData[0], Integer.valueOf(personData[1]));
    }

    public static ArrayList<Integer> parseToAgeBounders(String[] ages) {

        ArrayList<Integer> ageBounders = new ArrayList<>();
        ageBounders.add(0);
        for (int i = 0; i < ages.length; i++) {
            if (ageBounders.get(i ) >= Integer.valueOf(ages[i])) {
                System.err.println("Некорректный ввод");
                System.exit(0);
            }
            ageBounders.add(Integer.valueOf(ages[i]));
        }
        ageBounders.add(123);
        return ageBounders;
    }

}
