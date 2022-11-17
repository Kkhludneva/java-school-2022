package ru.croc.task6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task6 {
    public static String removeJavaCommends (String s){//Функция, удаляющая комментарии
        return s.replaceAll("/\\*+[^*]*\\*+(?:[^/*][^*]*\\*+)*/","")
                .replaceAll("//.*[^;]\n","\n");
    }
    public static void main(String[] args) {
        File input = new File(".\\ru.croc.task6\\Task6.java");/*открываем
         файл*/
        try (Scanner s = new Scanner(input)){/*если получилось открыть файл
        выполним этот блок*/
            String source = "";//создаем строку, в которую запишем содержимое файла
            while(s.hasNextLine()){//считываем файл до конца
                source += s.nextLine() + "\n";//перевод на новую строку для каждой строки
            }
            /*удаляем комментарии
            и выводим
            в консоль*/
            System.out.println(removeJavaCommends(source));
        }catch (FileNotFoundException ex){/* если файл не нашёлся
        сообщаем об этом*/
            System.out.println("Файл не найден :(");
        }

    }
}
