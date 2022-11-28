package ru.croc.task13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task13 {
    static Map<Integer,String> parseFilmsFromFile(File filmListData){
        Map<Integer,String> films = new HashMap<>();
        try (Scanner scanner = new Scanner(filmListData)){
            while(scanner.hasNextLine()){
                String[] film = scanner.nextLine().split(",");
                films.put(Integer.parseInt(film[0]),film[1]);
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        return films;
    }

    static List< List<Integer> > parseHistoryFromFile(File usersHistoryData){
        List< List<Integer> > usersHistory = new ArrayList<>();
        try (Scanner scanner = new Scanner(usersHistoryData)){
            while(scanner.hasNextLine()){
                String[] history = scanner.nextLine().split(",");
                List<Integer> historyNumbers = new ArrayList<>();
                for (int i=0;i<history.length;i++){
                    historyNumbers.add(Integer.parseInt(history[i]));
                }
                usersHistory.add(historyNumbers);
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        return usersHistory;
    }

    public static void main(String[] args) {
        File filmListData = new File("./src/ru/croc/task13/Films");
        //String filmFileData = "";
        Map<Integer,String> films = parseFilmsFromFile(filmListData);

        File usersHistoryData = new File("./src/ru/croc/task13/BrowsingHistory");
        List< List<Integer> > usersHistory =parseHistoryFromFile(usersHistoryData);

        List <Integer> curUser = new ArrayList<>();

        System.out.println("Введите номера просмотренных фильмов(от 1 до 6): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numbers = input.split("\\s");

        for (int j = 0; j < numbers.length; j++) {
            int value = Integer.valueOf(numbers[j]);
            if(value>0 && value<=6) {
                curUser.add(value);
            }else {
                System.err.println("Некорректный ввод");
                System.exit(0);
            }
        }

        RecommendGeneration recomendator = new RecommendGeneration(films,usersHistory);

        System.out.println(recomendator.getRecommendation(curUser));

        scanner.close();
    }


}
