package ru.croc.task16;

import ru.croc.task16.driversInfo.Driver;
import ru.croc.task16.driversInfo.Location;
import ru.croc.task16.driversInfo.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindingTaxiDriver {

    private static List<Person> drivers;

    public static void parseDrivers(File file) {
        String allData = "";
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                allData+= scanner.nextLine()+"\n";
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(allData);
        String[] allDriversData = allData.split(";");
        for (int i = 0; i < allDriversData.length; i++) {
            String[] curDriverSplitToFiends = allDriversData[i].split("\n");
        }
    }



}
