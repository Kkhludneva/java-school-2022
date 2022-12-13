package ru.croc.task18.dao;

import ru.croc.task17.ShopDatabaseConnector;
import ru.croc.task17.pojo.Order;
import ru.croc.task17.pojo.Product;

import java.sql.*;
import java.util.List;

public class OrderDAO {

    public static Order createOrder(String userLogin, List<Product> products){
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            Statement selectStatement = conn.createStatement();
            int lastOrderNum = 0;
            boolean hasRslt = selectStatement.execute("select * from orders");
            if (hasRslt){
                try (ResultSet result = selectStatement.getResultSet()) {
                    while (result.next()) {
                        int id = result.getInt("orderNumber");
                        if (id > lastOrderNum){
                            lastOrderNum = id;
                        }
                    }
                }
            }
            String sql = "INSERT INTO orders values(?,?,?)";
            for (Product product : products) {
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                   statement.setInt(1, lastOrderNum+1);
                    statement.setString(2, userLogin);
                    statement.setString(3, product.getCode());
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Order loadOrder(Order order) {
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            String sql = "INSERT INTO orders values(?,?,?)";
            for (String product : order.getProductCodes()) {
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, order.getNumber());
                    statement.setString(2, order.getUserLogin());
                    statement.setString(3, product);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    public static void printAll() {
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            Statement selectStatement = conn.createStatement();
            System.out.println("-----Table: orders------");
            boolean hasRslt = selectStatement.execute("select * from orders");
            if (hasRslt){
                try (ResultSet result = selectStatement.getResultSet()) {
                    while (result.next()) {
                        int id = result.getInt("orderNumber");
                        String userLogin = result.getString("userLogin");
                        String productCode = result.getString("productCode");
                        System.out.println(id+" | "+userLogin+" | " + productCode);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
