package ru.croc.task17;

import ru.croc.task17.dao.OrderDAO;
import ru.croc.task17.dao.ProductDAO;
import ru.croc.task17.pojo.Order;
import ru.croc.task17.pojo.Product;

import java.sql.*;

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
            creatingTables.execute("create table products(productCode varchar primary key," +
                    " productName varchar," +
                    "price integer);");
            creatingTables.execute("create table orders(orderNumber integer, userLogin varchar,productCode varchar," +
                    "CONSTRAINT FK_prodNum FOREIGN KEY (productCode) " +
                    "   REFERENCES products (productCode));");


            for (Product product: parser.getProducts()) {
                ProductDAO.create(product);
            }
            for (Order order: parser.getOrdersHistory()) {
                OrderDAO.create(order);
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
