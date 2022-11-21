package ru.croc.task9;

import java.util.Scanner;

import static ru.croc.task9.HashTools.hashPassword;

public class Task9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = "ffsjfaa";//лучше в 5 потоков (f - 6ая буква)
        System.out.println("Пароль: "+s + "\nЕго hash: "+hashPassword(s));
        System.out.println("Введите количество потоков (для ffsjfaa, лучше 5 потоков)");
        int numberOfThreads = scanner.nextInt();
        if (s.length()==7) {
            PasswordGuessingFromMD5.tryToGuess(numberOfThreads, hashPassword(s));
        }
    }



}
