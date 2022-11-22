package ru.croc.task10;

import java.util.Scanner;
import ru.croc.task10.AuctionRunnable;

public class Task10 {
    public static void main(String[] args) {
        System.out.println("Введите количество участников аукциона(не более 10): ");
        Scanner scanner = new Scanner(System.in);

        int numberOfBuyers = scanner.nextInt();
        if (numberOfBuyers<=10 && numberOfBuyers>0) {
            for (int i = 0; i < numberOfBuyers; i++) {
                Thread newThread = new Thread(new AuctionRunnable(i));
                newThread.start();
            }
        }else System.out.println("Некорректный ввод");
    }
}
