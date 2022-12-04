package ru.croc.task14;

import java.util.Collection;
import java.util.function.BiPredicate;

public interface BlackListFilter <C,BW>{
    /**
     * From the given list of comments removes ones
     * that contain words from the black list.
     *
     * @param comments list of comments; every comment
     *                 is a sequence of words, separated
     *                 by spaces, punctuation or line breaks
     * @param blackList list of words that should not
     *                  be present in a comment
     */
    Collection<C> filterComments(Collection<C> comments, Collection<BW> blackList, BiPredicate<C,BW> badWordFound);
}