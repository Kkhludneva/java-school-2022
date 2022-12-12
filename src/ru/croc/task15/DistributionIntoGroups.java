package ru.croc.task15;

import java.util.*;

public class DistributionIntoGroups {
    private final ArrayList<Integer> ageBounders;

    public DistributionIntoGroups(ArrayList<Integer> ageBounders) {
        this.ageBounders = ageBounders;
    }

    public Map<AgeRangePair, List<Person>> getDistribution(ArrayList<Person> people) {

        //treeMap может отсортировать возрастные интервалы по убыванию, передадим ей компаратор
        //сравнивать интервалы будем по нижней границе
        Map<AgeRangePair, List<Person>> distribution = new TreeMap<>(Collections.reverseOrder(Comparator.comparingInt(AgeRangePair::getLowerBound)));

        for (Person p : people) {
            for (int i = 0; i < ageBounders.size() - 1; i++) {
                AgeRangePair ageRange = new AgeRangePair(ageBounders.get(i),ageBounders.get(i + 1));
                if (p.isInAgeGroup(ageRange)) {
                    if (distribution.get(ageRange) != null && distribution.get(ageRange).contains(p)){
                        continue;//проверяем, не добавлен ли уже полный тезка
                    }
                    //добавляем человека в список по данному ключу
                    distribution.
                            computeIfAbsent(ageRange, k -> new ArrayList<>())
                            .add(p);
                }
            }
        }

        return distribution;
    }


}
