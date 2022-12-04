package ru.croc.task13;

import java.util.*;

public class RecommendGeneration {

    private final List<Film> films;
    private final List<List<Film>> usersHistory;

    public RecommendGeneration(List<Film> films, List<List<Film>> usersHistory) {
        this.films = films;
        this.usersHistory = usersHistory;
    }

    public String getRecommendation(List<Film> curUserHistory) {

        Map<Film, Integer> statistics = new HashMap<>();
        Set<Film> similarFilms = new HashSet<>();

        for (List<Film> history : usersHistory) {
            similarFilms.clear();
            for (Film h : history) {
                if (curUserHistory.contains(h)) {
                    //если нашли совпадение, то добавим общий элемент в множество
                    similarFilms.add(h);
                }
            }
            //проверяем, достаточно ли элементов в множестве
            if ((double) similarFilms.size() >= ((double) curUserHistory.size()) / 2) {
                //пробегаемся по текущей(подходящей по кол-ву совпадений) истории
                for (Film h : history) {
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
            System.out.println("Текущая статистика: "+statistics);*/

        }
        //в собранной статистике ищем самый часто встречающийся
        int max = Integer.MIN_VALUE;
        Film recommendation = new Film();

        for (Film f: statistics.keySet()) {
            if (statistics.get(f) > max){
                max = statistics.get(f);
                recommendation = f;
            }
        }
        //возвращаем самый часто встречающийся(обращаясь к map с фильмами)
        return recommendation.getTitle();
    }


}
