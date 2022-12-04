package ru.croc.task13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task13 {

    public static void main(String[] args) {
        File filmListData = new File("./src/ru/croc/task13/files/Films");

        List <Film> films = parseFilmsFromFile(filmListData);

        File usersHistoryData = new File("./src/ru/croc/task13/files/BrowsingHistory");
        List< List<Film> > usersHistory =parseHistoryFromFile(usersHistoryData, films);

        System.out.println("Введите номера просмотренных фильмов(от 1 до "+films.size()+ "): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numbers = input.split("\\s");

        List <Film> curUser = parseFilmsFromStringArray(numbers,films);


       RecommendGeneration recomendator = new RecommendGeneration(films,usersHistory);
       System.out.println(recomendator.getRecommendation(curUser));

        scanner.close();
    }

    static  List<Film>  parseFilmsFromFile(File filmListData){
        List <Film> films = new ArrayList<>();
        try (Scanner scanner = new Scanner(filmListData)){
            while(scanner.hasNextLine()){
                String[] film = scanner.nextLine().split(",");
                int number = Integer.parseInt(film[0]);
                films.add(new Film(number,film[1]));

            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        return films;
    }

    static  List< List<Film> > parseHistoryFromFile(File usersHistoryData,  List <Film> films){
        List< List<Film> > usersHistory = new ArrayList<>();
        try (Scanner scanner = new Scanner(usersHistoryData)){
            while(scanner.hasNextLine()){
                String[] historyNumbers = scanner.nextLine().split(",");
                List<Film> historyFilms = new ArrayList<>();
                for (int i=0;i<historyNumbers.length;i++){
                    for (Film f: films) {
                        if (f.getNumber() == Integer.valueOf(historyNumbers[i])){
                            historyFilms.add(f);
                        }
                    }
                }
                usersHistory.add(historyFilms);
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        return usersHistory;
    }

    static List<Film> parseFilmsFromStringArray(String[] numbers, List<Film> films){
        List <Film> curUser = new ArrayList<>();
        for (int j = 0; j < numbers.length; j++) {
            int value = Integer.valueOf(numbers[j]);
            if(value>0 && value<=films.size()) {
                for (Film f: films) {
                    if (f.getNumber() == value){
                        curUser.add(f);
                    }
                }
            }else {
                System.err.println("Некорректный ввод");
                System.exit(0);
            }
        }
        return curUser;
    }

}
