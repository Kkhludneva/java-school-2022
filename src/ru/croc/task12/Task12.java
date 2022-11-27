package ru.croc.task12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*Пусть в качестве комментариев пользователей будут посты с форума дачников-огородников,
а в качестве плохих слов, которые нужно убрать - названия овощей :)
(надеюсь, так можно)
Названия овощей заменяются на звездочки (случай с опечаткой на 1 букву не обрабатывается)
 */
public class Task12 {
    public static void main(String[] args) {
        File input = new File(".\\src\\ru\\croc\\task12\\commentsDataFile");
        String commentsData = "";
        try (Scanner scanner = new Scanner(input)){
            while(scanner.hasNextLine()){
                commentsData += scanner.nextLine() + "\n";
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        System.out.println("------ПОСТЫ ДО ОБРАБОТКИ----------------\n"+commentsData);
        ArrayList<String> commentsFromForum = new ArrayList<>(Arrays.asList(commentsData.split("\n\n")));

        Set<String> blackList = new HashSet<>(Arrays.asList("помидор","картошк","томат","огур","кабач","дын"));

        InvalidCommentsProcessing gardeningCommentsProcessing = new InvalidCommentsProcessing();
        gardeningCommentsProcessing.filterComments(commentsFromForum,blackList);

        System.out.println("------ПОСТЫ ПОСЛЕ ОБРАБОТКИ----------------");
        System.out.println(gardeningCommentsProcessing);
    }
}
