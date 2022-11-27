package ru.croc.task10;


import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class AuctionItem {
    private static volatile int currentPrice;
    private static String buyerName;
    public static final LocalDateTime ENDING_TIME = LocalDateTime.now().plus(30, ChronoUnit.SECONDS);

    //теперь методы synchronized, можно не прописывать синхронизацию при обращении к ним в runnable классе
    public static synchronized void bet(int newPrice, String buyer){
        if (LocalDateTime.now().isBefore(ENDING_TIME) && newPrice>currentPrice) {
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
            currentPrice = newPrice;
            buyerName = buyer;
            System.out.println(buyerName+"---"+format.format(currentPrice));
        }
    }
    public static synchronized String winner(){
        if (LocalDateTime.now().isAfter(ENDING_TIME)){
            return buyerName;
        }
        return "Торги ещё не завершены";
    }
}
