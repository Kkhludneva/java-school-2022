package ru.croc.task14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public interface BlackListFilter <C>{
    /**
     * From the given list of comments removes ones
     * that contain words from the black list.
     *
     * @param comments list of comments; every comment
     *                 is a sequence of words, separated
     *                 by spaces, punctuation or line breaks
     * @param CommentIsForbidden predicate which accepts the comment and
     *                     returns true if it should be deleted,
     *                     otherwise returns true
     */
    default Collection<C> filterComments(Iterable<C> comments, Predicate<C> CommentIsForbidden){
        Collection<C> validComments = new ArrayList<>();

        Iterator<C> it = comments.iterator();
        while (it.hasNext()) {
            C comment = it.next();
            if (!CommentIsForbidden.test(comment)) {
               validComments.add(comment);
            }
        }
        return validComments;
    }
}