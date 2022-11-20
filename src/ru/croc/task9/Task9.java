package ru.croc.task9;

import java.util.Scanner;

import static ru.croc.task9.HashTools.hashPassword;

public class Task9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = "aafkate";
        System.out.println("Пароль: "+s + "\nЕго hash: "+hashPassword(s));
        System.out.println("Введите количество потоков");
        int numberOfThreads = scanner.nextInt();
        System.out.println(numberOfThreads);
        if (s.length()==7) {
            PasswordGuessingFromMD5.tryToGuess(numberOfThreads, hashPassword(s));
        }
    }



}
