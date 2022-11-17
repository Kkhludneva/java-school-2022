package ru.croc.task8;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;


public class Task8 {
    public static void main(String[] args) {
        double sum = 0;
        System.out.print("Enter a double: ");

        Scanner scanner = new Scanner(System.in);

        //просим ввести double, если вводят другое - RunTimeException
        //не знаю, можно ли из RunTimeException в Exception, чтобы потом обработать
        // (попросить пользователя ввести сумму снова, например)
        try {
            sum = scanner.nextDouble();
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }

        Locale defaultLocale = Locale.getDefault();
        NumberFormat format = NumberFormat.getCurrencyInstance(defaultLocale);
        System.out.println(format.format(sum));
        /*
        тут не получилось:(
        System.out.println("Enter language: ");
        String language = scanner.nextLine();
        System.out.println("Enter country: ");
        String country = scanner.nextLine();

        Locale chosenLocale = new Locale(language,country);
        format = NumberFormat.getCurrencyInstance(chosenLocale);

        */

    }
}
