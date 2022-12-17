package ru.croc.task18.dao;

import ru.croc.task17.pojo.Order;
import ru.croc.task17.pojo.Product;

import java.sql.*;
import java.util.List;

public class OrderDAO {

    private final Connection dbConnection;

    public OrderDAO(Connection connection) {
        this.dbConnection = connection;
    }

    public Order createOrder(String userLogin, List<Product> products) {
        try {
            Statement selectStatement = dbConnection.createStatement();
            int lastOrderNum = 0;
            boolean hasRslt = selectStatement.execute("select * from \"order\"");
            if (hasRslt) {
                try (ResultSet result = selectStatement.getResultSet()) {
                    while (result.next()) {
                        int id = result.getInt("order_number");
                        if (id > lastOrderNum) {
                            lastOrderNum = id;
                        }
                    }
                }
            }

            String sql = "INSERT INTO \"order\" values(?,?,?)";
            for (Product product : products) {
                try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                    statement.setInt(1, lastOrderNum + 1);
                    statement.setString(2, userLogin);
                    statement.setString(3, product.getCode());
                    statement.executeUpdate();
                }
            }

            return new Order(lastOrderNum+1,userLogin,products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadOrder(Order order) {
        String sql = "INSERT INTO \"order\" values(?,?,?)";
        for (Product product : order.getProductCodes()) {
            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                statement.setInt(1, order.getNumber());
                statement.setString(2, order.getUserLogin());
                statement.setString(3, product.getCode());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void printAll() {
        try {
            Statement selectStatement = dbConnection.createStatement();
            System.out.println("-----Table: order------");
            boolean hasRslt = selectStatement.execute("select * from \"order\"");
            if (hasRslt) {
                try (ResultSet result = selectStatement.getResultSet()) {
                    while (result.next()) {
                        int id = result.getInt("order_number");
                        String userLogin = result.getString("user_login");
                        String productCode = result.getString("product_code");
                        System.out.println(id + " | " + userLogin + " | " + productCode);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
