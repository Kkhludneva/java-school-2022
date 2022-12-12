package ru.croc.task17;

import ru.croc.task17.DAO.OrderDAO;
import ru.croc.task17.DAO.ProductDAO;
import ru.croc.task17.DAO.UserDAO;
import ru.croc.task17.POJO.Order;
import ru.croc.task17.POJO.Product;
import ru.croc.task17.POJO.User;

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
            creatingTables.execute("create table users(orderNumber integer primary key, login varchar);");
            creatingTables.execute("create table products(productCode varchar primary key," +
                    " productName varchar," +
                    "price integer);");
            creatingTables.execute("create table orders(orderNumber integer, productCode varchar," +
                    "CONSTRAINT FK_orderNum FOREIGN KEY (orderNumber) " +
                    " REFERENCES users (orderNumber)," +
                    "CONSTRAINT FK_prodNum FOREIGN KEY (productCode) " +
                    "   REFERENCES products (productCode));");

            for (User user: parser.getUsers()) {
                UserDAO.create(user);
            }
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
        UserDAO.selectAll();
        ProductDAO.selectAll();
        OrderDAO.selectAll();
    }
}
