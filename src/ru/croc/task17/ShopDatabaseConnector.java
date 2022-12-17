package ru.croc.task17;

import ru.croc.task17.dao.OrderDAO;
import ru.croc.task17.dao.ProductDAO;
import ru.croc.task17.pojo.Order;
import ru.croc.task17.pojo.Product;

import java.sql.*;

public class ShopDatabaseConnector {

    public final Connection dbConnection;
    private final FileToObjectsParser parser;



    public ShopDatabaseConnector(FileToObjectsParser parser,Connection dbConnection){
        this.parser = parser;
        this.dbConnection =dbConnection;
    }

    public void createShopDatabase(){

        try {
            final Statement creatingTables = dbConnection.createStatement();

            creatingTables.execute("create table product(product_code varchar primary key," +
                    " product_name varchar," +
                    "price integer);");
            creatingTables.execute("create table \"order\" (order_number integer, user_login varchar,product_code varchar," +
                    "CONSTRAINT FK_prodNum FOREIGN KEY (product_code) " +
                    "   REFERENCES product (product_code) ON DELETE CASCADE);");

            ProductDAO productDAO = new ProductDAO(dbConnection);
            OrderDAO orderDAO = new OrderDAO(dbConnection);

            for (Product product: parser.getProducts()) {
               productDAO.create(product);
            }
            for (Order order: parser.getOrdersHistory()) {
                orderDAO.create(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showShopDatabase(){
        ProductDAO productDAO = new ProductDAO(dbConnection);
        OrderDAO orderDAO = new OrderDAO(dbConnection);
        productDAO.printAll();
       orderDAO.printAll();
    }
}
