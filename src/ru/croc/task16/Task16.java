package ru.croc.task16;

import java.io.File;
import java.util.List;

public class Task16 {
    public static void main(String[] args) {
        File input = new File("./src/ru/croc/task16/driversInfo/driversDataFile");

        FindingTaxiDriver.parseDrivers(input);

    }
}
