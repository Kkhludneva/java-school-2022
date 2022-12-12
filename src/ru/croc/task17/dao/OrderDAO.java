package ru.croc.task17.dao;

import ru.croc.task17.ShopDatabaseConnector;
import ru.croc.task17.pojo.Order;

import java.sql.*;

public class OrderDAO {
    public static void create(Order order) {
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
