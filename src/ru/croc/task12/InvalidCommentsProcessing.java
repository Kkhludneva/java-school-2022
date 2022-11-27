package ru.croc.task12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InvalidCommentsProcessing implements BlackListFilter{

    private List<String> validComments;

    public InvalidCommentsProcessing(){
        this.validComments = new ArrayList<>();
    }

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        for (String comment: comments) {
            for (String badWord: blackList) {
                if (comment.toLowerCase().contains
                        (badWord.toLowerCase())){
                    //перевела всё в строчные буквы, чтобы регистр не мешал обработать совпадение
                    comment = comment.toLowerCase()
                            .replaceAll(badWord, badWord.replaceAll(".","*"));
                }
            }
            this.validComments.add(comment);
        }
    }

    @Override
    public String toString() {
        String rslt = "";
        for (String comment: validComments) {
           rslt += comment+"\n\n";
        }
        return rslt;
    }
}
