package ru.croc.task10;

import java.time.LocalDateTime;
import java.util.Random;

public class AuctionRunnable implements Runnable{
    private static final Object lock = new Object();

    private int numberOfBuyer = 0;
    final Random generateBet = new Random();

    private String[] buyers ={"Богомолова Александра","Чернышев Даниил",
            "Кузнецова Вероника","Румянцев Демид","Кузьмина София",
            "Васильева Милана","Кудряшова Камилла","Морозов Александр",
            "Федорова Софья","Свиридов Никита"};

    public AuctionRunnable (int numberOfBuyer){
        this.numberOfBuyer = numberOfBuyer;
    }
    private static boolean auctionStopped = false;

    public void stopAuction() {
        auctionStopped = true;
    }
    @Override
    public void run() {
        while (LocalDateTime.now().isBefore(AuctionItem.ENDING_TIME)) {
            synchronized (lock) {
                int newBet = generateBet.nextInt();
                AuctionItem.bet(newBet, buyers[this.numberOfBuyer]);

                if (LocalDateTime.now().isAfter(AuctionItem.ENDING_TIME)) {
                    if (!auctionStopped)
                        System.out.println("\nWINNER IS:  " + AuctionItem.winner());
                    stopAuction();
                }
            }
        }
    }

}
