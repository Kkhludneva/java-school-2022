package ru.croc.task2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double a0 = s.nextDouble(); //первый член арифм прогрессии
        double delta = s.nextDouble(); //разность
        int n = s.nextInt(); //количество членов прогрессии
        double sum = 0;
        for (int i = 0; i < n; i++){
            sum += a0 + delta*i;
        }
        System.out.println(sum);
    }
}
