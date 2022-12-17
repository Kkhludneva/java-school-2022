package ru.croc.task17;

import java.io.File;


public class Task17 {
    public static void main(String[] args) {
        String url = String.join(" ", args);
        File storeData = new File(url);

        ShopDatabaseConnector connector = new ShopDatabaseConnector(new FileToObjectsParser(storeData));
        connector.createShopDatabase();
        connector.showShopDatabase();

    }
}
