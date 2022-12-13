package ru.croc.task18;

import ru.croc.task17.FileToObjectsParser;
import ru.croc.task17.pojo.*;
import ru.croc.task18.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopDatabaseConnector {
    public static final String URL = "jdbc:h2:mem:~/shop";
    private final FileToObjectsParser parser;

    public ShopDatabaseConnector(FileToObjectsParser parser){
        this.parser = parser;
    }

    public void createShopDatabase(){

        try {
            Connection conn = DriverManager.getConnection(URL);
            final Statement creatingTables = conn.createStatement();
            creatingTables.execute("create table products(price integer," +
                    " productName varchar," +
                    "productCode varchar);");
            creatingTables.execute("create table orders(orderNumber integer AUTO_INCREMENT, userLogin varchar,productCode varchar);");


            for (Product product: parser.getProducts()) {
                ProductDAO.createProduct(product);
            }

            for (Order order: parser.getOrdersHistory()) {
                OrderDAO.loadOrder(order);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showShopDatabase(){
        ProductDAO.printAll();
        OrderDAO.printAll();
    }
}
