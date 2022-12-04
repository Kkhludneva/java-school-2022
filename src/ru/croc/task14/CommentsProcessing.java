package ru.croc.task14;


import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiPredicate;

public class CommentsProcessing implements BlackListFilter<String,String>{
    @Override
    public Collection<String> filterComments(Collection<String> comments, Collection<String> blackList, BiPredicate<String,String> badWordFound) {
        Collection<String> validComments = new ArrayList<>();
        for (String s:comments) {
            for (String badWord:blackList) {
                if (badWordFound.test(s.toLowerCase(),badWord.toLowerCase())){
                    s = s.toLowerCase().replaceAll(badWord,badWord.replaceAll(".","*"));
                }
            }
            validComments.add(s);
        }
        return validComments;
    }


}
