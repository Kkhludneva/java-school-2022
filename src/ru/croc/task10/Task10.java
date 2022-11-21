package ru.croc.task10;

import java.util.Scanner;
import ru.croc.task10.AuctionRunnable;

public class Task10 {
    public static void main(String[] args) {
        System.out.println("Введите количество участников аукциона: ");
        Scanner scanner = new Scanner(System.in);

        int numberOfBuyers = scanner.nextInt();
        for(int i = 0; i<numberOfBuyers;i++){
            Thread newThread = new Thread(new AuctionRunnable(i));
            newThread.start();
        }
    }
}
