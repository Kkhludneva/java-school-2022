package ru.croc.task18;

import ru.croc.task17.FileToObjectsParser;
import ru.croc.task17.pojo.*;
import ru.croc.task18.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopDatabaseConnector {
    public final Connection dbConnection;
    private final FileToObjectsParser parser;
    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;

    public ShopDatabaseConnector(FileToObjectsParser parser, Connection dbConnection) {
        this.parser = parser;
        this.dbConnection = dbConnection;
        this.productDAO = new ProductDAO(dbConnection);
        this.orderDAO = new OrderDAO(dbConnection);
    }

    public void createShopDatabase() {
        try {
            final Statement creatingTables = dbConnection.createStatement();
            creatingTables.execute("create table product(product_code varchar primary key," +
                    " product_name varchar," +
                    "price integer);");
            creatingTables.execute("create table \"order\" (order_number integer, user_login varchar,product_code varchar," +
                    "CONSTRAINT FK_prodNum FOREIGN KEY (product_code) " +
                    "   REFERENCES product (product_code) ON DELETE CASCADE);");
            for (Product product : parser.getProducts()) {
                productDAO.createProduct(product);
            }
            for (Order order : parser.getOrdersHistory()) {
                orderDAO.loadOrder(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showShopDatabase() {
        productDAO.printAll();
        orderDAO.printAll();
    }
}
