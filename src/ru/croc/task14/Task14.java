package ru.croc.task14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiPredicate;

public class Task14 {
    public static void main(String[] args) {
        File input = new File(".\\src\\ru\\croc\\task14\\commentsDataFile");
        String commentsData = "";
        try (Scanner scanner = new Scanner(input)){
            while(scanner.hasNextLine()){
                commentsData += scanner.nextLine() + "\n";
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }

        ArrayList<String> commentsFromForum = new ArrayList<>(Arrays.asList(commentsData.split("\n\n")));
        HashSet<String> blackList = new HashSet<>(Arrays.asList("помидор","картошк","томат","огур","кабач","дын"));

        BiPredicate <String,String> badWordFound =
                (comment, badWord)-> {if (comment.contains(badWord)) return true;
                                    else return false;};

        CommentsProcessing processing = new CommentsProcessing();
        Collection<String> validComments = processing.filterComments(commentsFromForum,blackList,badWordFound);

        System.out.println("--------ПОСЛЕ обработки-------");
        for (String s: validComments) {
            System.out.println(s+"\n");
        }

        System.out.println("--------ДО обработки-------");
        for (String s: commentsFromForum) {
            System.out.println(s+"\n");
        }

    }
}
