package ru.croc.task8;

import ru.croc.task8.exceprions.NoDoubleEnteredException;
import ru.croc.task8.exceprions.WrongLocaleEnteredException;

import java.text.NumberFormat;
import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.Scanner;


public class Task8 {
    public static void main(String[] args) {
        double sum = 0;

        Scanner scanner = new Scanner(System.in);
        String s = "";
        String language ="";
        String country = "";

        System.out.print("Enter language and country: ");
        System.out.println("");
        s = scanner.nextLine();
        String[] localeData = s.split(" ");

       NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());

        try {
            language = localeData[0];
            country = localeData[1];
            Locale customLocale = new Locale.Builder().setLanguage(language).setRegion(country).build();
            format = NumberFormat.getCurrencyInstance(customLocale);
        }
        catch (IndexOutOfBoundsException e){ //Если ввели менее двух слов
            try{
                throw new WrongLocaleEnteredException("You should enter two words: language and country",e);
            }catch (WrongLocaleEnteredException ex){
                System.err.println(ex.getMessage());
                System.err.println("Default locale was set");
            }
        }
        catch (IllformedLocaleException e){//Если такой локали не существует
            try {
                // System.out.println(e.getMessage());
                throw new WrongLocaleEnteredException("There is no such locale",language,country,e);
            }catch (WrongLocaleEnteredException ex){
                System.err.println(ex.getMessage());
                System.err.println("Default locale was set");
            }
        }

        System.out.print("");
        System.out.print("Enter a double: ");

        try {
             if (scanner.hasNextDouble()) {
                  sum = scanner.nextDouble();
             } else {
                  throw new NoDoubleEnteredException("No double entered, try again");
             }
        } catch (NoDoubleEnteredException e) {
               System.err.println(e.getMessage());
        }
        scanner.close();

        System.out.println(format.format(sum));


    }
}
