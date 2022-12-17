package ru.croc.task17;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Task17 {
    public static void main(String[] args) {
        String url = String.join(" ", args);
        File storeData = new File(url);
        try {
            Connection dbConnector = DriverManager.getConnection("jdbc:h2:mem:~/shop");
            ShopDatabaseConnector connector = new ShopDatabaseConnector(new FileToObjectsParser(storeData),dbConnector);
            connector.createShopDatabase();
            connector.showShopDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
