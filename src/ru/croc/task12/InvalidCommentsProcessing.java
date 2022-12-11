package ru.croc.task12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InvalidCommentsProcessing implements BlackListFilter{

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        for (int i = 0; i<comments.size();i++) {
            for (String badWord:blackList) {
                if (comments.get(i).toLowerCase().contains
                        (badWord.toLowerCase())){
                    //перевела всё в строчные буквы, чтобы регистр не мешал обработать совпадение
                    comments.set(i, comments.get(i).toLowerCase()
                            .replaceAll(badWord,badWord.replaceAll(".","*")));
                }
            }
        }
    }


}
