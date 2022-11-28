package ru.croc.task13;

import java.util.*;

public class RecommendGeneration {

    private final Map<Integer, String> films;
    private final List<List<Integer>> usersHistory;

    public RecommendGeneration(Map<Integer, String> films, List<List<Integer>> usersHistory) {
        this.films = films;
        this.usersHistory = usersHistory;
    }

    public String getRecommendation(List<Integer> curUserHistory) {

        Map<Integer, Integer> statistics = new HashMap<>();
        Set<Integer> similarFilms = new HashSet<>();

        for (List<Integer> history : usersHistory) {
            similarFilms.clear();
            for (Integer h : history) {
                if (curUserHistory.contains(h)) {
                    //если нашли совпадение, то добавим общий элемент в множество
                    similarFilms.add(h);
                }
            }
            //проверяем, достаточно ли элементов в множестве
            if ((double) similarFilms.size() >= ((double) curUserHistory.size()) / 2) {
                //пробегаемся по текущей(подходящей по кол-ву совпадений) истории
                for (Integer h : history) {
                    if (curUserHistory.contains(h)){
                        continue;//пропускаем совпавшие эл-ты
                    }
                    //для не совпавших подсчитываем, сколько раз они встречаются
                    if (statistics.containsKey(h)) {
                        statistics.put(h, statistics.get(h) + 1);
                    } else {
                        statistics.put(h, 1);
                    }
                }
            }
            //ОТЛАДОЧНЫЙ ВЫВОД
            /*System.out.println("Набор: "+history);
            System.out.println("Совпали: "+similarFilms);
            System.out.println("Текущая статистика: "+statistics);
            */
        }
        //в собранной статистике ищем самый часто встречающийся
        int max = Integer.MIN_VALUE;
        int recommendationNumber = 0;
        for (int i = 1; i < films.size()+1; i++) {
            if (statistics.containsKey(i)){
               // System.out.println(max+" --- "+statistics.get(i));
                if (max<statistics.get(i)) {
                    max = statistics.get(i);
                    recommendationNumber = i;
                }
            }
        }
        //возвращаем самый часто встречающийся(обращаясь к map с фильмами)
        return films.get(recommendationNumber);
    }


}
